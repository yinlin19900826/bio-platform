package com.biocome.platform.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.regex.Pattern;

public class ValidateUtils
{
  private static String[] HanDigiStr = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
  private static String[] HanDiviStr = { "", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟", "万", "拾", "佰", "仟" };
  
  public static boolean isEmpty(String someStr)
  {
    return (someStr == null) || (someStr.trim().length() == 0) || (someStr.trim().equalsIgnoreCase("null"));
  }
  
  public static boolean isEmpty(Long someLong)
  {
    return (someLong == null) || (someLong.intValue() == 0);
  }
  
  public static boolean isEmpty(Object pObj)
  {
    if (pObj == null) {
      return true;
    }
    if (pObj == "") {
      return true;
    }
    if ((pObj instanceof String))
    {
      if ((((String)pObj).length() == 0) || ("null".equals(pObj))) {
        return true;
      }
    }
    else if ((pObj instanceof Collection))
    {
      if (((Collection)pObj).size() == 0) {
        return true;
      }
    }
    else if (((pObj instanceof Map)) && 
      (((Map)pObj).size() == 0)) {
      return true;
    }
    return false;
  }
  
  public static boolean isNotEmpty(Object pObj)
  {
    if (pObj == null) {
      return false;
    }
    if (pObj == "") {
      return false;
    }
    if ((pObj instanceof String))
    {
      if ((((String)pObj).length() == 0) || ("null".equals(pObj)) || ("".equals(((String)pObj).trim()))) {
        return false;
      }
    }
    else if ((pObj instanceof Collection))
    {
      if (((Collection)pObj).size() == 0) {
        return false;
      }
    }
    else if (((pObj instanceof Map)) && 
      (((Map)pObj).size() == 0)) {
      return false;
    }
    return true;
  }
  
  public static boolean isNumber(String str)
  {
    boolean result = false;
    if (isNotEmpty(str))
    {
      String regex = "^[0-9]*$";
      result = Pattern.matches(regex, str);
    }
    return result;
  }
  
  public static String conversionNull(String str)
  {
    if (str == null) {
      return "";
    }
    return str;
  }
  
  public static boolean checkObjisNull(Object obj)
  {
    if (obj == null) {
      return false;
    }
    return true;
  }
  
  public static boolean isPhone(String mobile)
  {
    boolean result = false;
    if (isNotEmpty(mobile))
    {
      String regex = "1([\\d]{10})|((\\+[0-9]{2,4})?\\(?[0-9]+\\)?-?)?[0-9]{7,8}";
      result = Pattern.matches(regex, mobile);
    }
    return result;
  }
  
  public static int length(String value)
  {
    int length = 0;
    if (isNotEmpty(value)) {
      length = value.length();
    }
    return length;
  }
  
  public static String getFixedPersonIDCode(String personIDCode)
    throws Exception
  {
    if (personIDCode == null) {
      throw new Exception("输入的身份证号无效，请检查");
    }
    if (personIDCode.length() == 18)
    {
      if (isIdentity(personIDCode)) {
        return personIDCode;
      }
      throw new Exception("输入的身份证号无效，请检查");
    }
    if (personIDCode.length() == 15) {
      return fixPersonIDCodeWithCheck(personIDCode);
    }
    throw new Exception("输入的身份证号无效，请检查");
  }
  
  public static String fixPersonIDCodeWithCheck(String personIDCode)
    throws Exception
  {
    if ((personIDCode == null) || (personIDCode.trim().length() != 15)) {
      throw new Exception("输入的身份证号不足15位，请检查");
    }
    if (!isIdentity(personIDCode)) {
      throw new Exception("输入的身份证号无效，请检查");
    }
    return fixPersonIDCodeWithoutCheck(personIDCode);
  }
  
  public static String fixPersonIDCodeWithoutCheck(String personIDCode)
    throws Exception
  {
    if ((personIDCode == null) || (personIDCode.trim().length() != 15)) {
      throw new Exception("输入的身份证号不足15位，请检查");
    }
    String id17 = personIDCode.substring(0, 6) + "19" + personIDCode.substring(6, 15);
    
    char[] code = { '1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2' };
    int[] factor = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 };
    int[] idcd = new int[18];
    for (int i = 0; i < 17; i++) {
      idcd[i] = Integer.parseInt(id17.substring(i, i + 1));
    }
    int sum = 0;
    for (int i = 0; i < 17; i++) {
      sum += idcd[i] * factor[i];
    }
    int remainder = sum % 11;
    String lastCheckBit = String.valueOf(code[remainder]);
    return id17 + lastCheckBit;
  }
  
