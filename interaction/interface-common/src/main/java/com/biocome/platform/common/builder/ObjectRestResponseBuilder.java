package com.biocome.platform.common.builder;

import com.biocome.platform.common.msg.ObjectRestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author hxy
 * @date 2019/8/12 15:46
 */
public class ObjectRestResponseBuilder<T> {
    private Logger log = LoggerFactory.getLogger(getClass());
    private ObjectRestResponse<T> resp;

    public ObjectRestResponseBuilder() {
        resp = new ObjectRestResponse();
    }

    public ObjectRestResponseBuilder setStatus(int status) {
        resp.setStatus(status);
        return this;
    }

    public ObjectRestResponseBuilder setMessage(String message) {
        resp.setMessage(message);
        return this;
    }

    public ObjectRestResponseBuilder setData(T data) {
        resp.setData(data);
        return this;
    }

    public ObjectRestResponseBuilder setRel(Boolean rel) {
        resp.setRel(rel);
        return this;
    }

    public ObjectRestResponse<T> create() {
        log.info("ObjectRestResponse:{}", resp.toString());
        return resp;
    }
}
