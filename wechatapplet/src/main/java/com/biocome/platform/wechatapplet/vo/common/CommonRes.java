package com.biocome.platform.wechatapplet.vo.common;

import java.util.List;

/**
 * @ClassName: CommonRes
 * @Author: shenlele
 * @Date: 2019/7/30 10:45
 * @Description:
 */
public class CommonRes<T> {
    private String res;
    private List<T> codes;

    public CommonRes error() {
        this.setRes("0");
        return this;
    }

    public CommonRes success(List<T> codes) {
        this.setRes("1");
        this.setCodes(codes);
        return this;
    }

    public String getRes() {
        return res;
    }

    private void setRes(String res) {
        this.res = res;
    }

    public List<T> getCodes() {
        return codes;
    }

    private void setCodes(List<T> codes) {
        this.codes = codes;
    }
}
