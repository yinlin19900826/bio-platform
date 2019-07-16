package com.biocome.platform.inter.basemanager.vo.card;

import java.util.List;

/**
 * @author hxy
 * @date 2019/5/14 11:05
 */
public class OpenCardVo {
    /**
     * 授权token认证
     */
    private String token;
    /**
     * 开卡用户编号
     */
    private String usercode;
    /**
     * 卡号
     */
    private String cardno;
    /**
     * 设备编号集合
     */
    private List<CardSnVo> list;
    /**
     * 卡类型
     */
    private String cardtype;

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

    public List<CardSnVo> getList() {
        return list;
    }

    public void setList(List<CardSnVo> list) {
        this.list = list;
    }

    public String getCardtype() {
        return cardtype;
    }

    public void setCardtype(String cardtype) {
        this.cardtype = cardtype;
    }
}
