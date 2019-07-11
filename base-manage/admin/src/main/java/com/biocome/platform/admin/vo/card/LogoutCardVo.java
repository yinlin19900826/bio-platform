package com.biocome.platform.admin.vo.card;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author hxy
 * @date 2019/5/15 13:59
 */
@ApiModel(value = "注销卡VO")
public class LogoutCardVo {
    /**
     * 请求token
     */
    @ApiModelProperty(value="请求token")
    private String token;
    /**
     * 用户编码
     */
    @ApiModelProperty(value="用户编码")
    private String usercode;
    /**
     * 卡号
     */
    @ApiModelProperty(value="卡号")
    private String cardno;
    /**
     * 卡类型
     */
    @ApiModelProperty(value="卡类型")
    private String cardtype;
    /**
     * 设备编号集合
     */
    @ApiModelProperty(value="设备编号集合")
    private List<CardSnVo> snList;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    public String getCardtype() {
        return cardtype;
    }

    public void setCardtype(String cardtype) {
        this.cardtype = cardtype;
    }

    public List<CardSnVo> getSnList() {
        return snList;
    }

    public void setSnList(List<CardSnVo> snList) {
        this.snList = snList;
    }
}
