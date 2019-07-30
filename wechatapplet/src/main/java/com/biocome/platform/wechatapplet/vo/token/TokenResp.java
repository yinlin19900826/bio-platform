package com.biocome.platform.wechatapplet.vo.token;

import com.biocome.platform.common.msg.auth.BaseRpcResponse;

/**
 * @ClassName: TokenResp
 * @Author: shenlele
 * @Date: 2019/7/29 18:28
 * @Description:
 */
public class TokenResp extends BaseRpcResponse {
    private String token;
    private String expires;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getExpires() {
        return expires;
    }

    public void setExpires(String expires) {
        this.expires = expires;
    }
}
