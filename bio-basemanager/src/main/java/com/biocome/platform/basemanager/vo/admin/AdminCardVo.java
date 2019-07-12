package com.biocome.platform.basemanager.vo.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("管理员卡信息")
public class AdminCardVo {
    @ApiModelProperty("管理员编码")
    private String usercode;
    @ApiModelProperty("管理员名称")
    private String username;
    @ApiModelProperty("管理员号码")
    private String tel;
    @ApiModelProperty("管理员地址")
    private String address;
    @ApiModelProperty("管理员类型")
    private String adminType;
    @ApiModelProperty("门禁卡号")
    private String cardNo;
    @ApiModelProperty("证件号码")
    private String certNo;
    @ApiModelProperty("社区名称")
    private String communityName;
    @ApiModelProperty("门禁卡状态")
    private String cardStatus;
    @ApiModelProperty("类型")
    private String cardkind;
    @ApiModelProperty("开卡时间")
    private String createtime;

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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAdminType() {
        return adminType;
    }

    public void setAdminType(String adminType) {
        this.adminType = adminType;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCertNo() {
        return certNo;
    }

    public void setCertNo(String certNo) {
        this.certNo = certNo;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public String getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(String cardStatus) {
        this.cardStatus = cardStatus;
    }

    public String getCardkind() {
        return cardkind;
    }

    public void setCardkind(String cardkind) {
        this.cardkind = cardkind;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
}
