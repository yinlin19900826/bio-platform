package com.biocome.platform.admin.vo.lesseecard;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

/**
 * @author hxy
 * @date 2019/5/17 10:26
 */
@ApiModel(value = "请求租户开卡信息列表")
public class LesseecardListReq {
    /**
     * 楼栋编号
     */
    @ApiModelProperty(value = "楼栋编号", notes = "楼栋编号")
    private String buildcode;
    /**
     * 房屋编号
     */
    @ApiModelProperty(value = "房屋编号", notes = "房屋编号")
    private String housecode;
    /**
     * 租户姓名
     */
    @ApiModelProperty(value = "租户姓名", notes = "租户姓名")
    private String username;
    /**
     * 门禁卡号
     */
    @ApiModelProperty(value = "门禁卡号", notes = "门禁卡号")
    private String cardno;
    /**
     * 是否有效(0、注销，1、有效，2、发卡 3、禁用 4、黑名单)'
     */
    @ApiModelProperty(value = "是否有效", notes = "是否有效")
    private Integer isalive;
    /**
     * 租户性别(0男 1女 2未知)
     */
    @ApiModelProperty(value = "是否有效", notes = "是否有效")
    private Integer usersex;


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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    public Integer getIsalive() {
        return isalive;
    }

    public void setIsalive(Integer isalive) {
        this.isalive = isalive;
    }

    public Integer getUsersex() {
        return usersex;
    }

    public void setUsersex(Integer usersex) {
        this.usersex = usersex;
    }
}
