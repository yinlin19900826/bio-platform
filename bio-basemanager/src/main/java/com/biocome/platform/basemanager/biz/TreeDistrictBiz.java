package com.biocome.platform.basemanager.biz;

import com.biocome.platform.basemanager.mapper.TreeDistrictMapper;
import com.biocome.platform.common.util.ValidateUtils;
import com.biocome.platform.inter.basemanager.vo.tree.TreeDistrictVO;
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
    private TreeDistrictMapper treeDistrictMapper;

    /**
     * 根据所传父级行政区划代码，查询当前区划下所有地区,组装树(不包括小区楼栋，也不包含楼栋门禁总数)
     *
     * @param parentcode 父级代码
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
}
