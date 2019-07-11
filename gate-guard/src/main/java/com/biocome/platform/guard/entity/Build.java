package com.biocome.platform.guard.entity;

import com.biocome.platform.common.entity.Base;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 楼栋实体类
 *
 * @ClassName: Build
 * @Author: shenlele
 * @Date: 2019/5/7 10:48
 * @Description:
 */
@ApiModel(value = "楼栋实体类")
@Table(name = "base_build")
public class Build extends Base {

    /**
     * 主键编号
     */
    @Id
    private Integer id;

    /**
     * 楼幢编码
     */
    @ApiModelProperty(value = "楼幢编码")
    private String buildcode;

    /**
     * 楼幢名称
     */
    @ApiModelProperty(value = "楼幢名称")
    private String buildname;

    /**
     * 楼幢地址
     */
    @ApiModelProperty(value = "楼幢地址")
    private String buildaddress;

    /**
     * 楼幢类型
     */
    @ApiModelProperty(value = "楼幢类型")
    private String buildtype;

    /**
     * 楼幢性质（0未知1商用2民用...）
     */
    @ApiModelProperty(value = "楼幢性质（0未知1商用2民用...）")
    private Integer buildnature;

    /**
     * 所属小区编码
     */
    @ApiModelProperty(value = "所属小区编码")
    private String estatecode;

    /**
     * 所属小区名称
     */
    @ApiModelProperty(value = "所属小区名称")
    private String estatename;

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
     * 单元数
     */
    @ApiModelProperty(value = "单元数")
    private String unitnum;

    /**
     * 层数
     */
    @ApiModelProperty(value = "层数")
    private String floornum;

    /**
     * 户数
     */
    @ApiModelProperty(value = "户数")
    private String housenum;

    /**
     * 是否有电梯(0是1否2未知)
     */
    @ApiModelProperty(value = "是否有电梯(0是1否2未知)")
    private Integer islift;

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
     * 获取楼幢编码
     *
     * @return buildcode - 楼幢编码
     */
    public String getBuildcode() {
        return buildcode;
    }

    /**
     * 设置楼幢编码
     *
     * @param buildcode 楼幢编码
     */
    public void setBuildcode(String buildcode) {
        this.buildcode = buildcode;
    }

    /**
     * 获取楼幢名称
     *
     * @return buildname - 楼幢名称
     */
    public String getBuildname() {
        return buildname;
    }

    /**
     * 设置楼幢名称
     *
     * @param buildname 楼幢名称
     */
    public void setBuildname(String buildname) {
        this.buildname = buildname;
    }

    /**
     * 获取楼幢地址
     *
     * @return buildaddress - 楼幢地址
     */
    public String getBuildaddress() {
        return buildaddress;
    }

    /**
     * 设置楼幢地址
     *
     * @param buildaddress 楼幢地址
     */
    public void setBuildaddress(String buildaddress) {
        this.buildaddress = buildaddress;
    }

    /**
     * 获取楼幢类型
     *
     * @return buildtype - 楼幢类型
     */
    public String getBuildtype() {
        return buildtype;
    }

    /**
     * 设置楼幢类型
     *
     * @param buildtype 楼幢类型
     */
    public void setBuildtype(String buildtype) {
        this.buildtype = buildtype;
    }

    /**
     * 获取楼幢性质（0未知1商用2民用...）
     *
     * @return buildnature - 楼幢性质（0未知1商用2民用...）
     */
    public Integer getBuildnature() {
        return buildnature;
    }

    /**
     * 设置楼幢性质（0未知1商用2民用...）
     *
     * @param buildnature 楼幢性质（0未知1商用2民用...）
     */
    public void setBuildnature(Integer buildnature) {
        this.buildnature = buildnature;
    }

    /**
     * 获取所属小区编码
     *
     * @return estatecode - 所属小区编码
     */
    public String getEstatecode() {
        return estatecode;
    }

    /**
     * 设置所属小区编码
     *
     * @param estatecode 所属小区编码
     */
    public void setEstatecode(String estatecode) {
        this.estatecode = estatecode;
    }

    /**
     * 获取所属小区名称
     *
     * @return estatename - 所属小区名称
     */
    public String getEstatename() {
        return estatename;
    }

    /**
     * 设置所属小区名称
     *
     * @param estatename 所属小区名称
     */
    public void setEstatename(String estatename) {
        this.estatename = estatename;
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
     * 获取单元数
     *
     * @return unitnum - 单元数
     */
    public String getUnitnum() {
        return unitnum;
    }

    /**
     * 设置单元数
     *
     * @param unitnum 单元数
     */
    public void setUnitnum(String unitnum) {
        this.unitnum = unitnum;
    }

    /**
     * 获取层数
     *
     * @return floornum - 层数
     */
    public String getFloornum() {
        return floornum;
    }

    /**
     * 设置层数
     *
     * @param floornum 层数
     */
    public void setFloornum(String floornum) {
        this.floornum = floornum;
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
     * 获取是否有电梯(0是1否2未知)
     *
     * @return islift - 是否有电梯(0是1否2未知)
     */
    public Integer getIslift() {
        return islift;
    }

    /**
     * 设置是否有电梯(0是1否2未知)
     *
     * @param islift 是否有电梯(0是1否2未知)
     */
    public void setIslift(Integer islift) {
        this.islift = islift;
    }
}
