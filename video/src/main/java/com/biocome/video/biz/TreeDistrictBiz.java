package com.biocome.video.biz;

import com.biocome.platform.common.biz.BaseBiz;
import com.biocome.platform.common.util.ValidateUtils;
import com.biocome.video.entity.CameraGroup;
import com.biocome.video.mapper.CameraGroupMapper;
import com.biocome.video.mapper.CameraMapper;
import com.biocome.video.mapper.TreeVideoMapper;
import com.biocome.video.mapper.VideoMapper;
import com.biocome.video.vo.*;
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
 * @ClassName: TreeDistrictBiz
 * @Author: yinlin
 * @Date: 2019/5/17 10:42
 * @Description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TreeDistrictBiz extends BaseBiz<CameraGroupMapper,CameraGroup> {

    private static Logger log = LoggerFactory.getLogger(TreeDistrictBiz.class);
    @Autowired
    private VideoMapper videoMapper;
    @Autowired
    private CameraMapper cameraMapper;

    @Autowired
    private TreeVideoMapper treeVideoMapper;

    @Autowired
    private CameraGroupMapper cameraGroupMapper;

    /**
     *
     *
     * @param parentcode
     * @return java.util.List<com.github.wxiaoqi.security.admin.camera.tree.TreeDistrictVO>
     * @Author yinlin
     * @Date 2019/5/31 11:26
     */
    /**
     *
     * @param parentcode
     * @return
     * @throws Exception
     */
    public List<TreeVideoVO> getVideoTree(String parentcode) throws Exception {
        Integer type = null;
        //如果不传该值，默认为最高父节点
        if (ValidateUtils.isEmpty(parentcode)) {
            //如果传值为0代表查询所有
            type = 1;
            parentcode = "0";
        }
//        List<TreeDistrictVO> list = treeDistrictMapper.selectTree(type, parentcode);
        List<TreeVideoVO> list = treeVideoMapper.selectTreeVideo(type, parentcode);
        if (list != null && list.size() > 0) {
            getTreeVideo(list);
        }
        return list;
    }
    /**
     *
     *
     * @param
     * @return java.util.List<com.github.wxiaoqi.security.admin.camera.tree.TreeDistrictVO>
     * @Author yinlin
     * @Date 2019/6/3 11:26
     */
    /**
     *
     * @param list
     * @throws Exception
     */
    private void getTreeVideo(List<TreeVideoVO> list) throws Exception {
        if (ValidateUtils.isNotEmpty(list)) {
            List<TreeVideoVO> allSubs = new ArrayList<>();
            for (TreeVideoVO trd : list) {
                if (ValidateUtils.isNotEmpty(trd) && ValidateUtils.isNotEmpty(trd.getCode())) {
                    if (trd.getType() == 5) {
                        List<TreeVideoVO> bs = videoMapper.selectsixTree(trd.getType()+1,trd.getCode());

                        for (TreeVideoVO tree : bs) {
                            List<TreeVideoVO> subs = videoMapper.selectTree(tree.getType() + 1, tree.getCode());

                            for(TreeVideoVO cameratree : subs){

                                    List<CameraVo> bl = cameraMapper.selectByTree(cameratree.getCode());
                                    //allSubs.addAll(bl);
                                    cameratree.setChildren(bl);

                                }

                            tree.setChildren(subs);
                        }
                        trd.setChildren(bs);
                        continue;
                    } else {
                        //int i = 0;
                        List<TreeVideoVO> subs1 = treeVideoMapper.selectTreeVideo(trd.getType() + 1, trd.getCode());
                        List<TreeVideoVO> subs3 = new ArrayList<>();
                        for (TreeVideoVO tree1 : subs1) {
                            List<TreeVideoVO> subs2  = treeVideoMapper.selectAnotherTreeVideo(tree1.getType(), tree1.getCode());
                            allSubs.addAll(subs2);
                            subs3.addAll(subs2);
                            trd.setChildren(subs3);

                        }

                        //i++;

                    }
                }
            }
            if (ValidateUtils.isNotEmpty(allSubs)) {
                getTreeVideo(allSubs);
            }
        }
    }


    /**
     * 获取树形结构
     * @return
     */
    //@Cache(key = "tree:camera_group")
    public UITree getTree() {
        try {
            List<CameraGroup> list = mapper.selectAll();
            if(ValidateUtils.isNotEmpty(list)){
                List<UINodeVo> vos = new ArrayList<UINodeVo>();
                for(CameraGroup entity : list){
                    UINodeVo vo = new UINodeVo();
                    vo.setId(entity.getId());
                    vo.setName(entity.getName());
                    vo.setParentId(entity.getParentId());
                    vo.setType(entity.getNodeType());
                    vo.setCameraId(entity.getCameraId());
                    vo.setPipeline_id(entity.getPipelineId());
                    vos.add(vo);
                }
                UITree tree = buildTreeFromRoot(vos);
                return tree;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 从根节点创建树
     * @return
     */
    public  UITree buildTreeFromRoot(List<UINodeVo> list){
        UITree tree = new UITree();
        if(ValidateUtils.isNotEmpty(list)){
            //将列表改成应映射结构
            Map<Integer, List<UINode>> map = new HashMap<Integer, List<UINode>>();
            List<UINode> lst = new ArrayList<>();
            for(UINodeVo vo : list){
                UINode node = new UINode();
                node.setId(vo.getId());
                List<Integer> Ids = cameraGroupMapper.getAllChildIds(vo.getId());
                int sumAllOnLineCameras = 0;
                int sumAllCameras = 0;
                for(Integer id :Ids){
                    int countOnLineCameraAmount = cameraGroupMapper.getOnLineCameraAmount(id);
                    int countCameraAmount = cameraGroupMapper.getCameraAmount(id);
                    if(countOnLineCameraAmount!=0 ){
                        sumAllOnLineCameras+=countOnLineCameraAmount;
                    }
                    if(countCameraAmount!=0 ){
                        sumAllCameras+=countCameraAmount;
                    }
                }
                node.setCountlivevideo(sumAllOnLineCameras);
                node.setCountcamera(sumAllCameras);
                node.setName(vo.getName());
                node.setAttach(vo.getAttach());
                node.setType(vo.getType());
                node.setCameraId(vo.getCameraId());
                node.setPipeline_id(vo.getPipeline_id());
                if(ValidateUtils.isNotEmpty(vo.getParentId())){
                    node.setParentId(vo.getParentId());
                }
                Integer parentId = vo.getParentId();
                if(ValidateUtils.isNotEmpty(parentId)){
                    List<UINode> nodeList = map.get(parentId);
                    if(ValidateUtils.isEmpty(nodeList)){
                        nodeList = new ArrayList<UINode>();
                        map.put(parentId, nodeList);
                    }
                    nodeList.add(node);
                }else{
                    lst.add(node);
                }
            }
            if(lst.size() != 1){
                log.info("错误的根节点数，根节点数量："+lst.size());
            }
            int level = 1;
            UINode root = lst.get(0);
            setChildList(root, level, map);
            tree.setRoot(root);
        }
        return tree;
    }

    /**
     * 迭代设置子节点
     * @param parent
     * @param level
     * @param map
     */
    public  void setChildList(UINode parent, int level, Map<Integer, List<UINode>> map) {
        parent.setLevel(level);
        // List<String> subGroupIds = tree.findBranchById(String.valueOf(vo.getId()));
        List<UINode> childList = map.get(parent.getId());
        if(ValidateUtils.isNotEmpty(childList)){
            if(ValidateUtils.isEmpty(parent.getChildList())){
                parent.setChildList(new ArrayList<UINode>());
            }
            parent.getChildList().addAll(childList);
            if(ValidateUtils.isNotEmpty(childList)){
                for(UINode node : childList){
                    if(level == 4){

                        List<CameraVo> videoDevice = cameraMapper.getVideoDevice(node.getCameraId());
                        node.setChildList(videoDevice);
                    }
                    setChildList(node, level+1, map);
                }
            }
        }
    }

}
