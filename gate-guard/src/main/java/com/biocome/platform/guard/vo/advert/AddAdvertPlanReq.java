package com.biocome.platform.guard.vo.advert;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * @author hxy
 * @date 2019/5/31 09:25
 */
@ApiModel(value = "添加广告计划请求参数")
public class AddAdvertPlanReq {
    @ApiModelProperty(value = "广告计划名称")
    private String planname;
    @ApiModelProperty(value = "播放开始时间")
    private Date begintime;
    @ApiModelProperty(value = "播放结束时间")
    private Date endtime;
    @ApiModelProperty(value = "播放速度 0慢 1中 2快")
    private String speed;
    @ApiModelProperty(value = "播放速度 0：默认优先级，1：高级优先级")
    private String priority;
    @ApiModelProperty(value = "下发单位的编号")
    private List<String> codes;
    @ApiModelProperty(value = "素材ID")
    private List<Integer> materialIds;

    public String getPlanname() {
        return planname;
    }

    public void setPlanname(String planname) {
        this.planname = planname;
    }

    public List<String> getCodes() {
        return codes;
    }

    public void setCodes(List<String> codes) {
        this.codes = codes;
    }

    public List<Integer> getMaterialIds() {
        return materialIds;
    }

    public void setMaterialIds(List<Integer> materialIds) {
        this.materialIds = materialIds;
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
