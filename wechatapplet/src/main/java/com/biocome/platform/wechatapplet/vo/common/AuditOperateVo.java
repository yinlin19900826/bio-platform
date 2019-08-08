package com.biocome.platform.wechatapplet.vo.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

import java.util.Date;

/**
 * @author yinlin
 * @date 2019/8/2 18:16
 */
@ApiModel(value = "审核返回")
public class AuditOperateVo {

    /**
     * 主键id
     */
    @ApiModelProperty(value="id")
    private Integer id;

    /**
     * 租户名
     */
    @ApiModelProperty(value="租户名")
    private String username;

    /**
     * 小区名
     */
    @ApiModelProperty(value="小区名")
    private String estatename;

    /**
     * 楼栋名
     */
    @ApiModelProperty(value="楼栋名")
    private String buildname;

    /**
     * 单元名
     */
    @ApiModelProperty(value="单元名")
    private String unitname;

    /**
     * 创建申请时间
     */
    @ApiModelProperty(value="创建申请时间")
    private Date createtime;

    /**
     * 出生日期
     */
    @ApiModelProperty(value="出生日期")
    private Date birthtime;

    /**
     * 证件类型
     */
    @ApiModelProperty(value="证件类型")
    private Integer paperstype;


    /**
     * 证件号码
     */
    @ApiModelProperty(value="证件号码")
    private String papersnum;

    /**
     * 性别
     */
    @ApiModelProperty(value="性别")
    private Integer usersex;

    /**
     * 民族
     */
    @ApiModelProperty(value="民族")
    private String nation;

    /**
     * 户籍地址
     */
    @ApiModelProperty(value="户籍地址")
    private String domicileaddress;

    /**
     * 婚姻状态
     */
    @ApiModelProperty(value="婚姻状态")
    private Integer maritalstatus;

    /**
     * 户籍类型
     */
    @ApiModelProperty(value="户籍类型")
    private Integer domiciletype;

    /**
     * 文化程度
     */
    @ApiModelProperty(value="文化程度")
    private Integer culture;

    /**
     * 职业
     */
    @ApiModelProperty(value="职业")
    private Integer occupation;


    /**
     * 来深居住事由
     */
    @ApiModelProperty(value="来深居住事由")
    private String reason;

    /**
     * 电话号码
     */
    @ApiModelProperty(value="电话号码")
    private String tel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEstatename() {
        return estatename;
    }

    public void setEstatename(String estatename) {
        this.estatename = estatename;
    }

    public String getBuildname() {
        return buildname;
    }

    public void setBuildname(String buildname) {
        this.buildname = buildname;
    }

    public String getUnitname() {
        return unitname;
    }

    public void setUnitname(String unitname) {
        this.unitname = unitname;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
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

    public Integer getUsersex() {
        return usersex;
    }

    public void setUsersex(Integer usersex) {
        this.usersex = usersex;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getDomicileaddress() {
        return domicileaddress;
    }

    public void setDomicileaddress(String domicileaddress) {
        this.domicileaddress = domicileaddress;
    }

    public Integer getMaritalstatus() {
        return maritalstatus;
    }

    public void setMaritalstatus(Integer maritalstatus) {
        this.maritalstatus = maritalstatus;
    }

    public Integer getDomiciletype() {
        return domiciletype;
    }

    public void setDomiciletype(Integer domiciletype) {
        this.domiciletype = domiciletype;
    }

    public Integer getCulture() {
        return culture;
    }

    public void setCulture(Integer culture) {
        this.culture = culture;
    }

    public Integer getOccupation() {
        return occupation;
    }

    public void setOccupation(Integer occupation) {
        this.occupation = occupation;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }


    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
