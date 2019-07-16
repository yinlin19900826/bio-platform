package com.biocome.platform.guard.vo.card;

import io.swagger.annotations.ApiParam;

public class AddCardParam {
    @ApiParam(value = "用户编码")
    private String usercode;
    @ApiParam(value = "管理员编码", required = true)
    private String admincode;
    @ApiParam(value = "卡类型（1、普通卡 2、管理员卡 3、房东卡 4、管理员通卡", required = true)
    private int cardtype;
    @ApiParam(value = "卡种类 (1、IC卡 2、CPU卡 3、深圳通卡)", required = true)
    private int cardkind;
    @ApiParam(value = "卡号", required = true)
    private String cardNo;
    @ApiParam(value = "逻辑卡号")
    private String logicCardno;
    @ApiParam(value = "电话号码")
    private String phone;
    @ApiParam(value = "指定楼栋编码 租户时必填")
    private String buildcode;
    @ApiParam(value = "指定房间编码 租户时必填")
    private String housecode;

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getAdmincode() {
        return admincode;
    }

    public void setAdmincode(String admincode) {
        this.admincode = admincode;
    }

    public int getCardtype() {
        return cardtype;
    }

    public void setCardtype(int cardtype) {
        this.cardtype = cardtype;
    }

    public int getCardkind() {
        return cardkind;
    }

    public void setCardkind(int cardkind) {
        this.cardkind = cardkind;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getLogicCardno() {
        return logicCardno;
    }

    public void setLogicCardno(String logicCardno) {
        this.logicCardno = logicCardno;
    }

    public String getBuildcode() {
        return buildcode;
    }

    public void setBuildcode(String buildcode) {
        this.buildcode = buildcode;
    }

    public String getHousecode() {
        return housecode;
    }

    public void setHousecode(String housecode) {
        this.housecode = housecode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
