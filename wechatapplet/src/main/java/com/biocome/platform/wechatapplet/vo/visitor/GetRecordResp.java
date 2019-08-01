package com.biocome.platform.wechatapplet.vo.visitor;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author hxy
 * @date 2019/7/31 13:50
 */
@ApiModel(value = "获取记录响应")
public class GetRecordResp {
    @ApiModelProperty(value = "创建时间")
    private String createtime;
    @ApiModelProperty(value = "失效时间")
    private String overduetime;
    @ApiModelProperty(value = "访客电话")
    private String phone;
    @ApiModelProperty(value = "设备名称")
    private String deviceName;
    @ApiModelProperty(value = "记录状态")
    private String statusStr;

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getOverduetime() {
        return overduetime;
    }

    public void setOverduetime(String overduetime) {
        this.overduetime = overduetime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getStatusStr() {
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }
}
