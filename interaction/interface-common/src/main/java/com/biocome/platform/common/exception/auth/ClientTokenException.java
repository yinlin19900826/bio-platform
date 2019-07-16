package com.biocome.platform.common.exception.auth;


import com.biocome.platform.common.exception.BaseException;
import com.biocome.platform.common.constant.CommonConstants;

/**
 * Created by ace on 2017/9/10.
 */
public class ClientTokenException extends BaseException {
    public ClientTokenException(String message) {
        super(message, CommonConstants.EX_CLIENT_INVALID_CODE);
    }
}
