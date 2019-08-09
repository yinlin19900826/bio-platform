package com.biocome.platform.wechatapplet.vo.build;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;

/**
 * @ClassName: HouseResp
 * @Author: shenlele
 * @Date: 2019/8/7 11:54
 * @Description:
 */
@ApiModel("房间返回实体类")
public class HouseResp {

    @ApiParam("房屋编号")
    private String houseCode;
    @ApiParam("楼栋编号")
    private String buildCode;
    @ApiParam("房屋名称")
    private String houseName;
    @ApiParam("房屋所住人员数量")
    private Integer countLessee;

    public String getHouseCode() {
        return houseCode;
    }

    public void setHouseCode(String houseCode) {
        this.houseCode = houseCode;
    }

    public String getBuildCode() {
        return buildCode;
    }

    public void setBuildCode(String buildCode) {
        this.buildCode = buildCode;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public Integer getCountLessee() {
        return countLessee;
    }

    public void setCountLessee(Integer countLessee) {
        this.countLessee = countLessee;
    }
}
