package com.biocome.platform.guard.constant;

/**
 * ${DESCRIPTION}
 *
 * @author wanghaobin
 * @create 2017-06-17 14:41
 */
public class AdminCommonConstant {
    public final static int ROOT = -1;
    public final static int DEFAULT_GROUP_TYPE = 0;
    /**
     * 权限关联类型
     */
    public final static String AUTHORITY_TYPE_GROUP = "group";
    /**
     * 权限资源类型
     */
    public final static String RESOURCE_TYPE_MENU = "menu";
    public final static String RESOURCE_TYPE_BTN = "button";

    public final static String RESOURCE_REQUEST_METHOD_GET = "GET";
    public final static String RESOURCE_REQUEST_METHOD_PUT = "PUT";
    public final static String RESOURCE_REQUEST_METHOD_DELETE = "DELETE";
    public final static String RESOURCE_REQUEST_METHOD_POST = "POST";

    public final static String RESOURCE_ACTION_VISIT = "访问";

    public final static String BOOLEAN_NUMBER_FALSE = "0";

    public final static String BOOLEAN_NUMBER_TRUE = "1";

    /**
     * 行政区划等级
     */
    public final static String DISTRICT_PROVINCE = "1";
    public final static String DISTRICT_CITY = "2";
    public final static String DISTRICT_COUNTY = "3";
    public final static String DISTRICT_STREET = "4";
    public final static String DISTRICT_POLICESTATIO = "5";
    public final static String DISTRICT_ESTATE = "6";
    public final static String DISTRICT_BUILD = "7";

    /**
     * 公用字符串数字
     */
    public final static String DEFAULT_ZERO = "0";
    public final static String DEFAULT_ONE = "1";
    public final static String DEFAULT_TWO = "2";
    public final static String DEFAULT_THREE = "3";
    public final static String DEFAULT_FOUR = "4";
    public final static String DEFAULT_FIVE = "5";

    /**
     * redis广告素材待删除key
     */
    public final static String DELETE_KEY = "advert_material_delete";
}
