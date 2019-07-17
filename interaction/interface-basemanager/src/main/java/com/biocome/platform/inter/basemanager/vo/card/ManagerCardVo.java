package com.biocome.platform.inter.basemanager.vo.card;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * @author hxy
 * @date 2019/5/15 14:23
 */
@ApiModel(value = "远程调用管理卡")
public class ManagerCardVo {
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
     * 设备编号
     */
    @ApiModelProperty(value="设备编号")
    private List<CardSnVo> snList;
    /**
     * 禁用开始时间
     */
    @ApiModelProperty(value="禁用开始时间")
    private Date starttime;
    /**
     * 禁用结束时间
     */
    @ApiModelProperty(value="禁用结束时间")
    private Date endtime;
    /**
     * 行为 1：禁用 2：恢复
     */
    @ApiModelProperty(value="行为 1：禁用 2：恢复")
    private String operation;

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

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
