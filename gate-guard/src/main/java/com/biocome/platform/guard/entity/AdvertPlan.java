package com.biocome.platform.guard.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.biocome.platform.common.entity.Base;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @ClassName: AdvertPlan
 * @Author: shenlele
 * @Date: 2019/5/30 15:09
 * @Description:
 */
@ApiModel(value = "广告计划实体类")
@Table(name = "base_advert_plan")
public class AdvertPlan extends Base {
    /**
     * 主键编号
     */
    @Id
    private Integer id;

    /**
     * 广告计划名称
     */
    @ApiModelProperty(value = "广告计划名称")
    private String planname;

    /**
     * 下发社区
     */
    @ApiModelProperty(value = "下发社区")
    private String downestate;

    /**
     * 审核状态（0通过1未通过2未审核）
     */
    @ApiModelProperty(value = "审核状态（0通过1未通过2未审核）")
    private String isaudit;

    /**
     * 审核意见
     */
    @ApiModelProperty(value = "审核意见")
    private String auditidea;

    /**
     * 审核时间
     */
    @ApiModelProperty(value = "审核时间")
    private Date audittime;

    /**
     * 审核人账号
     */
    @ApiModelProperty(value = "审核人账号")
    private String auditcode;

    /**
     * 审核人昵称
     */
    @ApiModelProperty(value = "审核人昵称")
    private String auditname;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlanname() {
        return planname;
    }

    public void setPlanname(String planname) {
        this.planname = planname;
    }

    public String getDownestate() {
        return downestate;
    }

    public void setDownestate(String downestate) {
        this.downestate = downestate;
    }

    public String getIsaudit() {
        return isaudit;
    }

    public void setIsaudit(String isaudit) {
        this.isaudit = isaudit;
    }

    public String getAuditidea() {
        return auditidea;
    }

    public void setAuditidea(String auditidea) {
        this.auditidea = auditidea;
    }

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    public Date getAudittime() {
        return audittime;
    }

    public void setAudittime(Date audittime) {
        this.audittime = audittime;
    }

    public String getAuditcode() {
        return auditcode;
    }

    public void setAuditcode(String auditcode) {
        this.auditcode = auditcode;
    }

    public String getAuditname() {
        return auditname;
    }

    public void setAuditname(String auditname) {
        this.auditname = auditname;
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
}
