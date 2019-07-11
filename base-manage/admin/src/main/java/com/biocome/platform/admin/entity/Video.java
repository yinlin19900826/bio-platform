package com.biocome.platform.admin.entity;

import com.biocome.platform.common.entity.Base;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 视频实体类
 *
 * @ClassName: Video
 * @Author: yinlin
 * @Date: 2019/6/3 10:48
 * @Description:
 */
@ApiModel(value = "视频实体类")
@Table(name = "base_video")
public class Video extends Base {
    /**
     * 主键编号
     */
    @Id
    private Integer id;

    /**
     * 视频编码
     */
    @ApiModelProperty(value = "视频编码")
    private String videocode;

    /**
     * 视频名称
     */
    @ApiModelProperty(value = "视频名称")
    private String videoname;

    /**
     * 视频全称
     */
    @ApiModelProperty(value = "视频全称")
    private String fullname;

    /**
     * 视频类型
     */
    @ApiModelProperty(value = "视频类型")
    private String videotype;

    /**
     * 所属区域编码
     */
    @ApiModelProperty(value = "所属区域编码")
    private String areacode;

    /**
     * 类型
     */
    @ApiModelProperty(value = "类型")
    private int type;

    /**
     * 详细地址
     */
    @ApiModelProperty(value = "详细地址")
    private String detailedaddress;

    /**
     * 省
     */
    @ApiModelProperty(value = "省")
    private String province;

    /**
     * 市
     */
    @ApiModelProperty(value = "市")
    private String city;

    /**
     * 县/区
     */
    @ApiModelProperty(value = "县/区")
    private String county;

    /**
     * 街道
     */
    @ApiModelProperty(value = "街道")
    private String street;

    /**
     * 派出所
     */
    @ApiModelProperty(value = "派出所")
    private String policestatio;

    /**
     * 上级代码
     */
    @ApiModelProperty(value = "上级代码")
    private String parentcode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVideocode() {
        return videocode;
    }

    public void setVideocode(String videocode) {
        this.videocode = videocode;
    }

    public String getVideoname() {
        return videoname;
    }

    public void setVideoname(String videoname) {
        this.videoname = videoname;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getVideotype() {
        return videotype;
    }

    public void setVideotype(String videotype) {
        this.videotype = videotype;
    }

    public String getAreacode() {
        return areacode;
    }

    public void setAreacode(String areacode) {
        this.areacode = areacode;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDetailedaddress() {
        return detailedaddress;
    }

    public void setDetailedaddress(String detailedaddress) {
        this.detailedaddress = detailedaddress;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPolicestatio() {
        return policestatio;
    }

    public void setPolicestatio(String policestatio) {
        this.policestatio = policestatio;
    }

    public String getParentcode() {
        return parentcode;
    }

    public void setParentcode(String parentcode) {
        this.parentcode = parentcode;
    }
}
