package com.biocome.platform.admin.biz;

import com.biocome.platform.admin.mapper.*;
import com.biocome.platform.admin.vo.camera.CameraVo;
import com.biocome.platform.admin.vo.tree.TreeDistrictVO;
import com.biocome.platform.admin.vo.tree.TreeVideoVO;
import com.biocome.platform.admin.mapper.*;
import com.biocome.platform.common.util.ValidateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: TreeDistrictBiz
 * @Author: shenlele
 * @Date: 2019/5/17 10:42
 * @Description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TreeDistrictBiz {

    @Autowired
    private BuildMapper buildMapper;
    @Autowired
    private EstateMapper estateMapper;
    @Autowired
    private VideoMapper videoMapper;
    @Autowired
    private CameraMapper cameraMapper;
    @Autowired
    private TreeDistrictMapper treeDistrictMapper;
    @Autowired
    private TreeVideoMapper treeVideoMapper;


    /**
     * 根据所传父级行政区划代码，查询当前区划下所有地区,组装树
     *
     * @param parentcode 父级行政区划代码
     * @return java.util.List<TreeDistrictVO>
     * @Author shenlele
     * @Date 2019/5/17 10:44
     */
    public List<TreeDistrictVO> getTreeDistrict(String parentcode) throws Exception {
        Integer type = null;
        //如果不传该值，默认为最高父节点
        if (ValidateUtils.isEmpty(parentcode)) {
            //如果传值为0代表查询所有
            type = 1;
            parentcode = "0";
        }
        List<TreeDistrictVO> list = treeDistrictMapper.selectTreeDistrict(type, parentcode);
        if (list != null && list.size() > 0) {
            getTreeAreas(list);
        }
        return list;
    }

    /**
     * 组装树
     *
     * @param list 父级列表
     * @return void
     * @Author shenlele
     * @Date 2019/5/17 10:49
     */
    private void getTreeAreas(List<TreeDistrictVO> list) throws Exception {
        if (ValidateUtils.isNotEmpty(list)) {
            List<TreeDistrictVO> allSubs = new ArrayList<>();
            for (TreeDistrictVO trd : list) {
                if (ValidateUtils.isNotEmpty(trd) && ValidateUtils.isNotEmpty(trd.getCode())) {
                    if (trd.getType() == 5) {
                        List<TreeDistrictVO> bs = estateMapper.selectByTree(trd.getCode());
                        for (TreeDistrictVO tree : bs) {
                            tree.setType(6);
                            //该返回中查询了楼栋所有的设备，如果countdevice为0，则表示没有设备，页面显示标志为无设备标志
                            List<TreeDistrictVO> bl = buildMapper.selectByTree(tree.getCode());
                            tree.setChildren(bl);
                        }
                        trd.setChildren(bs);
                        continue;
                    } else {
                        List<TreeDistrictVO> subs = treeDistrictMapper.selectTreeDistrict(trd.getType() + 1, trd.getCode());
                        allSubs.addAll(subs);
                        trd.setChildren(subs);
                    }
                }
            }

            if (ValidateUtils.isNotEmpty(allSubs)) {
                getTreeAreas(allSubs);
            }
        }
    }

    /**
     * 根据所传父级行政区划代码，查询当前区划下所有地区,组装树(不包括小区楼栋，也不包含楼栋门禁总数)
     *
     * @param parentcode
     * @return java.util.List<TreeDistrictVO>
     * @Author shenlele
     * @Date 2019/5/21 11:26
     */
    public List<TreeDistrictVO> getTree(String parentcode) throws Exception {
        Integer type = null;
        //如果不传该值，默认为最高父节点
        if (ValidateUtils.isEmpty(parentcode)) {
            //如果传值为0代表查询所有
            type = 1;
            parentcode = "0";
        }
        List<TreeDistrictVO> list = treeDistrictMapper.selectTree(type, parentcode);
        if (list != null && list.size() > 0) {
            getTreeDetails(list);
        }
        return list;
    }

    private void getTreeDetails(List<TreeDistrictVO> list) throws Exception {
        if (ValidateUtils.isNotEmpty(list)) {
            List<TreeDistrictVO> allSubs = new ArrayList<>();
            for (TreeDistrictVO trd : list) {
                if (ValidateUtils.isNotEmpty(trd) && ValidateUtils.isNotEmpty(trd.getCode())) {
                    List<TreeDistrictVO> subs = treeDistrictMapper.selectTree(trd.getType() + 1, trd.getCode());
                    allSubs.addAll(subs);
                    trd.setChildren(subs);
                }
            }
            if (ValidateUtils.isNotEmpty(allSubs)) {
                getTreeDetails(allSubs);
            }
        }
    }

    /**
     * 根据所传父级行政区划代码，查询当前区划下所有地区,组装树(不包括楼栋，也不包含楼栋门禁总数)
     *
     * @param parentcode
     * @return java.util.List<TreeDistrictVO>
     * @Author shenlele
     * @Date 2019/5/21 11:26
     */
    public List<TreeDistrictVO> getAdvertTree(String parentcode) throws Exception {
        Integer type = null;
        //如果不传该值，默认为最高父节点
        if (ValidateUtils.isEmpty(parentcode)) {
            //如果传值为0代表查询所有
            type = 4;
        }
        List<TreeDistrictVO> list = treeDistrictMapper.selectTree(type, parentcode);
        if (list != null && list.size() > 0) {
            getTreeAdvert(list);
        }
        return list;
    }

    private void getTreeAdvert(List<TreeDistrictVO> list) throws Exception {
        if (ValidateUtils.isNotEmpty(list)) {
            List<TreeDistrictVO> allSubs = new ArrayList<>();
            for (TreeDistrictVO trd : list) {
                if (ValidateUtils.isNotEmpty(trd) && ValidateUtils.isNotEmpty(trd.getCode())) {
                    if (trd.getType() == 5) {

                        List<TreeDistrictVO> bs = estateMapper.selectByTree(trd.getCode());
                        for (TreeDistrictVO tree : bs) {
                            tree.setType(6);
                            //该返回中查询了楼栋所有的设备，如果countdevice为0，则表示没有设备，页面显示标志为无设备标志
                            List<TreeDistrictVO> bl = buildMapper.selectByTree(tree.getCode());
                            tree.setChildren(bl);
                        }
                        trd.setChildren(bs);
                        continue;
                    } else {
                        List<TreeDistrictVO> subs = treeDistrictMapper.selectTree(trd.getType() + 1, trd.getCode());
                        allSubs.addAll(subs);
                        trd.setChildren(subs);
                    }
                }
            }
            if (ValidateUtils.isNotEmpty(allSubs)) {
                getTreeAdvert(allSubs);
            }
        }
    }
    /**
     *
     *
     * @param parentcode
     * @return java.util.List<TreeDistrictVO>
     * @Author yinlin
     * @Date 2019/5/31 11:26
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
     * @return java.util.List<TreeDistrictVO>
     * @Author yinlin
     * @Date 2019/6/3 11:26
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
