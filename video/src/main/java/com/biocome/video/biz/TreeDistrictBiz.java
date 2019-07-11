package com.biocome.video.biz;

import com.biocome.platform.common.util.ValidateUtils;
import com.biocome.video.mapper.CameraMapper;
import com.biocome.video.mapper.TreeVideoMapper;
import com.biocome.video.mapper.VideoMapper;
import com.biocome.video.vo.CameraVo;
import com.biocome.video.vo.TreeVideoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: TreeDistrictBiz
 * @Author: yinlin
 * @Date: 2019/5/17 10:42
 * @Description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TreeDistrictBiz {

    @Autowired
    private VideoMapper videoMapper;
    @Autowired
    private CameraMapper cameraMapper;

    @Autowired
    private TreeVideoMapper treeVideoMapper;

    /**
     *
     *
     * @param parentcode
     * @return java.util.List<com.github.wxiaoqi.security.admin.vo.tree.TreeDistrictVO>
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
     * @return java.util.List<com.github.wxiaoqi.security.admin.vo.tree.TreeDistrictVO>
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
                                    //该返回中查询了楼栋所有的设备，如果countdevice为0，则表示没有设备，页面显示标志为无设备标志
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

}
