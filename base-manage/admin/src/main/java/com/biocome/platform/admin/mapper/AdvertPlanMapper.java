package com.biocome.platform.admin.mapper;

import com.biocome.platform.admin.entity.AdvertPlan;
import com.biocome.platform.admin.vo.advert.AdvertAddListRpcResp;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.Date;
import java.util.List;

/**
 * @author hxy
 * @date 2019/5/31 09:56
 */
public interface AdvertPlanMapper extends Mapper<AdvertPlan> {
    List<AdvertPlan> getAdvertPlanList(@Param("id") Integer id,@Param("planname") String planname,@Param("isaudit") String isaudit,@Param("starttime") Date starttime,@Param("endtime") Date endtime);

    int deleteAdvertPlan(@Param("list")List<Integer> advertPlanIds);

    int insertAdvertResultMapper(@Param("list")List<AdvertAddListRpcResp> advertAddListRpcResps);

    int insertReturnId(@Param("plan")AdvertPlan plan);
}
