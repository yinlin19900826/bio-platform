package com.biocome.platform.guard.vo.showmanage;

import com.biocome.platform.guard.vo.device.DeviceStatusRes;
import com.biocome.platform.guard.vo.device.DoorStatusRes;
import com.biocome.platform.inter.basemanager.vo.device.DeviceInfoResp;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: DeviceAndStatusVo
 * @Author: shenlele
 * @Date: 2019/5/20 15:09
 * @Description:
 */
@ApiModel(value = "门禁管理展示设备信息，门禁机状态，门信息实体类")
public class DeviceAndStatusVo {

    /**
     * 设备信息
     */
    @ApiModelProperty(value = "设备信息")
    private DeviceInfoResp device;
    /**
     * 门禁机状态信息
     */
    @ApiModelProperty(value = "门禁机状态信息")
    private DeviceStatusRes deviceStatusRes;
    /**
     * 门信息
     */
    @ApiModelProperty(value = "门信息")
    private DoorStatusRes doorStatusRes;

    public DeviceInfoResp getDevice() {
        return device;
    }

    public void setDevice(DeviceInfoResp device) {
        this.device = device;
    }

    public DeviceStatusRes getDeviceStatusRes() {
        return deviceStatusRes;
    }

    public void setDeviceStatusRes(DeviceStatusRes deviceStatusRes) {
        this.deviceStatusRes = deviceStatusRes;
    }

    public DoorStatusRes getDoorStatusRes() {
        return doorStatusRes;
    }

    public void setDoorStatusRes(DoorStatusRes doorStatusRes) {
        this.doorStatusRes = doorStatusRes;
    }
}
