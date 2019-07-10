package com.biocome.platform.common.handler;

import com.biocome.platform.common.msg.ObjectRestResponse;

/**
 * @author hxy
 * @date 2019/5/10 16:15
 */
public class ResultHandler {
    public static ObjectRestResponse objectRestResponseHandle(ObjectRestResponse resp, int result){
        if (result == 0){
            resp.failure();
            return resp;
        }else if (result >= 1){
            resp.success();
            return resp;
        }else{
            resp.error();
            return resp;
        }
    }
}
