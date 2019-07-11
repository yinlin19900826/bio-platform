package com.biocome.platform.admin.vo.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("楼栋信息")
public class AdminBuildingVo {
    @ApiModelProperty(value = "绑定id,请求时没有则不传")
    private Integer id;
    @ApiModelProperty(value = "楼栋编码")
    private String buildCode;
    @ApiModelProperty(value = "楼栋名称")
    private String buildName;
    @ApiModelProperty(value = "社区编码")
    private String comunityCode;
    @ApiModelProperty(value = "社区名称")
    private String comunityName;
    @ApiModelProperty(value = "管理员姓名")
    private String username;
    @ApiModelProperty(value = "管理员电话")
    private String phone;
    @ApiModelProperty(value = "是否默认电话开门 0 是 1 否")
    private Integer phoneOpen;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBuildCode() {
        return buildCode;
    }

    public void setBuildCode(String buildCode) {
        this.buildCode = buildCode;
    }

    public String getBuildName() {
        return buildName;
    }

    public void setBuildName(String buildName) {
        this.buildName = buildName;
    }

    public String getComunityCode() {
        return comunityCode;
    }

    public void setComunityCode(String comunityCode) {
        this.comunityCode = comunityCode;
    }

    public String getComunityName() {
        return comunityName;
    }

    public void setComunityName(String comunityName) {
        this.comunityName = comunityName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getPhoneOpen() {
        return phoneOpen;
    }

    public void setPhoneOpen(Integer phoneOpen) {
        this.phoneOpen = phoneOpen;
    }
}
