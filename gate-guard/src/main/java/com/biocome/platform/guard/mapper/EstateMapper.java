package com.biocome.platform.guard.mapper;

import com.biocome.platform.guard.entity.Estate;
import com.biocome.platform.guard.vo.tree.TreeDistrictVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName: EstateMapper
 * @Author: shenlele
 * @Date: 2019/5/8 13:58
 * @Description:
 */
public interface EstateMapper {

    /**
     * 根据所属行政区划代码查询所有小区，构建树
     *
     * @param areacode 所属行政区划代码
     * @return java.util.List<TreeDistrictVO>
     * @Author shenlele
     * @Date 2019/5/20 11:40
     */
    List<TreeDistrictVO> selectByTree(@Param("areacode") String areacode);

    /**
     * 根据小区编号列表查询小区信息
     *
     * @param estateCodes 小区编号列表
     * @return java.util.List<Estate>
     * @Author shenlele
     * @Date 2019/7/12 9:14
     */
    List<Estate> queryEstateByEstatecode(@Param("list") List<String> estateCodes);

}
