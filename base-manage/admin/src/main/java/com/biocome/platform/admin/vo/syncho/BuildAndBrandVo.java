package com.biocome.platform.admin.vo.syncho;

import com.biocome.platform.admin.constant.AdminCommonConstant;
import com.biocome.platform.admin.vo.common.CommonVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: BuildAndBrandVo
 * @Author: shenlele
 * @Date: 2019/5/22 15:32
 * @Description:
 */
@ApiModel(value = "楼栋所属行政区划和厂家编码实体类")
public class BuildAndBrandVo extends CommonVo {

    /**
     * 厂家编号
     */
    @ApiModelProperty(value = "厂家编号")
    private String brand;
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
     * 所属小区编码
     */
    @ApiModelProperty(value = "所属小区编码")
    private String estatecode;
    /**
     * 楼幢编码
     */
    @ApiModelProperty(value = "楼幢编码")
    private String buildcode;

    public BuildAndBrandVo(){}

    public BuildAndBrandVo(SynchoVo vo){
        this.setToken(vo.getToken());
        if (AdminCommonConstant.DISTRICT_PROVINCE.equals(vo.getType())) {
            this.setProvince(vo.getCode());
        } else if (AdminCommonConstant.DISTRICT_CITY.equals(vo.getType())) {
            this.setCity(vo.getCode());
        } else if (AdminCommonConstant.DISTRICT_COUNTY.equals(vo.getType())) {
            this.setCounty(vo.getCode());
        } else if (AdminCommonConstant.DISTRICT_STREET.equals(vo.getType())) {
            this.setStreet(vo.getCode());
        } else if (AdminCommonConstant.DISTRICT_POLICESTATIO.equals(vo.getType())) {
            this.setPolicestatio(vo.getCode());
        } else if (AdminCommonConstant.DISTRICT_ESTATE.equals(vo.getType())) {
            this.setEstatecode(vo.getCode());
        } else if (AdminCommonConstant.DISTRICT_BUILD.equals(vo.getType())) {
            this.setBuildcode(vo.getCode());
        }
    }


    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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

    public String getEstatecode() {
        return estatecode;
    }

    public void setEstatecode(String estatecode) {
        this.estatecode = estatecode;
    }

    public String getBuildcode() {
        return buildcode;
    }

    public void setBuildcode(String buildcode) {
        this.buildcode = buildcode;
    }
}
