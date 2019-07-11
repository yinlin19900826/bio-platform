package com.biocome.platform.guard.vo.syncho;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: BuildVo
 * @Author: shenlele
 * @Date: 2019/5/14 16:50
 * @Description:
 */
@ApiModel(value = "远程同步楼栋实体类")
public class BuildVo {

    @ApiModelProperty(value = "楼幢编码（唯一性）")
    private String buildcode;
    @ApiModelProperty(value = "楼幢名称")
    private String buildname;
    @ApiModelProperty(value = "单元数")
    private String unitnum;
    @ApiModelProperty(value = "层数")
    private String floornum;
    @ApiModelProperty(value = "户数")
    private String housenum;
    @ApiModelProperty(value = "是否有电梯")
    private String islift;
    @ApiModelProperty(value = "所属小区编码")
    private String estatecode;

    public String getBuildcode() {
        return buildcode;
    }

    public void setBuildcode(String buildcode) {
        this.buildcode = buildcode;
    }

    public String getBuildname() {
        return buildname;
    }

    public void setBuildname(String buildname) {
        this.buildname = buildname;
    }

    public String getUnitnum() {
        return unitnum;
    }

    public void setUnitnum(String unitnum) {
        this.unitnum = unitnum;
    }

    public String getFloornum() {
        return floornum;
    }

    public void setFloornum(String floornum) {
        this.floornum = floornum;
    }

    public String getHousenum() {
        return housenum;
    }

    public void setHousenum(String housenum) {
        this.housenum = housenum;
    }

    public String getIslift() {
        return islift;
    }

    public void setIslift(String islift) {
        this.islift = islift;
    }

    public String getEstatecode() {
        return estatecode;
    }

    public void setEstatecode(String estatecode) {
        this.estatecode = estatecode;
    }
}
