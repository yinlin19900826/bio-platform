package com.biocome.platform.admin.mapper;

import com.biocome.platform.admin.entity.House;
import com.biocome.platform.admin.vo.showmanage.HouseVo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @ClassName: HouseMapper
 * @Author: shenlele
 * @Date: 2019/5/8 14:01
 * @Description:
 */
public interface HouseMapper extends Mapper<House> {

    /**
     * 根据房屋名称，房屋地址查询房屋信息，查询所有时传参都为null
     *
     * @param housename 房屋名称
     * @param housesite 房屋地址
     * @return java.util.List<House>
     * @Author shenlele
     * @Date 2019/5/8 14:02
     */
    List<House> selectByAttribute(@Param("housename") String housename, @Param("housesite") String housesite);


    /**
     * 根据ID删除
     *
     * @param list 主键
     * @return int
     * @Author shenlele
     * @Date 2019/5/8 14:03
     */
    int deleteHouse(@Param("list") List<Integer> list);

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
