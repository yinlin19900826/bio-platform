package com.biocome.platform.admin.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.biocome.platform.common.entity.Base;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @ClassName: Landlord
 * @Author: shenlele
 * @Date: 2019/5/8 11:25
 * @Description:
 */
@ApiModel(value = "房东实体类")
@Table(name = "base_landlord")
public class Landlord extends Base {
    /**
     * 主键编号
     */
    @Id
    private Integer id;

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
     * 证件类型(0身份证1港澳通行证2...)
     */
    @ApiModelProperty(value = "证件类型(0身份证1港澳通行证2...)")
    private Integer paperstype;

    /**
     * 证件号码
     */
    @ApiModelProperty(value = "证件号码")
    private String papersnum;

    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号码")
    private String tel;

    /**
     * 人像照
     */
    @ApiModelProperty(value = "人像照")
    private String photo;

    /**
     * 证件照
     */
    @ApiModelProperty(value = "证件照")
    private String papersphoto;

    /**
     * 居住地地址
     */
    @ApiModelProperty(value = "居住地地址")
    private String resideaddress;

    /**
     * 房屋编码
     */
    @ApiModelProperty(value = "房屋编码")
    private String roomcode;

    /**
     * 房东类型（0未知1原房东2管理员3房东家属4网格员5警务人员6治安科7保洁人员8代管9维修员10申报义务人11分局管道12送气人员13集成单位）
     */
    @ApiModelProperty(value = "房东类型（0未知1原房东2管理员3房东家属4网格员5警务人员6治安科7保洁人员8代管9维修员10申报义务人11分局管道12送气人员13集成单位）")
    private Integer landlordtype;

    /**
     * 管理权限（0所有权限1只读权限2无权限）
     */
    @ApiModelProperty(value = "管理权限（0所有权限1只读权限2无权限）")
    private Integer adminjurisdiction;

    /**
     * 是否开通app(0是1否)
     */
    @ApiModelProperty(value = "是否开通app(0是1否)")
    private Integer isapp;

    /**
     * 登记时间
     */
    @ApiModelProperty(value = "登记时间")
    private Date registertime;

    /**
     * 登记人
     */
    @ApiModelProperty(value = "登记人")
    private String registerpeople;

    /**
     * 获取主键编号
     *
     * @return id - 主键编号
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键编号
     *
     * @param id 主键编号
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取用户姓名
     *
     * @return username - 用户姓名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户姓名
     *
     * @param username 用户姓名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    /**
     * 获取用户性别(0男1女2未知)
     *
     * @return usersex - 用户性别(0男1女2未知)
     */
    public Integer getUsersex() {
        return usersex;
    }

    /**
     * 设置用户性别(0男1女2未知)
     *
     * @param usersex 用户性别(0男1女2未知)
     */
    public void setUsersex(Integer usersex) {
        this.usersex = usersex;
    }

    /**
     * 获取证件类型(0身份证1港澳通行证2...)
     *
     * @return paperstype - 证件类型(0身份证1港澳通行证2...)
     */
    public Integer getPaperstype() {
        return paperstype;
    }

    /**
     * 设置证件类型(0身份证1港澳通行证2...)
     *
     * @param paperstype 证件类型(0身份证1港澳通行证2...)
     */
    public void setPaperstype(Integer paperstype) {
        this.paperstype = paperstype;
    }

    /**
     * 获取证件号码
     *
     * @return papersnum - 证件号码
     */
    public String getPapersnum() {
        return papersnum;
    }

    /**
     * 设置证件号码
     *
     * @param papersnum 证件号码
     */
    public void setPapersnum(String papersnum) {
        this.papersnum = papersnum;
    }

    /**
     * 获取手机号码
     *
     * @return tel - 手机号码
     */
    public String getTel() {
        return tel;
    }

    /**
     * 设置手机号码
     *
     * @param tel 手机号码
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * 获取人像照
     *
     * @return photo - 人像照
     */
    public String getPhoto() {
        return photo;
    }

    /**
     * 设置人像照
     *
     * @param photo 人像照
     */
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    /**
     * 获取证件照
     *
     * @return papersphoto - 证件照
     */
    public String getPapersphoto() {
        return papersphoto;
    }

    /**
     * 设置证件照
     *
     * @param papersphoto 证件照
     */
    public void setPapersphoto(String papersphoto) {
        this.papersphoto = papersphoto;
    }

    /**
     * 获取居住地地址
     *
     * @return resideaddress - 居住地地址
     */
    public String getResideaddress() {
        return resideaddress;
    }

    /**
     * 设置居住地地址
     *
     * @param resideaddress 居住地地址
     */
    public void setResideaddress(String resideaddress) {
        this.resideaddress = resideaddress;
    }

    /**
     * 获取房屋编码
     *
     * @return roomcode - 房屋编码
     */
    public String getRoomcode() {
        return roomcode;
    }

    /**
     * 设置房屋编码
     *
     * @param roomcode 房屋编码
     */
    public void setRoomcode(String roomcode) {
        this.roomcode = roomcode;
    }

    /**
     * 获取房东类型（0未知1原房东2管理员3房东家属4网格员5警务人员6治安科7保洁人员8代管9维修员10申报义务人11分局管道12送气人员13集成单位）
     *
     * @return landlordtype - 房东类型（0未知1原房东2管理员3房东家属4网格员5警务人员6治安科7保洁人员8代管9维修员10申报义务人11分局管道12送气人员13集成单位）
     */
    public Integer getLandlordtype() {
        return landlordtype;
    }

    /**
     * 设置房东类型（0未知1原房东2管理员3房东家属4网格员5警务人员6治安科7保洁人员8代管9维修员10申报义务人11分局管道12送气人员13集成单位）
     *
     * @param landlordtype 房东类型（0未知1原房东2管理员3房东家属4网格员5警务人员6治安科7保洁人员8代管9维修员10申报义务人11分局管道12送气人员13集成单位）
     */
    public void setLandlordtype(Integer landlordtype) {
        this.landlordtype = landlordtype;
    }

    /**
     * 获取管理权限（0所有权限1只读权限2无权限）
     *
     * @return adminjurisdiction - 管理权限（0所有权限1只读权限2无权限）
     */
    public Integer getAdminjurisdiction() {
        return adminjurisdiction;
    }

    /**
     * 设置管理权限（0所有权限1只读权限2无权限）
     *
     * @param adminjurisdiction 管理权限（0所有权限1只读权限2无权限）
     */
    public void setAdminjurisdiction(Integer adminjurisdiction) {
        this.adminjurisdiction = adminjurisdiction;
    }

    public Integer getIsapp() {
        return isapp;
    }

    public void setIsapp(Integer isapp) {
        this.isapp = isapp;
    }

    /**
     * 获取登记时间
     *
     * @return registertime - 登记时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    public Date getRegistertime() {
        return registertime;
    }

    /**
     * 设置登记时间
     *
     * @param registertime 登记时间
     */
    public void setRegistertime(Date registertime) {
        this.registertime = registertime;
    }

    /**
     * 获取登记人
     *
     * @return registerpeople - 登记人
     */
    public String getRegisterpeople() {
        return registerpeople;
    }

    /**
     * 设置登记人
     *
     * @param registerpeople 登记人
     */
    public void setRegisterpeople(String registerpeople) {
        this.registerpeople = registerpeople;
    }
}
