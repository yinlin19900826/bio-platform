package com.biocome.platform.guard.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.biocome.platform.common.entity.Base;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @ClassName: Advert
 * @Author: shenlele
 * @Date: 2019/5/30 15:14
 * @Description:
 */
@ApiModel(value = "广告实体类")
@Table(name = "base_advert")
public class Advert extends Base {
    /**
     * 主键编号
     */
    @Id
    private Integer id;

    /**
     * 广告文件ID
     */
    @ApiModelProperty(value = "广告文件ID")
    private String adno;

    /**
     * 投放设备编号
     */
    @ApiModelProperty(value = "投放设备编号")
    private String sn;

    /**
     * 广告名称
     */
    @ApiModelProperty(value = "广告名称")
    private String adname;

    /**
     * 素材名称
     */
    @ApiModelProperty(value = "素材名称")
    private String materialname;

    /**
     * 广告类型
     */
    @ApiModelProperty(value = "广告类型（0：图片，1：视频，2：文字，3：声音）")
    private String type;

    /**
     * 图片/视频地址
     */
    @ApiModelProperty(value = "图片/视频地址")
    private String filepath;

    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间")
    private Date begintime;

    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间")
    private Date endtime;

    /**
     * 播放速度
     */
    @ApiModelProperty(value = "播放速度")
    private String speed;

    /**
     * 下发状态（0失败1成功）
     */
    @ApiModelProperty(value = "下发状态（0失败1成功）")
    private String issue;

    /**
     * 播放优先级(0：默认优先级，1：高级优先级)
     */
    @ApiModelProperty(value = "播放优先级(0：默认优先级，1：高级优先级)")
    private String priority;

    /**
     * 同一广告文件广告ID可以不同，但是field一定相同
     */
    @ApiModelProperty(value = "同一广告文件广告ID可以不同，但是field一定相同")
    private String fileid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdno() {
        return adno;
    }

    public void setAdno(String adno) {
        this.adno = adno;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
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

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    public Date getBegintime() {
        return begintime;
    }

    public void setBegintime(Date begintime) {
        this.begintime = begintime;
    }

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
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

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
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

    public String getMaterialname() {
        return materialname;
    }

    public void setMaterialname(String materialname) {
        this.materialname = materialname;
    }
}
