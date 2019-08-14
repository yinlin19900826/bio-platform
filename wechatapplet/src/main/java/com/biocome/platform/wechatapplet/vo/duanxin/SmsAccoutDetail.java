package com.biocome.platform.wechatapplet.vo.duanxin;

import java.util.List;

/**
 * @author hxy
 * @date 2019/8/14 11:24
 */
public class SmsAccoutDetail {
    private String userid;
    private String password;
    private List<SmsMessageDetail> submit;

    public SmsAccoutDetail() {
    }

    public SmsAccoutDetail(String userid, String password, List<SmsMessageDetail> submit) {
        this.userid = userid;
        this.password = password;
        this.submit = submit;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<SmsMessageDetail> getSubmit() {
        return submit;
    }

    public void setSubmit(List<SmsMessageDetail> submit) {
        this.submit = submit;
    }
}
