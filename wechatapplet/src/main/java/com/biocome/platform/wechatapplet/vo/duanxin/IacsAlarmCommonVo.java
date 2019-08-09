package com.biocome.platform.wechatapplet.vo.duanxin;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.HashMap;

/**
 * @ClassName: IacsAlarmCommonVo
 * @Author: shenlele
 * @Date: 2019/03/13 11:47
 * @Description:
 */
public class IacsAlarmCommonVo {

    /**
     * 告警名称
     */
    private String alarmName;
    /**
     * 错误信息集合
     */
    private HashMap<String, String> alarmMessageMap;
    /**
     * 错误信息
     */
    private String alarmMessage;
    /**
     * 发生时间
     */
    private Date happenTime;

    public String getAlarmName() {
        return alarmName;
    }

    public void setAlarmName(String alarmName) {
        this.alarmName = alarmName;
    }

    public HashMap<String, String> getAlarmMessageMap() {
        return alarmMessageMap;
    }

    public void setAlarmMessageMap(HashMap<String, String> alarmMessageMap) {
        this.alarmMessageMap = alarmMessageMap;
    }

    public String getAlarmMessage() {
        return alarmMessage;
    }

    public void setAlarmMessage(String alarmMessage) {
        this.alarmMessage = alarmMessage;
    }

    public Date getHappenTime() {
        return happenTime;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public void setHappenTime(Date happenTime) {
        this.happenTime = happenTime;
    }

}
