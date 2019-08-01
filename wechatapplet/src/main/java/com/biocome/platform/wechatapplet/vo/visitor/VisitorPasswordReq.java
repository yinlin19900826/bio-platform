package com.biocome.platform.wechatapplet.vo.visitor;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author hxy
 * @date 2019/7/30 18:10
 */
@ApiModel(value = "请求开门密码")
public class VisitorPasswordReq {
    @ApiModelProperty(value = "设备编号")
    private String sn;
    @ApiModelProperty(value = "设备名称")
    private String deviceName;
    @ApiModelProperty(value = "访客电话")
    private String phone;
    @ApiModelProperty(value = "访客备注")
    private String remark;
    @ApiModelProperty(value = "用户类型 0 租户 1 其他")
    private String usertype;
    @ApiModelProperty(value = "用户编号")
    private String usercode;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }
}
