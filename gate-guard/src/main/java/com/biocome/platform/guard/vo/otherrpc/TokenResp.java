package com.biocome.platform.guard.vo.otherrpc;

import com.biocome.platform.common.msg.auth.BaseRpcResponse;

/**
 * @author hxy
 * @date 2019/5/23 18:03
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
