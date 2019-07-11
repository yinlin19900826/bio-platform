package com.biocome.platform.common.msg.auth;

import com.biocome.platform.common.constant.RestCodeConstants;
import com.biocome.platform.common.msg.BaseResponse;

/**
 * Created by ace on 2017/8/23.
 */
public class TokenErrorResponse extends BaseResponse {
    public TokenErrorResponse(String message) {
        super(RestCodeConstants.TOKEN_ERROR_CODE, message);
    }
}
