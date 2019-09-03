package com.biocome.platform.file.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName: FileNameUtil
 * @Author: shenlele
 * @Date: 2019/9/3 14:31
 * @Description:
 */
public class FileNameUtil {

    public static final SimpleDateFormat YYYYMMDDHHMMSSS = new SimpleDateFormat("yyyyMMddHHmmssSSS");

    public static String updateName(String name) {
        String[] strArr = name.split("\\.");
        StringBuilder sb = new StringBuilder(strArr[0]).append("-").append(YYYYMMDDHHMMSSS.format(new Date())).append(".").append(strArr[1]);
        return sb.toString();
    }
}
