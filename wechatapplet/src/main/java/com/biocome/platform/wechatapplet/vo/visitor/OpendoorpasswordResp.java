package com.biocome.platform.wechatapplet.vo.visitor;

import com.biocome.platform.common.msg.auth.BaseRpcResponse;

/**
 * @author hxy
 * @date 2019/5/15 18:25
 */
public class OpendoorpasswordResp extends BaseRpcResponse {
    private String password;

    public OpendoorpasswordResp(){

    }

    public OpendoorpasswordResp(String password) {
        this.password = password;
    }

    public OpendoorpasswordResp(String message, String result, String errorcode, String password) {
        super(message, result, errorcode);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
