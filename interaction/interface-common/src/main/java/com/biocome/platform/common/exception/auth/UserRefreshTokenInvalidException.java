package com.biocome.platform.common.exception.auth;


import com.biocome.platform.common.constant.CommonConstants;
import com.biocome.platform.common.exception.BaseException;

/**
 * Created by ace on 2017/9/8.
 */
public class UserRefreshTokenInvalidException extends BaseException {
    public UserRefreshTokenInvalidException(String message) {
        super(message, CommonConstants.EX_USER_REFRESH_TOKEN_INVALID);
    }
}
