package com.biocome.platform.inter.basemanager.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: HouseVo
 * @Author: shenlele
 * @Date: 2019/5/20 14:46
 * @Description:
 */
@ApiModel(value = "展示房屋实体类")
public class HouseVo {

    private Integer id;
    @ApiModelProperty(value = "房屋编码")
    private String housecode;
    @ApiModelProperty(value = "房屋名称")
    private String housename;
    @ApiModelProperty(value = "楼幢编码")
    private String buildcode;
    @ApiModelProperty(value = "小区编码")
    private String estatecode;
    @ApiModelProperty(value = "房屋居住人员总数（用来判断展示有无住户）")
    private String countlessee;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHousecode() {
        return housecode;
    }

    public void setHousecode(String housecode) {
        this.housecode = housecode;
    }

    public String getHousename() {
        return housename;
    }

    public void setHousename(String housename) {
        this.housename = housename;
    }

    public String getBuildcode() {
        return buildcode;
    }

    public void setBuildcode(String buildcode) {
        this.buildcode = buildcode;
    }

    public String getEstatecode() {
        return estatecode;
    }

    public void setEstatecode(String estatecode) {
        this.estatecode = estatecode;
    }

    public String getCountlessee() {
        return countlessee;
    }

    public void setCountlessee(String countlessee) {
        this.countlessee = countlessee;
    }
}
