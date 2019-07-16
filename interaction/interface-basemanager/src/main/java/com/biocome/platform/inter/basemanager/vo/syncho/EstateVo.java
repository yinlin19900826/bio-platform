package com.biocome.platform.inter.basemanager.vo.syncho;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: EstateVo
 * @Author: shenlele
 * @Date: 2019/5/14 16:33
 * @Description:
 */
@ApiModel(value = "远程同步小区实体类")
public class EstateVo {

    @ApiModelProperty(value = "小区编码")
    private String estatecode;
    @ApiModelProperty(value = "小区名称")
    private String estatename;
    @ApiModelProperty(value = "省")
    private String province;
    @ApiModelProperty(value = "市")
    private String city;
    @ApiModelProperty(value = "县")
    private String county;
    @ApiModelProperty(value = "车位数")
    private String carnum;
    @ApiModelProperty(value = "楼栋数")
    private String buildnum;
    @ApiModelProperty(value = "户数")
    private String housenum;
    @ApiModelProperty(value = "经度")
    private String lng;
    @ApiModelProperty(value = "纬度")
    private String lat;
    @ApiModelProperty(value = "所属物业编码")
    private String managecode;
    @ApiModelProperty(value = "所属区域编码")
    private String areacode;

    public String getEstatecode() {
        return estatecode;
    }

    public void setEstatecode(String estatecode) {
        this.estatecode = estatecode;
    }

    public String getEstatename() {
        return estatename;
    }

    public void setEstatename(String estatename) {
        this.estatename = estatename;
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

    public String getCarnum() {
        return carnum;
    }

    public void setCarnum(String carnum) {
        this.carnum = carnum;
    }

    public String getBuildnum() {
        return buildnum;
    }

    public void setBuildnum(String buildnum) {
        this.buildnum = buildnum;
    }

    public String getHousenum() {
        return housenum;
    }

    public void setHousenum(String housenum) {
        this.housenum = housenum;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getManagecode() {
        return managecode;
    }

    public void setManagecode(String managecode) {
        this.managecode = managecode;
    }

    public String getAreacode() {
        return areacode;
    }

    public void setAreacode(String areacode) {
        this.areacode = areacode;
    }
}
