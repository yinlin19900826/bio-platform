package com.biocome.platform.wechatapplet.vo.device;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author hxy
 * @date 2019/7/30 13:42
 */
@ApiModel(value = "获取设备详情")
public class DeviceDetailResp {
    @ApiModelProperty(value = "设备编号")
    private String sn;
    @ApiModelProperty(value = "设备名称")
    private String deviceName;

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }
}
