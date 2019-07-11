package com.biocome.platform.admin.vo;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @ClassName: LesseeVo
 * @Author: shenlele
 * @Date: 2019/5/7 17:50
 * @Description:
 */
@ApiModel(value = "远程同步租户返回实体类")
public class LesseeVo {
    /**
     * 用户姓名
     */
    @ApiModelProperty(value = "用户姓名")
    private String username;
    /**
     * 用户编号
     */
    @ApiModelProperty(value = "用户编号")
    private String usercode;
    /**
     * 用户性别(0男1女2未知)
     */
    @ApiModelProperty(value = "用户性别(0男1女2未知)")
    private Integer usersex;
    /**
     * 年龄类型（0:16岁以下，1:16-65岁，2:65岁以上）
     */
    @ApiModelProperty(value = "年龄类型（0:16岁以下，1:16-65岁，2:65岁以上）")
    private Integer agetype;
    /**
     * 证件号码
     */
    @ApiModelProperty(value = "证件号码")
    private String papersnum;
    /**
     * 小区编号
     */
    @ApiModelProperty(value = "小区编号")
    private String estatecode;
    /**
     * 职业
     */
    @ApiModelProperty(value = "职业")
    private String occupation;
    /**
     * 政治面貌
     */
    @ApiModelProperty(value = "政治面貌")
    private String politicsstatus;
    /**
     * 是否注销(0是1否)
     */
    @ApiModelProperty(value = "是否注销(0是1否)")
    private Integer islogout;
    /**
     * 出生开始时间
     */
    @ApiModelProperty(value = "出生开始时间")
    private Date birthbegintime;

    /**
     * 出生结束时间
     */
    @ApiModelProperty(value = "出生结束时间")
    private Date birthendtime;

    /**
     * 登记开始时间
     */
    @ApiModelProperty(value = "登记开始时间")
    private Date registerbegintime;

    /**
     * 登记结束时间
     */
    @ApiModelProperty(value = "登记结束时间")
    private Date registerendtime;

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

    public String getPapersnum() {
        return papersnum;
    }

    public void setPapersnum(String papersnum) {
        this.papersnum = papersnum;
    }

    public String getEstatecode() {
        return estatecode;
    }

    public void setEstatecode(String estatecode) {
        this.estatecode = estatecode;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getPoliticsstatus() {
        return politicsstatus;
    }

    public void setPoliticsstatus(String politicsstatus) {
        this.politicsstatus = politicsstatus;
    }

    public Integer getIslogout() {
        return islogout;
    }

    public void setIslogout(Integer islogout) {
        this.islogout = islogout;
    }

    /**
     * 获取年龄类型（0:16岁以下，1:16-65岁，2:65岁以上）
     *
     * @return agetype - 年龄类型（0:16岁以下，1:16-65岁，2:65岁以上）
     */
    public Integer getAgetype() {
        return agetype;
    }

    /**
     * 设置年龄类型（0:16岁以下，1:16-65岁，2:65岁以上）
     *
     * @param agetype 年龄类型（0:16岁以下，1:16-65岁，2:65岁以上）
     */
    public void setAgetype(Integer agetype) {
        this.agetype = agetype;
    }

    /**
     * 获取出生日期开始时间
     *
     * @return birthbegintime - 出生日期开始时间
     */
    @JSONField(serialize = false, format = "yyyy-MM-dd HH:mm:ss")
    public Date getBirthbegintime() {
        return birthbegintime;
    }

    /**
     * 设置出生日期开始时间
     *
     * @param birthbegintime 出生日期开始时间
     */
    public void setBirthbegintime(Date birthbegintime) {
        this.birthbegintime = birthbegintime;
    }

    /**
     * 获取出生日期结束时间
     *
     * @return birthendtime - 出生日期结束时间
     */
    @JSONField(serialize = false, format = "yyyy-MM-dd HH:mm:ss")
    public Date getBirthendtime() {
        return birthendtime;
    }

    /**
     * 设置出生日期结束时间
     *
     * @param birthendtime 出生日期结束时间
     */
    public void setBirthendtime(Date birthendtime) {
        this.birthendtime = birthendtime;
    }

    /**
     * 获取登记日期开始时间
     *
     * @return registerbegintime - 登记日期开始时间
     */
    @JSONField(serialize = false, format = "yyyy-MM-dd HH:mm:ss")
    public Date getRegisterbegintime() {
        return registerbegintime;
    }

    /**
     * 设置登记日期开始时间
     *
     * @param registerbegintime 登记日期开始时间
     */
    public void setRegisterbegintime(Date registerbegintime) {
        this.registerbegintime = registerbegintime;
    }

    /**
     * 获取登记日期结束时间
     *
     * @return registerendtime - 登记日期结束时间
     */
    @JSONField(serialize = false, format = "yyyy-MM-dd HH:mm:ss")
    public Date getRegisterendtime() {
        return registerendtime;
    }

    /**
     * 设置登记日期结束时间
     *
     * @param registerendtime 登记日期结束时间
     */
    public void setRegisterendtime(Date registerendtime) {
        this.registerendtime = registerendtime;
    }
}
