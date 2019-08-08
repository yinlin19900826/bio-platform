package com.biocome.platform.wechatapplet.vo.userdetail;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author hxy
 * @date 2019/8/1 11:24
 */
@ApiModel(value = "用户详情请求")
public class UserDetailReq {
    @ApiModelProperty(value = "证件照")
    private String papersphoto;
    @ApiModelProperty(value = "手持证件照")
    private String photo;
    @ApiModelProperty(value = "用户姓名")
    private String username;
    @ApiModelProperty(value = "用户性别")
    private String sex;
    @ApiModelProperty(value = "民族")
    private String nation;
    @ApiModelProperty(value = "生日")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    @ApiModelProperty(value = "户籍地址")
    private String domicileaddress;
    @ApiModelProperty(value = "证件类型")
    private String paperstype;
    @ApiModelProperty(value = "证件号")
    private String papersnum;
    @ApiModelProperty(value = "无证原因")
    private String nopaperreason;
    @ApiModelProperty(value = "入住日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date checkintime;
    @ApiModelProperty(value = "门禁物理卡号")
    private String cardno;
    @ApiModelProperty(value = "楼栋编号")
    private String buildcode;
    @ApiModelProperty(value = "社区编号")
    private String estatecode;
    @ApiModelProperty(value = "房间编号")
    private String housecode;
    @ApiModelProperty(value = "登记人")
    private String registrant;
    @ApiModelProperty(value = "有无证件0有1无")
    private Integer ispapers;

    public Integer getIspapers() {
        return ispapers;
    }

    public void setIspapers(Integer ispapers) {
        this.ispapers = ispapers;
    }

    public String getPapersphoto() {
        return papersphoto;
    }

    public void setPapersphoto(String papersphoto) {
        this.papersphoto = papersphoto;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }
    @JSONField(format = "yyyy-MM-dd")
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getDomicileaddress() {
        return domicileaddress;
    }

    public void setDomicileaddress(String domicileaddress) {
        this.domicileaddress = domicileaddress;
    }

    public String getPaperstype() {
        return paperstype;
    }

    public void setPaperstype(String paperstype) {
        this.paperstype = paperstype;
    }

    public String getPapersnum() {
        return papersnum;
    }

    public void setPapersnum(String papersnum) {
        this.papersnum = papersnum;
    }

    public String getNopaperreason() {
        return nopaperreason;
    }

    public void setNopaperreason(String nopaperreason) {
        this.nopaperreason = nopaperreason;
    }
    @JSONField(format = "yyyy-MM-dd")
    public Date getCheckintime() {
        return checkintime;
    }

    public void setCheckintime(Date checkintime) {
        this.checkintime = checkintime;
    }

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
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

    public String getHousecode() {
        return housecode;
    }

    public void setHousecode(String housecode) {
        this.housecode = housecode;
    }

    public String getRegistrant() {
        return registrant;
    }

    public void setRegistrant(String registrant) {
        this.registrant = registrant;
    }
}
