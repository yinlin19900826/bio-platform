package com.biocome.platform.basemanager.mapper;

import com.biocome.platform.basemanager.entity.Estate;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @ClassName: EstateMapper
 * @Author: shenlele
 * @Date: 2019/5/8 13:58
 * @Description:
 */
public interface EstateMapper extends Mapper<Estate> {

    /**
     * 根据小区编号或小区名称查询所有小区信息，查询所有时传参都为null
     *
     * @param estatename 小区名称
     * @param estatecode 小区编号
     * @return java.util.List<Estate>
     * @Author shenlele
     * @Date 2019/5/8 13:59
     */
    List<Estate> selectByAttribute(@Param("estatename") String estatename, @Param("estatecode") String estatecode);


    /**
     * 根据ID删除
     *
     * @param list 主键列表
     * @return
     * @Author shenlele
     * @Date 2019/5/8 13:59
     */
    void deleteEstate(@Param("list") List<Integer> list);

}
