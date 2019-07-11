package com.biocome.platform.guard.mapper;

import com.biocome.platform.guard.vo.tree.TreeDistrictVO;
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
     * 根据所传父级行政区划代码，查询当前区划下所有地区,包括小区，楼栋
     *
     * @param type       用户等级
     * @param parentcode 父级行政区划代码
     * @return java.util.List<TreeDistrictVO>
     * @Author shenlele
     * @Date 2019/5/17 10:41
     */
    List<TreeDistrictVO> selectTreeDistrict(@Param("type") Integer type, @Param("parentcode") String parentcode);

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
