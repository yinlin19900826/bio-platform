package com.biocome.platform.inter.basemanager.vo.inoutRecord;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author hxy
 * @date 2019/5/21 15:10
 */
@ApiModel(value = "门禁刷卡信息返回")
public class InoutRecordForListResp {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "id")
    private String id;
    /**
     * 开门时间
     */
    @ApiModelProperty(value = "开门时间")
    private Date createtime;
    /**
     * 开门方式(1刷卡2动态密码3远程 4视频开门5密码开门6电话开门7按钮开门8非法卡9过期卡)
     */
    @ApiModelProperty(value = "开门方式(1刷卡2动态密码3远程 4视频开门5密码开门6电话开门7按钮开门8非法卡9过期卡)")
    private String opentype;
    /**
     * 开门卡号(动态密码/卡号)
     */
    @ApiModelProperty(value = "开门卡号(动态密码/卡号)")
    private String cardno;
    /**
     * 用户编号
     */
    @ApiModelProperty(value = "用户编号")
    private String usercode;
    /**
     * 用户姓名
     */
    @ApiModelProperty(value = "用户姓名")
    private String username;
    /**
     * 用户性别(0男1女2未知)
     */
    @ApiModelProperty(value = "用户性别(0男1女2未知)", notes = "用户性别(0男1女2未知)")
    private String usersex;
    /**
     * 手机号码
     */
    @ApiModelProperty(value = "电话", notes = "电话")
    private String tel;
    /**
     * 楼栋编码
     */
    @ApiModelProperty(value = "楼栋编码", notes = "楼栋编码")
    private String buildCode;
    /**
     * 楼栋名称
     */
    @ApiModelProperty(value = "楼栋名称", notes = "楼栋名称")
    private String buildName;
    /**
     * 抓拍相片
     */
    @ApiModelProperty(value = "抓拍相片", notes = "抓拍相片")
    private String picpath;
    /**
     * 抓拍视频
     */
    @ApiModelProperty(value = "抓拍视频", notes = "抓拍视频")
    private String videopath;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getOpentype() {
        return opentype;
    }

    public void setOpentype(String opentype) {
        this.opentype = opentype;
    }

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsersex() {
        return usersex;
    }

    public void setUsersex(String usersex) {
        this.usersex = usersex;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getBuildCode() {
        return buildCode;
    }

    public void setBuildCode(String buildCode) {
        this.buildCode = buildCode;
    }

    public String getBuildName() {
        return buildName;
    }

    public void setBuildName(String buildName) {
        this.buildName = buildName;
    }

    public String getPicpath() {
        return picpath;
    }

    public void setPicpath(String picpath) {
        this.picpath = picpath;
    }

    public String getVideopath() {
        return videopath;
    }

    public void setVideopath(String videopath) {
        this.videopath = videopath;
    }
}
