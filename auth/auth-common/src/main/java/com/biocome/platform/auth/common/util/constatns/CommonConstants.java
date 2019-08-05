package com.biocome.platform.auth.common.util.constatns;

/**
 * Created by ace on 2017/8/29.
 */
public class CommonConstants {
    public final static String RESOURCE_TYPE_MENU = "menu";
    public final static String RESOURCE_TYPE_BTN = "button";
    public static final Integer EX_TOKEN_ERROR_CODE = 40101;
    // 用户token异常
    public static final Integer EX_USER_INVALID_CODE = 40102;
    // 客户端token异常
    public static final Integer EX_CLIENT_INVALID_CODE = 40131;
    public static final Integer EX_CLIENT_FORBIDDEN_CODE = 40331;
    public static final Integer EX_OTHER_CODE = 500;
    public static final String CONTEXT_KEY_USER_ID = "currentUserId";
    public static final String CONTEXT_KEY_USERNAME = "currentUserName";
    public static final String CONTEXT_KEY_USER_NAME = "currentUser";
    public static final String CONTEXT_KEY_USER_TOKEN = "currentUserToken";
    public static final String JWT_KEY_USER_ID = "userId";
    public static final String JWT_KEY_NAME = "name";
    public static final String JWT_KEY_EFFECTIVE_CODE = "effectiveCode";
    public static final String JWT_KEY_USERCODE = "usercode";
    public static final String JWT_KEY_ENDTIME = "endtime";

    public static final String JWT_REFRESH_TOKEN_KEY_PRIFIX = "refresh_token";
    public static final String JWT_ACCESS_TOKEN_EFFECTIVE_CODE = "effectiveCode";
    public static final String ACCESS_TOKEN_HEADER = "ACCESS_TOKEN";
    public static final String REFRESH_TOKEN_HEADER = "REFRESH_TOKEN";
    public static final String NEW_TOKEN = "NEW_TOKEN";

    //token有效期
    public static final Integer TOKEN_EFFETIVE_DAY = 30;
    public static final Integer TOKEN_CHANGE_MINITE = 5;
    public static final int REFRESH_TOKEN_EXPIRE = 60 * 60 * 24 *30;
}
