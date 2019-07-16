package com.biocome.platform.inter.basemanager.entity;

import com.biocome.platform.common.entity.Base;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 房屋实体类
 *
 * @ClassName: House
 * @Author: shenlele
 * @Date: 2019/5/7 10:49
 * @Description:
 */
@ApiModel(value = "房屋实体类")
@Table(name = "base_house")
public class House extends Base {
    /**
     * 主键编号
     */
    @Id
    private Integer id;

    /**
     * 房屋编码
     */
    @ApiModelProperty(value = "房屋编码")
    private String housecode;

    /**
     * 房屋名称
     */
    @ApiModelProperty(value = "房屋名称")
    private String housename;

    /**
     * 所属单元编码
     */
    @ApiModelProperty(value = "所属单元编码")
    private String unitcode;

    /**
     * 所属单元名称
     */
    @ApiModelProperty(value = "所属单元名称")
    private String unitname;

    /**
     * 所属楼栋编码
     */
    @ApiModelProperty(value = "所属楼栋编码")
    private String buildcode;

    /**
     * 所属楼栋名称
     */
    @ApiModelProperty(value = "所属楼栋名称")
    private String buildname;

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
     * 房屋结构
     */
    @ApiModelProperty(value = "房屋结构")
    private String housestruc;

    /**
     * 户型结构
     */
    @ApiModelProperty(value = "户型结构")
    private String typestruc;

    /**
     * 房屋地址
     */
    @ApiModelProperty(value = "房屋地址")
    private String housesite;

    /**
     * 房屋面积
     */
    @ApiModelProperty(value = "房屋面积")
    private String housearea;

    /**
     * 使用情况
     */
    @ApiModelProperty(value = "使用情况")
    private String usecase;

    /**
     * 使用用途（0未知1商用2民用...）
     */
    @ApiModelProperty(value = "使用用途（0未知1商用2民用...）")
    private Integer usenature;

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
     * 获取房屋编码
     *
     * @return housecode - 房屋编码
     */
    public String getHousecode() {
        return housecode;
    }

    /**
     * 设置房屋编码
     *
     * @param housecode 房屋编码
     */
    public void setHousecode(String housecode) {
        this.housecode = housecode;
    }

    /**
     * 获取房屋名称
     *
     * @return housename - 房屋名称
     */
    public String getHousename() {
        return housename;
    }

    /**
     * 设置房屋名称
     *
     * @param housename 房屋名称
     */
    public void setHousename(String housename) {
        this.housename = housename;
    }

    /**
     * 获取所属单元编码
     *
     * @return unitcode - 所属单元编码
     */
    public String getUnitcode() {
        return unitcode;
    }

    /**
     * 设置所属单元编码
     *
     * @param unitcode 所属单元编码
     */
    public void setUnitcode(String unitcode) {
        this.unitcode = unitcode;
    }

    /**
     * 获取所属单元名称
     *
     * @return unitname - 所属单元名称
     */
    public String getUnitname() {
        return unitname;
    }

    /**
     * 设置所属单元名称
     *
     * @param unitname 所属单元名称
     */
    public void setUnitname(String unitname) {
        this.unitname = unitname;
    }

    /**
     * 获取所属楼栋编码
     *
     * @return buildcode - 所属楼栋编码
     */
    public String getBuildcode() {
        return buildcode;
    }

    /**
     * 设置所属楼栋编码
     *
     * @param buildcode 所属楼栋编码
     */
    public void setBuildcode(String buildcode) {
        this.buildcode = buildcode;
    }

    /**
     * 获取所属楼栋名称
     *
     * @return buildname - 所属楼栋名称
     */
    public String getBuildname() {
        return buildname;
    }

    /**
     * 设置所属楼栋名称
     *
     * @param buildname 所属楼栋名称
     */
    public void setBuildname(String buildname) {
        this.buildname = buildname;
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
     * 获取房屋结构
     *
     * @return housestruc - 房屋结构
     */
    public String getHousestruc() {
        return housestruc;
    }

    /**
     * 设置房屋结构
     *
     * @param housestruc 房屋结构
     */
    public void setHousestruc(String housestruc) {
        this.housestruc = housestruc;
    }

    /**
     * 获取户型结构
     *
     * @return typestruc - 户型结构
     */
    public String getTypestruc() {
        return typestruc;
    }

    /**
     * 设置户型结构
     *
     * @param typestruc 户型结构
     */
    public void setTypestruc(String typestruc) {
        this.typestruc = typestruc;
    }

    /**
     * 获取房屋地址
     *
     * @return housesite - 房屋地址
     */
    public String getHousesite() {
        return housesite;
    }

    /**
     * 设置房屋地址
     *
     * @param housesite 房屋地址
     */
    public void setHousesite(String housesite) {
        this.housesite = housesite;
    }

    /**
     * 获取房屋面积
     *
     * @return housearea - 房屋面积
     */
    public String getHousearea() {
        return housearea;
    }

    /**
     * 设置房屋面积
     *
     * @param housearea 房屋面积
     */
    public void setHousearea(String housearea) {
        this.housearea = housearea;
    }

    /**
     * 获取使用情况
     *
     * @return usecase - 使用情况
     */
    public String getUsecase() {
        return usecase;
    }

    /**
     * 设置使用情况
     *
     * @param usecase 使用情况
     */
    public void setUsecase(String usecase) {
        this.usecase = usecase;
    }

    /**
     * 获取使用用途（0未知1商用2民用...）
     *
     * @return usenature - 使用用途（0未知1商用2民用...）
     */
    public Integer getUsenature() {
        return usenature;
    }

    /**
     * 设置使用用途（0未知1商用2民用...）
     *
     * @param usenature 使用用途（0未知1商用2民用...）
     */
    public void setUsenature(Integer usenature) {
        this.usenature = usenature;
    }
}
