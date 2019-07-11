package com.biocome.platform.guard.mapper;

import com.biocome.platform.guard.vo.showmanage.HouseVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName: HouseMapper
 * @Author: shenlele
 * @Date: 2019/5/8 14:01
 * @Description:
 */
public interface HouseMapper {


    /**
     * 根据楼栋编号查询房间信息
     *
     * @param buildcode
     * @return java.util.List<HouseVo>
     * @Author shenlele
     * @Date 2019/5/20 15:14
     */
    List<HouseVo> selectHouseVo(@Param("buildcode") String buildcode);

}