  public static boolean isIdentity(String identity)
  {
    if (identity == null) {
      return false;
    }
    if ((identity.length() == 18) || (identity.length() == 15))
    {
      String id15 = null;
      if (identity.length() == 18) {
        id15 = identity.substring(0, 6) + identity.substring(8, 17);
      } else {
        id15 = identity;
      }
      try
      {
        Long.parseLong(id15);
        
        String birthday = "19" + id15.substring(6, 12);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        sdf.parse(birthday);
        if ((identity.length() == 18) && 
          (!fixPersonIDCodeWithoutCheck(id15).equals(identity))) {
          return false;
        }
      }
      catch (Exception e)
      {
        return false;
      }
      return true;
    }
    return false;
  }
  
  public static Date getBirthdayFromPersonIDCode(String identity)
    throws Exception
  {
    String id = getFixedPersonIDCode(identity);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    try
    {
      return new Date(sdf.parse(id.substring(6, 14)).getTime());
    }
    catch (ParseException e)
    {
      throw new Exception("不是有效的身份证号，请检查");
    }
  }
  
  public static String getGenderFromPersonIDCode(String identity)
    throws Exception
  {
    String id = getFixedPersonIDCode(identity);
    char sex = id.charAt(16);
    return sex % '\002' == 0 ? "2" : "1";
  }
  
  private static String PositiveIntegerToHanStr(String NumStr)
  {
    String RMBStr = "";
    boolean lastzero = false;
    boolean hasvalue = false;
    
    int len = NumStr.length();
    if (len > 15) {
      return "数值过大!";
    }
    for (int i = len - 1; i >= 0; i--) {
      if (NumStr.charAt(len - i - 1) != ' ')
      {
        int n = NumStr.charAt(len - i - 1) - '0';
        if ((n < 0) || (n > 9)) {
          return "输入含非数字字符!";
        }
        if (n != 0)
        {
          if (lastzero) {
            RMBStr = RMBStr + HanDigiStr[0];
          }
          if ((n != 1) || (i % 4 != 1) || (i != len - 1)) {
            RMBStr = RMBStr + HanDigiStr[n];
          }
          RMBStr = RMBStr + HanDiviStr[i];
          hasvalue = true;
        }
        else if ((i % 8 == 0) || ((i % 8 == 4) && (hasvalue)))
        {
          RMBStr = RMBStr + HanDiviStr[i];
        }
        if (i % 8 == 0) {
          hasvalue = false;
        }
        lastzero = (n == 0) && (i % 4 != 0);
      }
    }
    if (RMBStr.length() == 0) {
      return HanDigiStr[0];
    }
    return RMBStr;
  }
  
  public static String numToRMBStr(double val)
  {
    String SignStr = "";
    String TailStr = "";
    if (val < 0.0D)
    {
      val = -val;
      SignStr = "负";
    }
    if ((val > 100000000000000.0D) || (val < -100000000000000.0D)) {
      return "数值位数过大!";
    }
    long temp = Math.round(val * 100.0D);
    long integer = temp / 100L;
    long fraction = temp % 100L;
    int jiao = (int)fraction / 10;
    int fen = (int)fraction % 10;
    if ((jiao == 0) && (fen == 0))
    {
      TailStr = "整";
    }
    else
    {
      TailStr = HanDigiStr[jiao];
      if (jiao != 0) {
        TailStr = TailStr + "角";
      }
      if ((integer == 0L) && (jiao == 0)) {
        TailStr = "";
      }
      if (fen != 0) {
        TailStr = TailStr + HanDigiStr[fen] + "分";
      }
    }
    return SignStr + PositiveIntegerToHanStr(String.valueOf(integer)) + "元" + TailStr;
  }
}
