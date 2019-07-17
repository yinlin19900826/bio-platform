package com.biocome.platform.basemanager.mapper;

import com.biocome.platform.inter.basemanager.vo.tree.TreeDistrictVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName: TreeDistrictMapper
 * @Author: shenlele
 * @Date: 2019/5/17 10:39
 * @Description:
 */
public interface TreeDistrictMapper {

    /**
     * 根据所传父级行政区划代码，查询当前区划下所有地区不包括小区楼栋，也不包含楼栋门禁总数
     *
     * @param type
     * @param parentcode
     * @return java.util.List<TreeDistrictVO>
     * @Author shenlele
     * @Date 2019/5/21 11:20
     */
    List<TreeDistrictVO> selectTree(@Param("type") Integer type, @Param("parentcode") String parentcode);
}
