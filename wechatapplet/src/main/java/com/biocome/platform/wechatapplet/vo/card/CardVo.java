package com.biocome.platform.wechatapplet.vo.card;

import java.util.List;

/**
 * @ClassName: CardVo
 * @Author: shenlele
 * @Date: 2019/7/30 10:45
 * @Description:
 */
public class CardVo {
    private String res;
    private List<String> codes;

    public CardVo error() {
        this.setRes("0");
        return this;
    }

    public CardVo success(List<String> codes) {
        this.setRes("1");
        this.setCodes(codes);
        return this;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public List<String> getCodes() {
        return codes;
    }

    public void setCodes(List<String> codes) {
        this.codes = codes;
    }
}
