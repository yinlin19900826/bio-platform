package com.biocome.platform.common.msg.auth;

/**
 * @author hxy
 * @date 2019/5/14 10:27
 */
public class BaseRpcResponse {
    private String message;
    private String result;
    private String errorcode;

    public BaseRpcResponse() {

    }

    public BaseRpcResponse(String message, String result, String errorcode) {
        this.message = message;
        this.result = result;
        this.errorcode = errorcode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(String errorcode) {
        this.errorcode = errorcode;
    }

    @Override
    public String toString() {
        return "BaseRpcResponse{" +
                "message='" + message + '\'' +
                ", result='" + result + '\'' +
                ", errorcode='" + errorcode + '\'' +
                '}';
    }

    public BaseRpcResponse success() {
        this.setMessage("成功");
        this.setErrorcode("");
        this.setResult("1");
        return this;
    }

    public BaseRpcResponse error() {
        this.setMessage("服务异常");
        this.setErrorcode("500");
        this.setResult("0");
        return this;
    }

    public BaseRpcResponse failure() {
        this.setMessage("失败");
        this.setErrorcode("204");
        this.setResult("0");
        return this;
    }
}
