package com.biocome.platform.inter.basemanager.vo.lesseecard;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author hxy
 * @date 2019/5/16 11:24
 */
@ApiModel(value = "租户开卡信息")
public class LesseeCardVo {
    /**
     * 用户姓名
     */
    @ApiModelProperty(value = "用户姓名", notes = "用户姓名")
    private String username;

    /**
     * 用户编号
     */
    @ApiModelProperty(value = "用户编号", notes = "用户编号")
    private String usercode;
    /**
     * 用户性别(0男1女2未知)
     */
    @ApiModelProperty(value = "用户性别(0男1女2未知)", notes = "用户性别(0男1女2未知)")
    private Integer usersex;
    /**
     * 出生日期
     */

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "出生日期", notes = "出生日期")
    private Date birthtime;
    /**
     * 证件类型(0身份证1港澳台通行证2护照3其他)
     */
    @ApiModelProperty(value = "证件类型(0身份证1港澳台通行证2护照3其他)", notes = "证件类型(0身份证1港澳台通行证2护照3其他)")
    private Integer paperstype;

    /**
     * 证件号码
     */
    @ApiModelProperty(value = "证件号码", notes = "证件号码")
    private String papersnum;
    /**
     * 户籍地址
     */
    @ApiModelProperty(value = "户籍地址", notes = "户籍地址")
    private String domicileaddress;
    /**
     * 门禁卡号（物理卡号）
     */
    @ApiModelProperty(value = "门禁卡号（物理卡号）", notes = "门禁卡号（物理卡号）")
    private String cardno;
    /**
     * 逻辑卡号
     */
    @ApiModelProperty(value = "逻辑卡号", notes = "逻辑卡号")
    private String logicCardno;
    /**
     * 小区编号
     */
    @ApiModelProperty(value = "小区编号", notes = "小区编号")
    private String estatecode;

    /**
     * 小区名称
     */
    @ApiModelProperty(value = "小区名称", notes = "小区名称")
    private String estatename;

    /**
     * 楼栋编号
     */
    @ApiModelProperty(value = "楼栋编号", notes = "楼栋编号")
    private String buildcode;

    /**
     * 楼栋名称
     */
    @ApiModelProperty(value = "楼栋名称", notes = "楼栋名称")
    private String buildname;

    /**
     * 人像照
     */
    @ApiModelProperty(value = "人像照", notes = "人像照")
    private String photo;

    /**
     * 头像照
     */
    @ApiModelProperty(value = "头像照", notes = "头像照")
    private String headphoto;

    /**
     * 证件照
     */
    @ApiModelProperty(value = "证件照", notes = "证件照")
    private String papersphoto;

    /**
     * 卡状态
     */
    @ApiModelProperty(value = "卡状态", notes = "卡状态")
    private String cardtype;
    /**
     * 卡种类
     */
    @ApiModelProperty(value = "卡种类", notes = "卡种类")
    private String cardkind;
    /**
     * 房屋编号
     */
    @ApiModelProperty(value = "房屋编号", notes = "房屋编号")
    private String housecode;
    /**
     * 开卡人编号
     */
    @ApiModelProperty(value = "开卡人编号", notes = "开卡人编号")
    private String admincode;
    /**
     * 是否激活（0否，1是）
     */
    @ApiModelProperty(value = "是否激活（0否，1是）", notes = "是否激活（0否，1是）")
    private String ifactivate;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public Integer getUsersex() {
        return usersex;
    }

    public void setUsersex(Integer usersex) {
        this.usersex = usersex;
    }

    public Date getBirthtime() {
        return birthtime;
    }

    public void setBirthtime(Date birthtime) {
        this.birthtime = birthtime;
    }

    public Integer getPaperstype() {
        return paperstype;
    }

    public void setPaperstype(Integer paperstype) {
        this.paperstype = paperstype;
    }

    public String getPapersnum() {
        return papersnum;
    }

    public void setPapersnum(String papersnum) {
        this.papersnum = papersnum;
    }

    public String getDomicileaddress() {
        return domicileaddress;
    }

    public void setDomicileaddress(String domicileaddress) {
        this.domicileaddress = domicileaddress;
    }

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    public String getLogicCardno() {
        return logicCardno;
    }

    public void setLogicCardno(String logicCardno) {
        this.logicCardno = logicCardno;
    }

    public String getEstatecode() {
        return estatecode;
    }

    public void setEstatecode(String estatecode) {
        this.estatecode = estatecode;
    }

    public String getEstatename() {
        return estatename;
    }

    public void setEstatename(String estatename) {
        this.estatename = estatename;
    }

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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getHeadphoto() {
        return headphoto;
    }

    public void setHeadphoto(String headphoto) {
        this.headphoto = headphoto;
    }

    public String getPapersphoto() {
        return papersphoto;
    }

    public void setPapersphoto(String papersphoto) {
        this.papersphoto = papersphoto;
    }

    public String getCardtype() {
        return cardtype;
    }

    public void setCardtype(String cardtype) {
        this.cardtype = cardtype;
    }

    public String getCardkind() {
        return cardkind;
    }

    public void setCardkind(String cardkind) {
        this.cardkind = cardkind;
    }

    public String getHousecode() {
        return housecode;
    }

    public void setHousecode(String housecode) {
        this.housecode = housecode;
    }

    public String getAdmincode() {
        return admincode;
    }

    public void setAdmincode(String admincode) {
        this.admincode = admincode;
    }

    public String getIfactivate() {
        return ifactivate;
    }

    public void setIfactivate(String ifactivate) {
        this.ifactivate = ifactivate;
    }
}
