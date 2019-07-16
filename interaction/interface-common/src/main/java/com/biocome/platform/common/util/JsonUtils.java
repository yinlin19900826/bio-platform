package com.biocome.platform.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JsonUtils
{
  static final ObjectMapper mapper = new ObjectMapper();
  
  public static String beanToJson(Object object)
  {
    try
    {
      return mapper.writeValueAsString(object);
    }
    catch (JsonProcessingException e)
    {
      e.printStackTrace();
    }
    return "";
  }
  
  public static String beanListToJson(List list)
  {
    try
    {
      return mapper.writeValueAsString(list);
    }
    catch (JsonProcessingException e)
    {
      e.printStackTrace();
    }
    return "";
  }
  
  public static <T> T jsonToBean(String json, Class<T> bean)
  {
    try
    {
      return mapper.readValue(json, bean);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    return null;
  }
  
  public static <T> T jsonToBean(byte[] json, Class<T> bean)
  {
    try
    {
      return mapper.readValue(json, bean);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    return null;
  }
  
  public static <T> List<T> jsonToBeanList(String json, Class<T> bean)
  {
    try
    {
      CollectionType listType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, bean);
      return (List)mapper.readValue(json, listType);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    return null;
  }
  
  public static <T> Object[] jsonToBeanArray(String json, Class<T> bean)
  {
    try
    {
      ArrayType arrayType = mapper.getTypeFactory().constructArrayType(bean);
      return (Object[])mapper.readValue(json, arrayType);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    return null;
  }
  
  public static List jsonToList(String json)
  {
    try
    {
      return (List)mapper.readValue(json, ArrayList.class);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    return null;
  }
  
  public static Map jsonToMap(String json)
  {
    try
    {
      return (Map)mapper.readValue(json, Map.class);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    return null;
  }
}
