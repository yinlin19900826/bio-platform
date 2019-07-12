package com.biocome.platform.basemanager.vo.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("管理员信息")
public class SimpleAdminVo {
    @ApiModelProperty("管理员编码")
    private String usercode;
    @ApiModelProperty("管理员姓名")
    private String username;
    @ApiModelProperty("管理员电话")
    private String phone;
    @ApiModelProperty("管理员类型 批量下发、注销作为参数时不用传递")
    private String adminType;
    @ApiModelProperty("管理员地址 批量下发、注销作为参数时不用传递")
    private String address;

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
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

    public String getAdminType() {
        return adminType;
    }

    public void setAdminType(String adminType) {
        this.adminType = adminType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
