package com.biocome.platform.guard.mapper;

import com.biocome.platform.guard.vo.device.DeviceInfoResp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: hxy
 * @Date:2019/5/7 11:29
 * @Description:
 */
public interface DeviceMapper {

    /**
     * 根据device查询返回相关实体类
     *
     * @param device
     * @return java.util.List<DeviceInfoResp>
     * @Author shenlele
     * @Date 2019/5/28 10:28
     */
    List<DeviceInfoResp> selectByDevice(@Param("device") DeviceInfoResp device);
}
