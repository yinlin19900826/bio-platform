package com.biocome.platform.common.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ace on 2017/9/10.
 */
public class StringHelper {
    public static String getObjectValue(Object obj){
        return obj==null?"":obj.toString();
    }

    /**
     * 根据逗号分隔ID
     *
     * @param param
     * @return java.util.List<java.lang.String>
     * @Author shenlele
     * @Date 2019/5/15 9:09
     */
    public static List<String> getStrList(String param) {
        String[] strList = param.split(",");
        List<String> list = new ArrayList<>();
        for (String str : strList) {
            list.add(str);
        }
        return list;
    }
}
