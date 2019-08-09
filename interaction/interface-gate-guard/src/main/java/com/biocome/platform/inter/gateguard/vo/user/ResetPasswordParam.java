package com.biocome.platform.inter.gateguard.vo.user;

import io.swagger.annotations.ApiParam;

/**
 * @ClassName: ResetPasswordParam
 * @Author: zengqiang
 * @Date: 2019/8/5
 * @Description:
 */
public class ResetPasswordParam {
    @ApiParam(value = "用户名")
    private String certNo;
    @ApiParam(value = "手机号码")
    private String phoneNo;
    @ApiParam(value = "手机验证码")
    private String sms;
    @ApiParam(value = "重置密码")
    private String resetPassword;
    @ApiParam(value = "确认重置密码")
    private String confirmPassword;

    public String getCertNo() {
        return certNo;
    }

    public void setCertNo(String certNo) {
        this.certNo = certNo;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }

    public String getResetPassword() {
        return resetPassword;
    }

    public void setResetPassword(String resetPassword) {
        this.resetPassword = resetPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
