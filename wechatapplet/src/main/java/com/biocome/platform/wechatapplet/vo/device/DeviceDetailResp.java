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
    @ApiModelProperty(value = "蓝牙mac")
    private String bluetoothmac;
    @ApiModelProperty(value = "蓝牙摘要")
    private String devdigest;

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

    public String getBluetoothmac() {
        return bluetoothmac;
    }

    public void setBluetoothmac(String bluetoothmac) {
        this.bluetoothmac = bluetoothmac;
    }

    public String getDevdigest() {
        return devdigest;
    }

    public void setDevdigest(String devdigest) {
        this.devdigest = devdigest;
    }
}
