package com.biocome.platform.admin.vo.lesseecard;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author hxy
 * @date 2019/5/17 14:05
 */
@ApiModel(value = "请求租户开卡返回列表")
public class LesseecardListResp {
    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id", notes = "主键id")
    private Integer id;
    /**
     * 物理卡号
     */
    @ApiModelProperty(value = "物理卡号", notes = "物理卡号")
    private String physicalCardno;
    /**
     * 用户编号
     */
    @ApiModelProperty(value = "用户编号", notes = "用户编号")
    private String usercode;
    /**
     * 用户姓名
     */
    @ApiModelProperty(value = "用户姓名", notes = "用户姓名")
    private String userName;
    /**
     * 楼栋编号
     */
    @ApiModelProperty(value = "楼栋编号", notes = "楼栋编号")
    private String buildcode;
    /**
     * 楼栋名
     */
    @ApiModelProperty(value = "楼栋名", notes = "楼栋名")
    private String buildName;
    /**
     * 用户性别
     */
    @ApiModelProperty(value = "用户性别", notes = "用户性别")
    private String usersex;
    /**
     * 卡状态
     */
    @ApiModelProperty(value = "卡状态", notes = "卡状态")
    private String isalive;
    /**
     * 卡类别
     */
    @ApiModelProperty(value = "卡类别", notes = "卡类别")
    private String cardtype;
    /**
     * 入住日期
     */
    @ApiModelProperty(value = "入住日期", notes = "入住日期")
    private String checkintime;
    /**
     * 登记时间
     */
    @ApiModelProperty(value = "登记时间", notes = "登记时间")
    private String registertime;

    /**
     * 卡种类
     */
    @ApiModelProperty(value = "卡种类", notes = "卡种类")
    private String cardkind;

    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号码", notes = "手机号码")
    private String tel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getUsersex() {
        return usersex;
    }

    public void setUsersex(String usersex) {
        this.usersex = usersex;
    }

    public String getCardtype() {
        return cardtype;
    }

    public void setCardtype(String cardtype) {
        this.cardtype = cardtype;
    }

    public String getCheckintime() {
        return checkintime;
    }

    public void setCheckintime(String checkintime) {
        this.checkintime = checkintime;
    }

    public String getRegistertime() {
        return registertime;
    }

    public void setRegistertime(String registertime) {
        this.registertime = registertime;
    }

    public String getCardkind() {
        return cardkind;
    }

    public void setCardkind(String cardkind) {
        this.cardkind = cardkind;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
