package com.biocome.platform.common.constant;

/**
 * Created by ace on 2017/8/29.
 */
public class CommonConstants {
    public final static String RESOURCE_TYPE_MENU = "menu";
    public final static String RESOURCE_TYPE_BTN = "button";
    public static final Integer CODE_OK = 200;
    // 用户token异常
    public static final Integer EX_USER_INVALID_CODE = 40101;
    public static final Integer EX_USER_PASS_INVALID_CODE = 40001;
    public static final Integer EX_USER_TOKEN_EXPIRE = 40002;
    public static final Integer EX_USER_REFRESH_TOKEN_INVALID = 40003;
    public static final Integer EX_USERCODE_NULL = 40004;
    // 客户端token异常
    public static final Integer EX_CLIENT_INVALID_CODE = 40301;
    public static final Integer EX_CLIENT_FORBIDDEN_CODE = 40331;
    public static final Integer EX_OTHER_CODE = 500;
    public static final String CONTEXT_KEY_USER_ID = "currentUserId";
    public static final String CONTEXT_KEY_USERNAME = "currentUserName";
    public static final String CONTEXT_KEY_USER_NAME = "currentUser";
    public static final String CONTEXT_KEY_USER_TOKEN = "currentUserToken";
    public static final String CONTEXT_KEY_USERCODE = "currentUserCode";
    public static final String JWT_KEY_USER_ID = "userId";
    public static final String JWT_KEY_NAME = "name";

    public static final String JWT_ACCESS_TOKEN_EFFECTIVE_CODE = "effectiveCode";

    public static final String RESP_RESULT_SUCCESS = "1";

    public static final String DYNAMIC_PASSWORD_OPENTYPE= "2";

    //app
    public static final Integer EX_APP_DB_ERR = 50000;
    public static final Integer EX_APP_USER_NOT_EXIST = 50001;
    public static final Integer EX_APP_PASSWORD_NOT_MATCH = 50002;
    public static final Integer EX_APP_USER_PHONE_NOT_BIND = 50003;
    public static final Integer EX_APP_USERTYPE_NOT_NULL = 50004;
    public static final Integer EX_APP_ERR_PWD = 50005;

    public static final Integer EX_APP_SMS_PHONE_NULL = 51001;
    public static final Integer EX_APP_SMS_MSG_NOT_MATCH = 51002;

    //common
    //证件号码为空
    public static final Integer EX_CERTNO_NULL = 10001;
    public static final Integer EX_CERTNO_EXISTS = 10002;
    //检索类型不能为空
    public static final Integer EX_ES_SEARCH_TYPE_IS_NULL = 10010;
    public static final Integer EX_ES_SEARCH_NAME_IS_NULL = 10011;
    public static final Integer EX_ES_DATA_ERR = 10012;
}
