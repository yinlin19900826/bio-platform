package com.biocome.platform.wechatapplet.vo.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author yinlin
 * @date 2019/8/8 20:16
 */
@ApiModel(value = "更改房间号入参")
public class ChangeRoomVo {

    /**
     * 租户姓名
     */
    @ApiModelProperty(value="租户姓名")
    private String usercode;
    /**
     * 逻辑卡号
     */
    @ApiModelProperty(value="物理卡号")
    private String physicalcardno;

    /**
     * 楼栋名
     */
    @ApiModelProperty(value="楼栋名")
    private String buildname;

    /**
     * 楼栋编号
     */
    @ApiModelProperty(value="楼栋编号")
    private String buildcode;

    /**
     * 房间编号
     */
    @ApiModelProperty(value="房间编号")
    private String housecode;

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getPhysicalcardno() {
        return physicalcardno;
    }

    public void setPhysicalcardno(String physicalcardno) {
        this.physicalcardno = physicalcardno;
    }

    public String getBuildname() {
        return buildname;
    }

    public void setBuildname(String buildname) {
        this.buildname = buildname;
    }

    public String getBuildcode() {
        return buildcode;
    }

    public void setBuildcode(String buildcode) {
        this.buildcode = buildcode;
    }

    public String getHousecode() {
        return housecode;
    }

    public void setHousecode(String housecode) {
        this.housecode = housecode;
    }
}
