package com.biocome.platform.wechatapplet.mapper;

import com.biocome.platform.wechatapplet.vo.device.DeviceDetailResp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author hxy
 * @date 2019/7/30 14:02
 */
public interface DeviceDetailMapper {
    List<DeviceDetailResp> getDeviceByBuildcode(@Param("buildcode") String buildcode);

    String getDeviceNameBySn(@Param("sn") String sn);
}
