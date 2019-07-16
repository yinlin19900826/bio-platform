package com.biocome.platform.guard.mapper;

import com.biocome.platform.guard.entity.AdvertPlan;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.Date;
import java.util.List;

/**
 * @author hxy
 * @date 2019/5/31 09:56
 */
public interface AdvertPlanMapper extends Mapper<AdvertPlan> {
    /**
     * 查询广告计划
     *
     * @param id id
     * @param planname 计划名称
     * @param isaudit 审核状态
     * @param starttime 开始时间
     * @param endtime 结束时间
     * @return java.util.List<AdvertPlan>
     * @Author shenlele
     * @Date 2019/7/12 9:20
     */
    List<AdvertPlan> getAdvertPlanList(@Param("id") Integer id, @Param("planname") String planname, @Param("isaudit") String isaudit, @Param("starttime") Date starttime, @Param("endtime") Date endtime);
    
    /** 
     * 删除广告计划
     * 
     * @param advertPlanIds 计划id列表
     * @return int
     * @Author shenlele
     * @Date 2019/7/12 9:21
     */
    int deleteAdvertPlan(@Param("list") List<Integer> advertPlanIds);

    /**
     * 插入广告计划
     *
     * @param plan 广告计划
     * @return int
     * @Author shenlele
     * @Date 2019/7/12 9:22
     */
    int insertReturnId(@Param("plan") AdvertPlan plan);
}
