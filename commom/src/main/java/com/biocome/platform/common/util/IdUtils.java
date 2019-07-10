package com.biocome.platform.common.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hxy
 * @date 2019/5/8 17:33
 */
public class IdUtils {
    public static final String SEPERATOR_UNDERLINE = "_";
    public static final String SEPERATOR_COMMA = ",";

    public static List<Integer> getIds(String param) {
        String[] idsstr = param.split(",");
        List<Integer> list = new ArrayList<>();
        for (String idstr : idsstr) {
            list.add(Integer.parseInt(idstr));
        }
        return list;
    }

    public static List<String> seperate(String target, String seperator){
        List<String> list = new ArrayList<String>();
        if(ValidateUtils.isNotEmpty(target)){
            String[] strs = target.split(seperator);
            for(String str : strs){
                list.add(str);
            }
        }
        return list;
    }

    /**
     * 将id list转为string
     * @param list
     * @return
     */
    public static String list2String(List<Integer> list){
        StringBuffer bf = new StringBuffer();
        if(list != null && !list.isEmpty()){
            for(int i = 0 ; i < list.size(); i++){
                bf.append(list.get(i));
                if((i+1) != list.size()){
                    bf.append(SEPERATOR_COMMA);
                }
            }
        }
        return bf.toString();
    }
}
