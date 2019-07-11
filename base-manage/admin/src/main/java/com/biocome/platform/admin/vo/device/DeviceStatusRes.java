package com.biocome.platform.admin.vo.device;

import com.biocome.platform.common.msg.auth.BaseRpcResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: DeviceStatusRes
 * @Author: shenlele
 * @Date: 2019/5/15 17:48
 * @Description:
 */
@ApiModel(value = "请求设备状态返回实体类")
public class DeviceStatusRes extends BaseRpcResponse {

    @ApiModelProperty(value = "设备编号")
    private String sn;
    @ApiModelProperty(value = "设备是否在线(1在线)")
    private String facilitystatus;
    @ApiModelProperty(value = "摄像头是否在线（1在线）")
    private String camerastatus;
    @ApiModelProperty(value = "显示屏状态（1正常）预留")
    private String screenstatus;

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getFacilitystatus() {
        return facilitystatus;
    }

    public void setFacilitystatus(String facilitystatus) {
        this.facilitystatus = facilitystatus;
    }

    public String getCamerastatus() {
        return camerastatus;
    }

    public void setCamerastatus(String camerastatus) {
        this.camerastatus = camerastatus;
    }

    public String getScreenstatus() {
        return screenstatus;
    }

    public void setScreenstatus(String screenstatus) {
        this.screenstatus = screenstatus;
    }
}
