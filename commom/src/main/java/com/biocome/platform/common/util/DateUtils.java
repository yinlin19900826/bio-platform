package com.biocome.platform.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils
{
  public static final SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");
  public static final SimpleDateFormat yyyyMMdd2 = new SimpleDateFormat("/yyyy/MM/dd/");
  public static final SimpleDateFormat yyyyMMddHHMMSS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  public static final SimpleDateFormat yyyyMMddHHMMSSS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
  
  public static synchronized String getCurrentDateStr()
  {
    return yyyyMMdd.format(new Date());
  }
  
  public static synchronized String getCurrentDatePath()
  {
    return yyyyMMdd2.format(new Date());
  }
  
  public static synchronized Date getCurrentDate()
  {
    Date date = null;
    try
    {
      date = yyyyMMdd.parse(getCurrentDateStr());
    }
    catch (ParseException e)
    {
      e.printStackTrace();
    }
    return date;
  }
  
  public static synchronized String getCurrentTimeStr()
  {
    return yyyyMMddHHMMSS.format(new Date());
  }
  
  public static synchronized String getCurrentTimeStrWithMsec()
  {
    return yyyyMMddHHMMSSS.format(new Date());
  }
  
  public static synchronized Date getCurrentTime()
  {
    Date date = null;
    try
    {
      date = yyyyMMddHHMMSS.parse(getCurrentTimeStr());
    }
    catch (ParseException e)
    {
      e.printStackTrace();
    }
    return date;
  }
  
  public static synchronized Date getAddDaysDate(Date date, int day)
  {
    Calendar c = Calendar.getInstance();
    c.setTime(date);
    c.add(5, day);
    return c.getTime();
  }
  
  public static synchronized String getAddDaysDateStr(Date date, int day)
  {
    Date addDate = getAddDaysDate(date, day);
    return yyyyMMddHHMMSS.format(addDate);
  }
  
  public static synchronized Date getAddHourTime(Date time, int hour)
  {
    Calendar c = Calendar.getInstance();
    c.setTime(time);
    c.add(11, hour);
    return c.getTime();
  }
  
  public static synchronized String getAddHourTimeStr(Date time, int hour)
  {
    Date date = getAddHourTime(time, hour);
    return yyyyMMddHHMMSS.format(date);
  }
  
  public static synchronized Date getAddMinuteTime(Date time, int minute)
  {
    Calendar c = Calendar.getInstance();
    c.setTime(time);
    c.add(12, minute);
    return c.getTime();
  }
  
  public static synchronized String getAddMinuteTimeStr(Date time, int minute)
  {
    Date date = getAddMinuteTime(time, minute);
    return yyyyMMddHHMMSS.format(date);
  }
  
  public static synchronized Date getSubtractMinuteTime(Date time, int minute)
  {
    Calendar c = Calendar.getInstance();
    c.setTime(time);
    c.add(12, -minute);
    return c.getTime();
  }
  
  public static synchronized String getSubtractMinuteTimeStr(Date time, int minute)
  {
    Date date = getSubtractMinuteTime(time, minute);
    return yyyyMMddHHMMSS.format(date);
  }
  
  public static synchronized int compareCurrentTime(Date time)
  {
    int result = 0;
    Date currentTime = getCurrentTime();
    if (time.getTime() > currentTime.getTime()) {
      result = 1;
    } else if (time.getTime() < currentTime.getTime()) {
      result = -1;
    }
    return result;
  }
  
  public static synchronized int compareTime(Date time1, Date time2)
  {
    int result = 0;
    if (time1.getTime() > time2.getTime()) {
      result = 1;
    } else if (time1.getTime() < time2.getTime()) {
      result = -1;
    }
    return result;
  }
  
  public static synchronized Date getParseTime(String time)
  {
    Date date = null;
    if (ValidateUtils.isNotEmpty(time)) {
      try
      {
        if (time.indexOf("/") > 0) {
          time = time.replaceAll("/", "-");
        }
        date = yyyyMMddHHMMSS.parse(time);
      }
      catch (ParseException e)
      {
        e.printStackTrace();
      }
    }
    return date;
  }
  
  public static synchronized String getFormatTime(Date time)
  {
    String str = "";
    if (ValidateUtils.isNotEmpty(time)) {
      try
      {
        str = yyyyMMddHHMMSS.format(time);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    return str;
  }
  
  public static synchronized Date parseTime(String time)
  {
    Date date = null;
    if (ValidateUtils.isNotEmpty(time)) {
      try
      {
        date = yyyyMMddHHMMSS.parse(time);
      }
      catch (ParseException e)
      {
        e.printStackTrace();
      }
    }
    return date == null ? getCurrentTime() : date;
  }
  
  public static synchronized Date getParseDate(String dateStr)
  {
    Date date = null;
    if (ValidateUtils.isNotEmpty(dateStr)) {
      try
      {
        date = yyyyMMdd.parse(dateStr);
      }
      catch (ParseException e)
      {
        e.printStackTrace();
      }
    }
    return date;
  }
  
  public static synchronized String getFormatDate(Date date)
  {
    String str = "";
    if (date != null) {
      try
      {
        str = yyyyMMdd.format(date);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    return str;
  }
  
  public static synchronized String getCurrentYear()
  {
    Calendar cal = Calendar.getInstance();
    int year = cal.get(1);
    return String.valueOf(year);
  }
  
  public static synchronized String getCurrentMonth()
  {
    String result = "";
    Calendar cal = Calendar.getInstance();
    int month = cal.get(2) + 1;
    if (month < 10) {
      result = "0" + month;
    } else {
      result = String.valueOf(month);
    }
    return result;
  }
  
  public static synchronized String getCurrentDay()
  {
    String result = "";
    Calendar cal = Calendar.getInstance();
    int day = cal.get(5);
    if (day < 10) {
      result = "0" + day;
    } else {
      result = String.valueOf(day);
    }
    return result;
  }
  
  public static synchronized int getCurrentHour()
  {
    Calendar cal = Calendar.getInstance();
    return cal.get(11);
  }
  
  public static synchronized int getCurrentMinute()
  {
    Calendar cal = Calendar.getInstance();
    return cal.get(12);
  }
  
  public static synchronized int getCurrentSecond()
  {
    Calendar cal = Calendar.getInstance();
    return cal.get(13);
  }
  
  public static void main(String[] args)
  {
    System.out.println(getAddHourTimeStr(getCurrentTime(), 1));
    System.out.println(getAddDaysDateStr(getCurrentDate(), -7));
    System.out.println(getCurrentDateStr());
    System.out.println(getCurrentTimeStr());
    System.out.println(getAddMinuteTimeStr(getCurrentTime(), 3));
    System.out.println(compareCurrentTime(getAddMinuteTime(getCurrentTime(), -3)));
    
    System.out.println(getCurrentYear());
    System.out.println(getCurrentMonth());
    System.out.println(getCurrentDay());
    
    System.out.println(getCurrentHour());
    System.out.println(getCurrentMinute());
    System.out.println(getCurrentSecond());
  }
}
