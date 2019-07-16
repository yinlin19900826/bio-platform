package com.biocome.platform.common.exception.auth;


import com.biocome.platform.common.exception.BaseException;
import com.biocome.platform.common.constant.CommonConstants;

/**
 * Created by ace on 2017/9/12.
 */
public class ClientForbiddenException extends BaseException {
    public ClientForbiddenException(String message) {
        super(message, CommonConstants.EX_CLIENT_FORBIDDEN_CODE);
    }

}
