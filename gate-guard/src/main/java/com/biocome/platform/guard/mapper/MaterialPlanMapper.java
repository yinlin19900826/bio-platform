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
    int insertListMaterialPlan(@Param("list") List<MaterialPlan> list);

    int deleteByAdvertPlanIds(@Param("list") List<Integer> advertPlanIds);
}
