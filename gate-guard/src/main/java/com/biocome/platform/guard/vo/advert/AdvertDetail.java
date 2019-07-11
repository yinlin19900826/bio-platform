package com.biocome.platform.guard.vo.advert;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author hxy
 * @date 2019/5/30 15:46
 */
@ApiModel(value = "广告详情")
public class AdvertDetail {
    @ApiModelProperty(value = "广告文件下发ID（同一文件多次下发不一样）")
    private String adno;
    @ApiModelProperty(value = "广告名称")
    private String adname;
    @ApiModelProperty(value = "广告类型（0：图片，1：视频，2：文字，3：声音）")
    private String type;
    @ApiModelProperty(value = "图片/视频地址（http地址）")
    private String filepath;
    @ApiModelProperty(value = "开始时间（yyyy-MM-dd HH:mm:ss）")
    private Date begintime;
    @ApiModelProperty(value = "结束时间（yyyy-MM-dd HH:mm:ss）")
    private Date endtime;
    @ApiModelProperty(value = "播放速度")
    private String speed;
    @ApiModelProperty(value = "播放优先级（0：默认优先级，1：高优先级）")
    private String priority;
    @ApiModelProperty(value = "广告文件ID(同一文件多次下发一样)")
    private String fileid;

    public String getAdno() {
        return adno;
    }

    public void setAdno(String adno) {
        this.adno = adno;
    }

    public String getAdname() {
        return adname;
    }

    public void setAdname(String adname) {
        this.adname = adname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

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

    public String getFileid() {
        return fileid;
    }

    public void setFileid(String fileid) {
        this.fileid = fileid;
    }
}
