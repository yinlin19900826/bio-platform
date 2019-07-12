package com.biocome.platform.basemanager.mapper;

import com.biocome.platform.basemanager.entity.Build;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @ClassName: BuildMapper
 * @Author: shenlele
 * @Date: 2019/5/8 11:57
 * @Description:
 */
public interface BuildMapper extends Mapper<Build> {

    /**
     * 根据楼栋名称或楼栋地址查询所有楼栋信息，查询所有时传参都为null
     *
     * @param buildaddress 楼栋地址
     * @param buildname    楼栋名称
     * @return java.util.List<Build>
     * @Author shenlele
     * @Date 2019/5/8 13:53
     */
    List<Build> selectByAttribute(@Param("buildaddress") String buildaddress, @Param("buildname") String buildname);


    /**
     * 根据ID删除
     *
     * @param list 主键编号列表
     * @return
     * @Author shenlele
     * @Date 2019/5/8 13:56
     */
    void deleteBuild(@Param("list") List<Integer> list);

}