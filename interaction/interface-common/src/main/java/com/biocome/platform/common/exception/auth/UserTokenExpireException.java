package com.biocome.platform.common.exception.auth;


import com.biocome.platform.common.constant.CommonConstants;
import com.biocome.platform.common.exception.BaseException;

/**
 * Created by ace on 2017/9/8.
 */
public class UserTokenExpireException extends BaseException {
    public UserTokenExpireException(String message) {
        super(message, CommonConstants.EX_USER_TOKEN_EXPIRE);
    }
}
