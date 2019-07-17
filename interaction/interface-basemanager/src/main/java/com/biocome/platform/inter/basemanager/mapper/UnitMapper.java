package com.biocome.platform.inter.basemanager.mapper;

import com.biocome.platform.inter.basemanager.entity.Unit;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @ClassName: UnitMapper
 * @Author: shenlele
 * @Date: 2019/5/8 14:07
 * @Description:
 */
public interface UnitMapper extends Mapper<Unit> {

    /**
     * 根据小区名称，楼栋名称，单元名称查询所有单元信息，查询所有时传参都为null
     *
     * @param unitname   小区名称
     * @param buildname  楼栋名称
     * @param estatename 单元名称
     * @return java.util.List<Unit>
     * @Author shenlele
     * @Date 2019/5/8 14:07
     */
    List<Unit> selectByAttribute(@Param("unitname") String unitname, @Param("buildname") String buildname, @Param("estatename") String estatename);

    /**
     * 根据ID删除
     *
     * @param list 主键编号
     * @return int
     * @Author shenlele
     * @Date 2019/5/8 13:56
     */
    void deleteUnit(@Param("list") List<Integer> list);

}
