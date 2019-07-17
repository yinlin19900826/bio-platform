package com.biocome.platform.guard.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Auther: hxy
 * @Date:2019/5/7 10:57
 * @Description:
 */
@ApiModel(value = "卡模型")
@Table(name = "base_card")
public class Card {
    /**
     * 主键id
     */
    @ApiModelProperty(value="id")
    @Id
    private Integer id;
    /**
     * 逻辑卡号
     */
    @ApiModelProperty(value="逻辑卡号")
    @Column(name = "logic_cardno")
    private String logicCardno;
    /**
     * 物理卡号
     */
    @ApiModelProperty(value="物理卡号")
    @Column(name = "physical_cardno")
    private String physicalCardno;
    /**
     * 用户编号
     */
    @ApiModelProperty(value="用户编号")
    @Column(name = "usercode")
    private String usercode;
    /**
     * 开卡人编号
     */
    @ApiModelProperty(value="开卡人编号")
    @Column(name = "admincode")
    private String admincode;
    /**
     * 楼栋编号
     */
    @ApiModelProperty(value="楼栋编号")
    @Column(name = "buildcode")
    private String buildcode;

    /**
     * 楼栋编号
     */
    @ApiModelProperty(value="楼栋编号")
    @Column(name = "housecode")
    private String housecode;
    /**
     * 是否启用
     */
    @ApiModelProperty(value="是否启用")
    @Column(name = "isalive")
    private String isalive;
    /**
     * 创建时间
     */
    @ApiModelProperty(value="创建时间")
    @Column(name = "createtime")
    private Date createtime;
    /**
     * 设备编号
     */
    @ApiModelProperty(value="设备编号")
    @Column(name = "sn")
    private String sn;
    /**
     * 卡状态
     */
    @ApiModelProperty(value="卡状态")
    @Column(name = "cardtype")
    private String cardtype;
    /**
     * 卡种类
     */
    @ApiModelProperty(value="卡种类")
    @Column(name = "cardkind")
    private String cardkind;

    @ApiModelProperty(value="楼栋名称")
    //楼栋名称
    @Column(name = "buildname")
    private String buildname;

    @ApiModelProperty(value="用户名称")
    //用户姓名
    @Column(name = "username")
    private String username;

    @ApiModelProperty(value="电话号码")
    //手机号码
    @Column(name = "phone")
    private String phone;

    @ApiModelProperty(value="备注")
    //备注信息
    @Column(name = "remark")
    private String remark;



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

    public String getBuildcode() {
        return buildcode;
    }

    public void setBuildcode(String buildcode) {
        this.buildcode = buildcode;
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

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
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

    public String getAdmincode() {
        return admincode;
    }

    public void setAdmincode(String admincode) {
        this.admincode = admincode;
    }

    public String getHousecode() {
        return housecode;
    }

    public void setHousecode(String housecode) {
        this.housecode = housecode;
    }

    public String getBuildname() {
        return buildname;
    }

    public void setBuildname(String buildname) {
        this.buildname = buildname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
