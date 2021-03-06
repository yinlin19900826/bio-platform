package com.biocome.platform.basemanager.biz;

import com.ace.cache.annotation.Cache;
import com.ace.cache.annotation.CacheClear;
import com.biocome.platform.basemanager.entity.CameraGroup;
import com.biocome.platform.basemanager.mapper.CameraGroupMapper;
import com.biocome.platform.basemanager.vo.camera.AddGroupVo;
import com.biocome.platform.common.biz.BaseBiz;
import com.biocome.platform.common.constant.CommonConstants;
import com.biocome.platform.common.msg.BaseResponse;
import com.biocome.platform.common.tree.UINode;
import com.biocome.platform.common.tree.UINodeType;
import com.biocome.platform.common.tree.UITree;
import com.biocome.platform.common.tree.UITreeUtil;
import com.biocome.platform.common.util.IdUtils;
import com.biocome.platform.common.util.ValidateUtils;
import com.biocome.platform.common.vo.UINodeVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 镜头分组
 *
 * @author zengqiang
 * @email zengqiang724@163.com
 * @date 2019-06-05 14:57:40
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CameraGroupBiz extends BaseBiz<CameraGroupMapper,CameraGroup> {
    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    DictDetailBiz dictDetailBiz;

    @CacheClear(key = "tree:camera_group")
    public BaseResponse insertEntity(CameraGroup entity) {
        //默认设为分支
        entity.setNodeType(UINodeType.BRANCH.getValue());
        int count = checkDupication(entity.getParentId(), entity.getName());
        if(count > 0){
            return new BaseResponse(CommonConstants.EX_OTHER_CODE, "同一级目录下不能有重复名称！");
        }
        super.insertSelective(entity);
        return new BaseResponse();
    }

    @CacheClear(key = "tree:camera_group")
    @Override
    public void deleteById(Object id) {
        super.deleteById(id);
    }

    @CacheClear(key = "tree:camera_group")
    @Override
    public void updateSelectiveById(CameraGroup entity) {
        super.updateSelectiveById(entity);
    }


    /**
     * 获取树形结构
     * @return
     */
    @Cache(key = "tree:camera_group")
    public UITree getTree() {
        try {
            List<CameraGroup> list = mapper.selectAll();
            if(ValidateUtils.isNotEmpty(list)){
                List<UINodeVo> vos = new ArrayList<UINodeVo>();
                for(CameraGroup entity : list){
                    UINodeVo vo = new UINodeVo();
                    vo.setId(String.valueOf(entity.getId()));
                    vo.setName(entity.getName());
                    vo.setParentId(String.valueOf(entity.getParentId()));
                    vo.setType(entity.getNodeType());
                    vos.add(vo);
                }
                UITree tree = UITreeUtil.buildTreeFromRoot(vos);
                return tree;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public UITree getSelectableTree() {
        UITree tree = dictDetailBiz.getInstitutionTree();
        List<UINodeVo> vos = mapper.selectableList();
        Map<String, List<UINode>> map = UITreeUtil.list2Map(tree,vos);
        tree.addNodes(map);
        return tree;
    }

    /**
     * 添加到分组
     * @param vos
     * @throws Exception
     */
    @CacheClear(key = "tree:camera_group")
    public void add2Group(List<AddGroupVo> vos) throws Exception{
        if(ValidateUtils.isNotEmpty(vos)){
            Map<String, Integer> idMap = new HashMap<String, Integer>();
            for(AddGroupVo vo : vos){
                if(ValidateUtils.isEmpty(vo.getFromType())){
                    continue;
                }
                int type = vo.getFromType();
                if(type == UINodeType.BRANCH.getValue()){
                    if(ValidateUtils.isNotEmpty(vo.getFromParentId())){
                        vo.setParentId(Integer.parseInt(vo.getToParentId()));
                    }
                    vo.setCameraId(Integer.parseInt(vo.getFromId().split(IdUtils.SEPERATOR_UNDERLINE)[1]));
                    mapper.add2Group(vo);
                    idMap.put(vo.getFromId(), vo.getId());
                }
            }
            List<AddGroupVo> voList = new ArrayList<AddGroupVo>();
            for(AddGroupVo vo : vos){
                if(ValidateUtils.isEmpty(vo.getFromType())){
                    continue;
                }
                int type = vo.getFromType();
                if(type == UINodeType.LEAF.getValue()){
                    vo.setCameraId(Integer.parseInt(vo.getFromParentId().split(IdUtils.SEPERATOR_UNDERLINE)[1]));
                    vo.setPipelineId(Integer.parseInt(vo.getFromId().split(IdUtils.SEPERATOR_UNDERLINE)[1]));
                    vo.setParentId(idMap.get(vo.getFromParentId()));
                    voList.add(vo);
                }
            }
            mapper.batchAdd2Group(voList);
        }
    }

    /**
     * 根据id批量删除
     */
    @CacheClear(key = "tree:camera_group")
    public void batchDeleteByIds(List<Integer> ids) throws Exception{
        mapper.deleteByIds(ids);
    }

    @CacheClear(key = "tree:camera_group")
    public BaseResponse rename(Integer id, String name) {
        if(ValidateUtils.isEmpty(name)){
            return new BaseResponse(CommonConstants.EX_OTHER_CODE, "名称不能为空！");
        }
        Integer parentId = selectById(id).getParentId();
        if(ValidateUtils.isNotEmpty(parentId)){
            int count = checkDupication(parentId, name);
            if(count == 1){
                return new BaseResponse();
            }
            if(count > 1){
                return new BaseResponse(CommonConstants.EX_OTHER_CODE, "同一级目录下不能有重复名称！");
            }
        }
        try {
            CameraGroup entity = new CameraGroup();
            entity.setId(id);
            entity.setName(name);
            insertEntity(entity);
            return new BaseResponse();
        }catch (Exception e){
            e.printStackTrace();
            log.info("重命名失败，错误信息：数据库错误！");
            return new BaseResponse(CommonConstants.EX_OTHER_CODE, "重命名失败，错误信息：数据库错误！");
        }
    }

    private int checkDupication(Integer parentId, String name) {
        return mapper.getDupilicationCount(parentId, name);
    }

    /***
     * 移除树及树下所有分支
     * @param id
     */
    @CacheClear(key = "tree:camera_group")
    public void removeGroup(Integer id) {
        UITree tree = getTree();
        List<String> subGroupIds = tree.findBranchById(String.valueOf(id));
        List<Integer> ids = new ArrayList<Integer>();
        if(ValidateUtils.isNotEmpty(subGroupIds)){
            for(String subId : subGroupIds){
                ids.add(Integer.parseInt(subId));
            }
        }
        mapper.deleteByIds(ids);
    }

}