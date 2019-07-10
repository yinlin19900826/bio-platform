package com.biocome.platform.common.msg.auth;

import com.biocome.platform.common.constant.RestCodeConstants;
import com.biocome.platform.common.msg.BaseResponse;

/**
 * Created by ace on 2017/8/25.
 */
public class TokenForbiddenResponse  extends BaseResponse {
    public TokenForbiddenResponse(String message) {
        super(RestCodeConstants.TOKEN_FORBIDDEN_CODE, message);
    }
}
