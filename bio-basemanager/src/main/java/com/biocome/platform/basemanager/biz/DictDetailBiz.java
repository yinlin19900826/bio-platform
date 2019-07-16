package com.biocome.platform.basemanager.biz;

import com.ace.cache.annotation.Cache;
import com.biocome.platform.basemanager.entity.DictDetail;
import com.biocome.platform.basemanager.mapper.DictDetailMapper;
import com.biocome.platform.common.biz.BaseBiz;
import com.biocome.platform.common.constant.CommonConstants;
import com.biocome.platform.common.msg.TableResultResponse;
import com.biocome.platform.common.tree.UITree;
import com.biocome.platform.common.tree.UITreeUtil;
import com.biocome.platform.common.vo.UINodeVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zengqiang
 * @email zengqiang724@163.com
 * @date 2019-06-06 09:39:45
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DictDetailBiz extends BaseBiz<DictDetailMapper, DictDetail> {
    Logger log = LoggerFactory.getLogger(this.getClass());

    public TableResultResponse<DictDetail> getListByName(String name) {
        try {
            List<DictDetail> list = selectListByName(name);
            return new TableResultResponse<>(list.size(), list);
        }catch (Exception e){
            e.printStackTrace();
            log.info(e.getMessage());
            return new TableResultResponse<>(CommonConstants.EX_OTHER_CODE, "查询字典失败，错误信息：数据库错误！");
        }
    }

    /**
     * 根据名称查询字典
     * @param name
     * @return
     */
    public List<DictDetail> selectListByName(String name){
        return mapper.selectListByName(name);
    }

    /**
     * 查询所有机构
     * @return
     */

    public List<DictDetail> getInstitutionList() {
        return selectListByName("机构");
    }

    @Cache(key = "tree:institution")
    public UITree getInstitutionTree() {
        List<UINodeVo> vos = new ArrayList<UINodeVo>();
        List<DictDetail> institutionList = getInstitutionList();
        for(DictDetail dict: institutionList){
            UINodeVo vo = new UINodeVo();
            vo.setId(dict.getDictKey());
            vo.setParentId(dict.getParentKey());
            vo.setName(dict.getDictValue());
            vos.add(vo);
        }
        return UITreeUtil.buildTreeFromRoot(vos);
    }
}