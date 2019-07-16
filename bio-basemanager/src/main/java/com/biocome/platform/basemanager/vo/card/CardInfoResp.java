package com.biocome.platform.basemanager.vo.card;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author hxy
 * @date 2019/5/8 20:16
 */
@ApiModel(value = "卡信息返回")
public class CardInfoResp {
    /**
     * 主键id
     */
    @ApiModelProperty(value="id")
    private Integer id;
    /**
     * 逻辑卡号
     */
    @ApiModelProperty(value="逻辑卡号")
    private String logicCardno;
    /**
     * 物理卡号
     */
    @ApiModelProperty(value="物理卡号")
    private String physicalCardno;
    /**
     * 用户编号
     */
    @ApiModelProperty(value="用户编号")
    private String usercode;
    /**
     * 用户姓名
     */
    @ApiModelProperty(value="用户姓名")
    private String userName;
    /**
     * 楼栋编号
     */
    @ApiModelProperty(value="楼栋编号")
    private String buildcode;
    /**
     * 楼栋名
     */
    @ApiModelProperty(value="楼栋名")
    private String buildName;
    /**
     * 卡状态
     */
    @ApiModelProperty(value="卡状态")
    private String isalive;
    /**
     * 创建时间
     */
    @ApiModelProperty(value="创建时间")
    private Date createtime;
    /**
     * 卡类别
     */
    @ApiModelProperty(value="卡类别")
    private String cardtype;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogicCardno() {
        return logicCardno;
    }

    public void setLogicCardno(String logicCardno) {
        this.logicCardno = logicCardno;
    }

    public String getPhysicalCardno() {
        return physicalCardno;
    }

    public void setPhysicalCardno(String physicalCardno) {
        this.physicalCardno = physicalCardno;
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBuildcode() {
        return buildcode;
    }

    public void setBuildcode(String buildcode) {
        this.buildcode = buildcode;
    }

    public String getBuildName() {
        return buildName;
    }

    public void setBuildName(String buildName) {
        this.buildName = buildName;
    }

    public String getIsalive() {
        return isalive;
    }

    public void setIsalive(String isalive) {
        this.isalive = isalive;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getCardtype() {
        return cardtype;
    }

    public void setCardtype(String cardtype) {
        this.cardtype = cardtype;
    }
}
