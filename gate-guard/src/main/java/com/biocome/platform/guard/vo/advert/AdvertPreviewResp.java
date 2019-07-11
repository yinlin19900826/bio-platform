package com.biocome.platform.guard.vo.advert;

import com.biocome.platform.guard.entity.AdvertMaterial;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author hxy
 * @date 2019/6/6 16:40
 */
public class AdvertPreviewResp extends AdvertMaterial {
    @ApiModelProperty(value = "播放开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date begintime;
    @ApiModelProperty(value = "播放结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endtime;
    @ApiModelProperty(value = "播放速度 0慢 1中 2快")
    private String speed;
    @ApiModelProperty(value = "播放速度 0：默认优先级，1：高级优先级")
    private String priority;

    public Date getBegintime() {
        return begintime;
    }

    public void setBegintime(Date begintime) {
        this.begintime = begintime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
