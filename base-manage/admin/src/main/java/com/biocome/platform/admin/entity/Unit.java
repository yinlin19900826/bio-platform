package com.biocome.platform.admin.entity;

import com.biocome.platform.common.entity.Base;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 单元实体类
 *
 * @ClassName: Unit
 * @Author: shenlele
 * @Date: 2019/5/7 10:52
 * @Description:
 */
@ApiModel(value = "单元实体类")
@Table(name = "base_unit")
public class Unit extends Base {
    /**
     * 主键编号
     */
    @Id
    private Integer id;

    /**
     * 单元编码
     */
    @ApiModelProperty(value = "单元编码")
    private String unitcode;

    /**
     * 单元名称
     */
    @ApiModelProperty(value = "单元名称")
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
     * 户数
     */
    @ApiModelProperty(value = "户数")
    private String housenum;

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
     * 获取单元编码
     *
     * @return unitcode - 单元编码
     */
    public String getUnitcode() {
        return unitcode;
    }

    /**
     * 设置单元编码
     *
     * @param unitcode 单元编码
     */
    public void setUnitcode(String unitcode) {
        this.unitcode = unitcode;
    }

    /**
     * 获取单元名称
     *
     * @return unitname - 单元名称
     */
    public String getUnitname() {
        return unitname;
    }

    /**
     * 设置单元名称
     *
     * @param unitname 单元名称
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
}
