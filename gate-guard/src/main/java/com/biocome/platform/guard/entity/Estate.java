package com.biocome.platform.guard.entity;

import com.biocome.platform.common.entity.Base;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 小区实体类
 *
 * @ClassName: Estate
 * @Author: shenlele
 * @Date: 2019/5/7 10:48
 * @Description:
 */
@ApiModel(value = "小区实体类")
@Table(name = "base_estate")
public class Estate extends Base {
    /**
     * 主键编号
     */
    @Id
    private Integer id;

    /**
     * 小区编码
     */
    @ApiModelProperty(value = "小区编码")
    private String estatecode;

    /**
     * 小区名称
     */
    @ApiModelProperty(value = "小区名称")
    private String estatename;

    /**
     * 小区类型
     */
    @ApiModelProperty(value = "小区类型")
    private String estatetype;

    /**
     * 所属区域编码
     */
    @ApiModelProperty(value = "所属区域编码")
    private String areacode;

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
     * 车位数
     */
    @ApiModelProperty(value = "车位数")
    private String carnum;

    /**
     * 楼栋数
     */
    @ApiModelProperty(value = "楼栋数")
    private String buildnum;

    /**
     * 户数
     */
    @ApiModelProperty(value = "户数")
    private String housenum;

    /**
     * 经度
     */
    @ApiModelProperty(value = "经度")
    private String lng;

    /**
     * 纬度
     */
    @ApiModelProperty(value = "纬度")
    private String lat;

    /**
     * 所属物业编码
     */
    @ApiModelProperty(value = "所属物业编码")
    private String managecode;

    /**
     * 管理处电话
     */
    @ApiModelProperty(value = "管理处电话")
    private String managetel;

    /**
     * 客服电话
     */
    @ApiModelProperty(value = "客服电话")
    private String servicetel;

    /**
     * 获取主键编号
     *
     * @return id - 主键编号
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键编号
     *
     * @param id 主键编号
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取小区编码
     *
     * @return estatecode - 小区编码
     */
    public String getEstatecode() {
        return estatecode;
    }

    /**
     * 设置小区编码
     *
     * @param estatecode 小区编码
     */
    public void setEstatecode(String estatecode) {
        this.estatecode = estatecode;
    }

    /**
     * 获取小区名称
     *
     * @return estatename - 小区名称
     */
    public String getEstatename() {
        return estatename;
    }

    /**
     * 设置小区名称
     *
     * @param estatename 小区名称
     */
    public void setEstatename(String estatename) {
        this.estatename = estatename;
    }

    /**
     * 获取小区类型
     *
     * @return estatetype - 小区类型
     */
    public String getEstatetype() {
        return estatetype;
    }

    /**
     * 设置小区类型
     *
     * @param estatetype 小区类型
     */
    public void setEstatetype(String estatetype) {
        this.estatetype = estatetype;
    }

    /**
     * 获取所属区域编码
     *
     * @return areacode - 所属区域编码
     */
    public String getAreacode() {
        return areacode;
    }

    /**
     * 设置所属区域编码
     *
     * @param areacode 所属区域编码
     */
    public void setAreacode(String areacode) {
        this.areacode = areacode;
    }

    /**
     * 获取详细地址
     *
     * @return detailedaddress - 详细地址
     */
    public String getDetailedaddress() {
        return detailedaddress;
    }

    /**
     * 设置详细地址
     *
     * @param detailedaddress 详细地址
     */
    public void setDetailedaddress(String detailedaddress) {
        this.detailedaddress = detailedaddress;
    }

    /**
     * 获取省
     *
     * @return province - 省
     */
    public String getProvince() {
        return province;
    }

    /**
     * 设置省
     *
     * @param province 省
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * 获取市
     *
     * @return city - 市
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置市
     *
     * @param city 市
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 获取县/区
     *
     * @return county - 县/区
     */
    public String getCounty() {
        return county;
    }

    /**
     * 设置县/区
     *
     * @param county 县/区
     */
    public void setCounty(String county) {
        this.county = county;
    }

    /**
     * 获取街道
     *
     * @return street - 街道
     */
    public String getStreet() {
        return street;
    }

    /**
     * 设置街道
     *
     * @param street 街道
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * 获取派出所
     *
     * @return policestatio - 派出所
     */
    public String getPolicestatio() {
        return policestatio;
    }

    /**
     * 设置派出所
     *
     * @param policestatio 派出所
     */
    public void setPolicestatio(String policestatio) {
        this.policestatio = policestatio;
    }

    /**
     * 获取楼栋数
     *
     * @return buildnum - 楼栋数
     */
    public String getBuildnum() {
        return buildnum;
    }

    /**
     * 设置楼栋数
     *
     * @param buildnum 楼栋数
     */
    public void setBuildnum(String buildnum) {
        this.buildnum = buildnum;
    }

    /**
     * 获取户数
     *
     * @return housenum - 户数
     */
    public String getHousenum() {
        return housenum;
    }

    /**
     * 设置户数
     *
     * @param housenum 户数
     */
    public void setHousenum(String housenum) {
        this.housenum = housenum;
    }

    /**
     * 获取经度
     *
     * @return lng - 经度
     */
    public String getLng() {
        return lng;
    }

    /**
     * 设置经度
     *
     * @param lng 经度
     */
    public void setLng(String lng) {
        this.lng = lng;
    }

    /**
     * 获取纬度
     *
     * @return lat - 纬度
     */
    public String getLat() {
        return lat;
    }

    /**
     * 设置纬度
     *
     * @param lat 纬度
     */
    public void setLat(String lat) {
        this.lat = lat;
    }

    /**
     * 获取所属物业编码
     *
     * @return managecode - 所属物业编码
     */
    public String getManagecode() {
        return managecode;
    }

    /**
     * 设置所属物业编码
     *
     * @param managecode 所属物业编码
     */
    public void setManagecode(String managecode) {
        this.managecode = managecode;
    }

    /**
     * 获取管理处电话
     *
     * @return managetel - 管理处电话
     */
    public String getManagetel() {
        return managetel;
    }

    /**
     * 设置管理处电话
     *
     * @param managetel 管理处电话
     */
    public void setManagetel(String managetel) {
        this.managetel = managetel;
    }

    /**
     * 获取客服电话
     *
     * @return servicetel - 客服电话
     */
    public String getServicetel() {
        return servicetel;
    }

    /**
     * 设置客服电话
     *
     * @param servicetel 客服电话
     */
    public void setServicetel(String servicetel) {
        this.servicetel = servicetel;
    }

    public String getCarnum() {
        return carnum;
    }

    public void setCarnum(String carnum) {
        this.carnum = carnum;
    }
}
