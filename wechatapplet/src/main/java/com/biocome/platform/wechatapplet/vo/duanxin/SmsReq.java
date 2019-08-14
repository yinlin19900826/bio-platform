package com.biocome.platform.wechatapplet.vo.duanxin;

import java.util.List;

/**
 * @author hxy
 * @date 2019/8/14 11:22
 */
public class SmsReq {
    private String id;
    private String method;
    private SmsAccoutDetail params;

    public SmsReq() {
    }

    public SmsReq(String id, String method, SmsAccoutDetail params) {
        this.id = id;
        this.method = method;
        this.params = params;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public SmsAccoutDetail getParams() {
        return params;
    }

    public void setParams(SmsAccoutDetail params) {
        this.params = params;
    }
}
