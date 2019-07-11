package com.biocome.platform.common.util;

import javax.persistence.Column;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class QueryUtil {

    public static Map<String, Object> putQueryAttr(Object params, Class entityClz) {
        Map<String, String> columnMapping = getColumnMapping(entityClz);
        Map<String, Object> map = new HashMap<String, Object>();
        Class clazz = params.getClass();
        Field[] fields = clazz.getDeclaredFields();
        try {
            if(fields.length > 0){
                for(Field field : fields){
                    PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
                    Method method = pd.getReadMethod();
                    if(method == null){
                        continue;
                    }
                    Object value = method.invoke(params);
                    if(value != null){
                        if(value instanceof String){
                            if("".equals((String)value)){
                                continue;
                            }
                        }
                        String columnName = columnMapping.get(field.getName());
                        if(!ValidateUtils.isNotEmpty(columnName)){
                            map.put(field.getName(), value);
                        }
                    }
                }
            }
        }catch(IntrospectionException e){
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return map;
    }

    private static Map<String, String> getColumnMapping(Class clazz) {
        Map<String, String> map = new HashMap<String, String>();
        Field[] fileds = clazz.getDeclaredFields();
        for(Field field : fileds){
            if(field.isAnnotationPresent(Column.class)){
                Column column = field.getAnnotation(Column.class);
                if(ValidateUtils.isNotEmpty(column.name())){
                    map.put(field.getName(), column.name());
                }
            }
        }
        return map;
    }
}
