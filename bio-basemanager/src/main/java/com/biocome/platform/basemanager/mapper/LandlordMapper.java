package com.biocome.platform.basemanager.mapper;

import com.biocome.platform.basemanager.entity.Landlord;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @ClassName: LandlordMapper
 * @Author: shenlele
 * @Date: 2019/5/8 14:04
 * @Description:
 */
public interface LandlordMapper extends Mapper<Landlord> {

    /**
     * 根据姓名，手机号，证件号码，房东类型查询，查询全部时传参为null
     *
     * @param username     姓名
     * @param tel          手机号
     * @param papersnum    证件号
     * @param landlordtype 房东类型
     * @return java.util.List<Landlord>
     * @Author shenlele
     * @Date 2019/5/8 14:04
     */
    List<Landlord> selectByAttribute(@Param("username") String username, @Param("tel") String tel, @Param("papersnum") String papersnum, @Param("landlordtype") Integer landlordtype);


    /**
     * 根据ID删除
     *
     * @param list 主键
     * @return
     * @Author shenlele
     * @Date 2019/5/8 14:05
     */
    void deleteLandlord(@Param("list") List<Integer> list);

}
