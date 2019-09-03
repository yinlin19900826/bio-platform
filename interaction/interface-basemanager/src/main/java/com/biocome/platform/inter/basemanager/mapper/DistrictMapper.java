package com.biocome.platform.inter.basemanager.mapper;

import com.biocome.platform.inter.basemanager.vo.DistrictResp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName: DistrictMapper
 * @Author: shenlele
 * @Date: 2019/9/3 10:52
 * @Description:
 */
public interface DistrictMapper {

    /**
     * 根据父级编码查询行政区划信息
     *
     * @param code 编码
     * @return com.biocome.platform.inter.basemanager.vo.DistrictResp
     * @Author shenlele
     * @Date 2019/9/3 10:54
     */
    List<DistrictResp> selectDistrict(@Param("code") String code);
}
