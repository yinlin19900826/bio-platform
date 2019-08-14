package com.biocome.platform.common.msg;

/**
 * Created by Ace on 2017/6/11.
 */
public class ObjectRestResponse<T> extends BaseResponse {

    T data;
    boolean rel;

    public boolean isRel() {
        return rel;
    }

    public void setRel(boolean rel) {
        this.rel = rel;
    }

    public ObjectRestResponse() {
    }

    public ObjectRestResponse(boolean rel) {
        this.setRel(rel);
    }

    public ObjectRestResponse(int status, String message) {
        this.setStatus(status);
        this.setMessage(message);
    }

    public ObjectRestResponse rel(boolean rel) {
        this.setRel(rel);
        return this;
    }


    public ObjectRestResponse data(T data) {
        this.setData(data);
        return this;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ObjectRestResponse success() {
        this.setMessage("成功");
        this.setRel(true);
        return this;
    }

    public ObjectRestResponse failure() {
        this.setStatus(204);
        this.setMessage("失败");
        return this;
    }

    public ObjectRestResponse error() {
        this.setStatus(500);
        this.setMessage("异常，请检查参数格式");
        return this;
    }

    public ObjectRestResponse customError(String message) {
        this.setStatus(204);
        this.setMessage(message);
        return this;
    }

    @Override
    public String toString() {
        return "ObjectRestResponse{" + "status=" + super.getStatus() + "，message=" + super.getMessage()
                + "，data=" + data +
                ", rel=" + rel +
                '}';
    }
}
