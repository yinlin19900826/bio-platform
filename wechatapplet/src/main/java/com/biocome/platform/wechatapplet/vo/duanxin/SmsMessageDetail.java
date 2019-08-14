package com.biocome.platform.wechatapplet.vo.duanxin;

/**
 * @author hxy
 * @date 2019/8/14 11:25
 */
public class SmsMessageDetail {
    private String content;
    private String phone;

    public SmsMessageDetail() {
    }

    public SmsMessageDetail(String content, String phone) {
        this.content = content;
        this.phone = phone;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
