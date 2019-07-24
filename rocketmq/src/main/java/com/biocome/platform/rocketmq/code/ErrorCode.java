package com.biocome.platform.rocketmq.code;

import java.io.Serializable;

/**
 * @author hxy
 * @date 2019/7/9 17:06
 */
public interface ErrorCode extends Serializable {

    /**
     * 错误码
     *
     * @return
     */
    String getCode();

    /**
     * 错误信息
     *
     * @return
     */
    String getMsg();
}