package com.biocome.platform.wechatapplet.vo.message;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * @ClassName: MessageVo
 * @Author: shenlele
 * @Date: 2019/7/31 13:46
 * @Description:
 */
public class MessageVo {

    private String name;
    private String details;
    private Date time;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
