package com.biocome.platform.guard.otherrpc;

import com.biocome.platform.common.msg.auth.BaseRpcResponse;

/**
 * @author hxy
 * @date 2019/5/15 18:25
 */
public class OpendoorpasswordResp extends BaseRpcResponse {
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
