package com.biocome.platform.inter.basemanager.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author hxy
 * @date 2019/7/30 16:52
 */
@ApiModel(value = "访客记录模型")
@Table(name = "base_visitor_record")
public class VisitorRecord {
    @ApiModelProperty(value = "id")
    @Id
    private String id;
    @ApiModelProperty(value="用户编号")
    @Column(name = "usercode")
    private String usercode;
    @ApiModelProperty(value="访客电话")
    @Column(name = "visitor_phone")
    private String visitorPhone;
    @ApiModelProperty(value="设备编号")
    @Column(name = "sn")
    private String sn;
    @ApiModelProperty(value="设备名称")
    @Column(name = "device_name")
    private String deviceName;
    @ApiModelProperty(value="动态密码")
    @Column(name = "password")
    private String password;
    @ApiModelProperty(value="创建时间")
    @Column(name = "createtime")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date createtime;
    @ApiModelProperty(value="过期时间")
    @Column(name = "overduetime")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date overduetime;
    @ApiModelProperty(value="记录状态")
    @Column(name = "status")
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getVisitorPhone() {
        return visitorPhone;
    }

    public void setVisitorPhone(String visitorPhone) {
        this.visitorPhone = visitorPhone;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getOverduetime() {
        return overduetime;
    }

    public void setOverduetime(Date overduetime) {
        this.overduetime = overduetime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
