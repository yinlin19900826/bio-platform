package com.biocome.platform.guard.biz;

import com.biocome.platform.common.util.ValidateUtils;
import com.biocome.platform.guard.mapper.EstateMapper;
import com.biocome.platform.guard.mapper.TreeDistrictMapper;
import com.biocome.platform.inter.basemanager.mapper.BuildMapper;
import com.biocome.platform.inter.basemanager.vo.tree.TreeDistrictVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: TreeDistrictBiz
 * @Author: shenlele
 * @Date: 2019/7/11 10:42
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
    private TreeDistrictMapper treeDistrictMapper;


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
}
