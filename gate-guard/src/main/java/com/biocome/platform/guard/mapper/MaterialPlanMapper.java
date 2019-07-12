package com.biocome.platform.guard.mapper;

import com.biocome.platform.guard.entity.MaterialPlan;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 广告素材计划关联表
 * 
 * @author hxy
 * @email 402795620@qq.com
 * @date 2019-05-31 18:07:14
 */
public interface MaterialPlanMapper extends Mapper<MaterialPlan> {
    /**
     * 插入素材计划关联信息列表
     *
     * @param list 素材计划关联信息列表
     * @return int
     * @Author shenlele
     * @Date 2019/7/12 9:14
     */
    int insertListMaterialPlan(@Param("list") List<MaterialPlan> list);

    /**
     * 根据计划ID列表删除数据
     *
     * @param advertPlanIds 计划ID列表
     * @return int
     * @Author shenlele
     * @Date 2019/7/12 9:15
     */
    int deleteByAdvertPlanIds(@Param("list") List<Integer> advertPlanIds);
}
