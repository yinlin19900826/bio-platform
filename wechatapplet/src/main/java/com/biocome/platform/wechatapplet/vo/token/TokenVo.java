package com.biocome.platform.wechatapplet.vo.token;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: TokenVo
 * @Author: shenlele
 * @Date: 2019/7/29 18:28
 * @Description:
 */
@ApiModel(value = "向设备请求token")
public class TokenVo {


    @ApiModelProperty(value = "平台ID（biocome）",notes = "平台ID（biocome）")
    private String appid;
    @ApiModelProperty(value = "密码",notes = "密码")
    private String password;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public TokenVo() {
    }

    public TokenVo(String appid, String password) {
        this.appid = appid;
        this.password = password;
    }
}
