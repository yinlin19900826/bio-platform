package com.biocome.platform.wechatapplet.vo.duanxin;

import java.util.List;

/**
 * @author hxy
 * @date 2019/8/14 11:44
 */
public class SmsResp {
    private String id;
    private List<SmsDetailResp> result;
    private SmsErrorResp error;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<SmsDetailResp> getResult() {
        return result;
    }

    public void setResult(List<SmsDetailResp> result) {
        this.result = result;
    }

    public SmsErrorResp getError() {
        return error;
    }

    public void setError(SmsErrorResp error) {
        this.error = error;
    }
}
