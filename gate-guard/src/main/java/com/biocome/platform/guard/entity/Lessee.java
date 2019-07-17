package com.biocome.platform.guard.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.biocome.platform.common.entity.Base;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @ClassName: Lessee
 * @Author: shenlele
 * @Date: 2019/5/8 11:25
 * @Description:
 */
@ApiModel(value = "租户实体类")
@Table(name = "base_lessee")
public class Lessee extends Base {
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
     * 用户别名
     */
    @ApiModelProperty(value = "用户别名")
    private String useralias;

    /**
     * 用户性别(0男1女2未知)
     */
    @ApiModelProperty(value = "用户性别(0男1女2未知)")
    private Integer usersex;

    /**
     * 人员类型(0：房东1：业主2：家人3：租客4：临时客人)
     */
    @ApiModelProperty(value = "人员类型(0：房东1：业主2：家人3：租客4：临时客人)")
    private Integer usertype;

    /**
     * 是否关爱人员(0是1否)
     */
    @ApiModelProperty(value = "是否关爱人员(0是1否)")
    private Integer iscarepeople;

    /**
     * 证件类型(0身份证1港澳台通行证2护照3其他)
     */
    @ApiModelProperty(value = "证件类型(0身份证1港澳台通行证2护照3其他)")
    private Integer paperstype;

    /**
     * 证件号码
     */
    @ApiModelProperty(value = "证件号码")
    private String papersnum;

    /**
     * 民族
     */
    @ApiModelProperty(value = "民族")
    private String nation;

    /**
     * 籍贯
     */
    @ApiModelProperty(value = "籍贯")
    private String nativeplace;

    /**
     * 户籍地址
     */
    @ApiModelProperty(value = "户籍地址")
    private String domicileaddress;

    /**
     * 国籍
     */
    @ApiModelProperty(value = "国籍")
    private String nationality;

    /**
     * 有效时间
     */
    @ApiModelProperty(value = "有效时间")
    private Date validtime;

    /**
     * 政治面貌(1中共党员2中共预备党员3共青团员4民革党员5致公党党员6九三学社社员7台盟盟员8民盟盟员9民建会员10民进会员11农工党党员12群众)
     */
    @ApiModelProperty(value = "政治面貌(1中共党员2中共预备党员3共青团员4民革党员5致公党党员6九三学社社员7台盟盟员8民盟盟员9民建会员10民进会员11农工党党员12群众)")
    private Integer politicsstatus;

    /**
     * 人像照
     */
    @ApiModelProperty(value = "人像照")
    private String photo;

    /**
     * 头像照
     */
    @ApiModelProperty(value = "头像照")
    private String headphoto;

    /**
     * 证件照
     */
    @ApiModelProperty(value = "证件照")
    private String papersphoto;

    /**
     * 职业
     */
    @ApiModelProperty(value = "职业")
    private String occupation;

    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号码")
    private String tel;

    /**
     * 紧急联系人姓名
     */
    @ApiModelProperty(value = "紧急联系人姓名")
    private String emergencyname;

    /**
     * 紧急联系人电话
     */
    @ApiModelProperty(value = "紧急联系人电话")
    private String emergencytel;

    /**
     * 小区编号
     */
    @ApiModelProperty(value = "小区编号")
    private String estatecode;

    /**
     * 小区名称
     */
    @ApiModelProperty(value = "小区名称")
    private String estatename;

    /**
     * 楼栋编号
     */
    @ApiModelProperty(value = "楼栋编号")
    private String buildcode;

    /**
     * 楼栋名称
     */
    @ApiModelProperty(value = "楼栋名称")
    private String buildname;

    /**
     * 单元编号
     */
    @ApiModelProperty(value = "单元编号")
    private String unitcode;

    /**
     * 单元名称
     */
    @ApiModelProperty(value = "单元名称")
    private String unitname;

    /**
     * 居住房屋编码
     */
    @ApiModelProperty(value = "居住房屋编码")
    private String housecode;

    /**
     * 居住地地址
     */
    @ApiModelProperty(value = "居住地地址")
    private String resideaddress;

    /**
     * 出生日期
     */
    @ApiModelProperty(value = "出生日期")
    private Date birthtime;

    /**
     * 是否注销(0是1否)
     */
    @ApiModelProperty(value = "是否注销(0是1否)")
    private Integer islogout;

    /**
     * 注销人
     */
    @ApiModelProperty(value = "注销人")
    private String logoutpeople;

    /**
     * 注销日期
     */
    @ApiModelProperty(value = "注销日期")
    private Date logouttime;

    /**
     * 离开日期
     */
    @ApiModelProperty(value = "离开日期")
    private Date leavetime;

    /**
     * 入住日期
     */
    @ApiModelProperty(value = "入住日期")
    private Date checkintime;

    /**
     * 是否发卡(0是1否)
     */
    @ApiModelProperty(value = "是否发卡(0是1否)")
    private Integer iscard;

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
     * 获取用户别名
     *
     * @return useralias - 用户别名
     */
    public String getUseralias() {
        return useralias;
    }

    /**
     * 设置用户别名
     *
     * @param useralias 用户别名
     */
    public void setUseralias(String useralias) {
        this.useralias = useralias;
    }

    /**
     * 获取用户性别(0未知1男2女)
     *
     * @return usersex - 用户性别(0未知1男2女)
     */
    public Integer getUsersex() {
        return usersex;
    }

    /**
     * 设置用户性别(0未知1男2女)
     *
     * @param usersex 用户性别(0未知1男2女)
     */
    public void setUsersex(Integer usersex) {
        this.usersex = usersex;
    }

    /**
     * 获取是否关爱人员(0是1否)
     *
     * @return iscarepeople - 是否关爱人员(0是1否)
     */
    public Integer getIscarepeople() {
        return iscarepeople;
    }

    /**
     * 设置是否关爱人员(0是1否)
     *
     * @param iscarepeople 是否关爱人员(0是1否)
     */
    public void setIscarepeople(Integer iscarepeople) {
        this.iscarepeople = iscarepeople;
    }

    /**
     * 获取证件类型(0身份证1港澳台通行证2护照3其他)
     *
     * @return paperstype - 证件类型(0身份证1港澳台通行证2护照3其他)
     */
    public Integer getPaperstype() {
        return paperstype;
    }

    /**
     * 设置证件类型(0身份证1港澳台通行证2护照3其他)
     *
     * @param paperstype 证件类型(0身份证1港澳台通行证2护照3其他)
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
     * 获取民族
     *
     * @return nation - 民族
     */
    public String getNation() {
        return nation;
    }

    /**
     * 设置民族
     *
     * @param nation 民族
     */
    public void setNation(String nation) {
        this.nation = nation;
    }

    /**
     * 获取籍贯
     *
     * @return nativeplace - 籍贯
     */
    public String getNativeplace() {
        return nativeplace;
    }

    /**
     * 设置籍贯
     *
     * @param nativeplace 籍贯
     */
    public void setNativeplace(String nativeplace) {
        this.nativeplace = nativeplace;
    }

    /**
     * 获取国籍
     *
     * @return nationality - 国籍
     */
    public String getNationality() {
        return nationality;
    }

    /**
     * 设置国籍
     *
     * @param nationality 国籍
     */
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    /**
     * 获取有效时间
     *
     * @return validtime - 有效时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    public Date getValidtime() {
        return validtime;
    }

    /**
     * 设置有效时间
     *
     * @param validtime 有效时间
     */
    public void setValidtime(Date validtime) {
        this.validtime = validtime;
    }

    /**
     * 获取政治面貌(1中共党员2中共预备党员3共青团员4民革党员5致公党党员6九三学社社员7台盟盟员8民盟盟员9民建会员10民进会员11农工党党员12群众)
     *
     * @return politicsstatus - 政治面貌(1中共党员2中共预备党员3共青团员4民革党员5致公党党员6九三学社社员7台盟盟员8民盟盟员9民建会员10民进会员11农工党党员12群众)
     */
    public Integer getPoliticsstatus() {
        return politicsstatus;
    }

    /**
     * 设置政治面貌(1中共党员2中共预备党员3共青团员4民革党员5致公党党员6九三学社社员7台盟盟员8民盟盟员9民建会员10民进会员11农工党党员12群众)
     *
     * @param politicsstatus 政治面貌(1中共党员2中共预备党员3共青团员4民革党员5致公党党员6九三学社社员7台盟盟员8民盟盟员9民建会员10民进会员11农工党党员12群众)
     */
    public void setPoliticsstatus(Integer politicsstatus) {
        this.politicsstatus = politicsstatus;
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
     * 获取职业
     *
     * @return occupation - 职业
     */
    public String getOccupation() {
        return occupation;
    }

    /**
     * 设置职业
     *
     * @param occupation 职业
     */
    public void setOccupation(String occupation) {
        this.occupation = occupation;
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
     * 获取紧急联系人姓名
     *
     * @return emergencyname - 紧急联系人姓名
     */
    public String getEmergencyname() {
        return emergencyname;
    }

    /**
     * 设置紧急联系人姓名
     *
     * @param emergencyname 紧急联系人姓名
     */
    public void setEmergencyname(String emergencyname) {
        this.emergencyname = emergencyname;
    }

    /**
     * 获取紧急联系人电话
     *
     * @return emergencytel - 紧急联系人电话
     */
    public String getEmergencytel() {
        return emergencytel;
    }

    /**
     * 设置紧急联系人电话
     *
     * @param emergencytel 紧急联系人电话
     */
    public void setEmergencytel(String emergencytel) {
        this.emergencytel = emergencytel;
    }

    /**
     * 获取小区编号
     *
     * @return estatecode - 小区编号
     */
    public String getEstatecode() {
        return estatecode;
    }

    /**
     * 设置小区编号
     *
     * @param estatecode 小区编号
     */
    public void setEstatecode(String estatecode) {
        this.estatecode = estatecode;
    }

    /**
     * 获取小区名称
     *
     * @return estatename - 小区名称
     */
    public String getEstatename() {
        return estatename;
    }

    /**
     * 设置小区名称
     *
     * @param estatename 小区名称
     */
    public void setEstatename(String estatename) {
        this.estatename = estatename;
    }

    /**
     * 获取楼栋编号
     *
     * @return buildcode - 楼栋编号
     */
    public String getBuildcode() {
        return buildcode;
    }

    /**
     * 设置楼栋编号
     *
     * @param buildcode 楼栋编号
     */
    public void setBuildcode(String buildcode) {
        this.buildcode = buildcode;
    }

    /**
     * 获取楼栋名称
     *
     * @return buildname - 楼栋名称
     */
    public String getBuildname() {
        return buildname;
    }

    /**
     * 设置楼栋名称
     *
     * @param buildname 楼栋名称
     */
    public void setBuildname(String buildname) {
        this.buildname = buildname;
    }

    /**
     * 获取单元编号
     *
     * @return unitcode - 单元编号
     */
    public String getUnitcode() {
        return unitcode;
    }

    /**
     * 设置单元编号
     *
     * @param unitcode 单元编号
     */
    public void setUnitcode(String unitcode) {
        this.unitcode = unitcode;
    }

    /**
     * 获取单元名称
     *
     * @return unitname - 单元名称
     */
    public String getUnitname() {
        return unitname;
    }

    /**
     * 设置单元名称
     *
     * @param unitname 单元名称
     */
    public void setUnitname(String unitname) {
        this.unitname = unitname;
    }

    /**
     * 获取居住房屋编码
     *
     * @return housecode - 居住房屋编码
     */
    public String getHousecode() {
        return housecode;
    }

    /**
     * 设置居住房屋编码
     *
     * @param housecode 居住房屋编码
     */
    public void setHousecode(String housecode) {
        this.housecode = housecode;
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
     * 获取出生日期
     *
     * @return birthtime - 出生日期
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    public Date getBirthtime() {
        return birthtime;
    }

    /**
     * 设置出生日期
     *
     * @param birthtime 出生日期
     */
    public void setBirthtime(Date birthtime) {
        this.birthtime = birthtime;
    }

    /**
     * 获取是否注销(0是1否)
     *
     * @return islogout - 是否注销(0是1否)
     */
    public Integer getIslogout() {
        return islogout;
    }

    /**
     * 设置是否注销(0是1否)
     *
     * @param islogout 是否注销(0是1否)
     */
    public void setIslogout(Integer islogout) {
        this.islogout = islogout;
    }

    /**
     * 获取注销人
     *
     * @return logoutpeople - 注销人
     */
    public String getLogoutpeople() {
        return logoutpeople;
    }

    /**
     * 设置注销人
     *
     * @param logoutpeople 注销人
     */
    public void setLogoutpeople(String logoutpeople) {
        this.logoutpeople = logoutpeople;
    }

    /**
     * 获取注销日期
     *
     * @return logouttime - 注销日期
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    public Date getLogouttime() {
        return logouttime;
    }

    /**
     * 设置注销日期
     *
     * @param logouttime 注销日期
     */
    public void setLogouttime(Date logouttime) {
        this.logouttime = logouttime;
    }

    /**
     * 获取离开日期
     *
     * @return leavetime - 离开日期
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    public Date getLeavetime() {
        return leavetime;
    }

    /**
     * 设置离开日期
     *
     * @param leavetime 离开日期
     */
    public void setLeavetime(Date leavetime) {
        this.leavetime = leavetime;
    }

    /**
     * 获取入住日期
     *
     * @return checkintime - 入住日期
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    public Date getCheckintime() {
        return checkintime;
    }

    /**
     * 设置入住日期
     *
     * @param checkintime 入住日期
     */
    public void setCheckintime(Date checkintime) {
        this.checkintime = checkintime;
    }

    /**
     * 获取是否发卡(0是1否)
     *
     * @return iscard - 是否发卡(0是1否)
     */
    public Integer getIscard() {
        return iscard;
    }

    /**
     * 设置是否发卡(0是1否)
     *
     * @param iscard 是否发卡(0是1否)
     */
    public void setIscard(Integer iscard) {
        this.iscard = iscard;
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

    public Integer getUsertype() {
        return usertype;
    }

    public void setUsertype(Integer usertype) {
        this.usertype = usertype;
    }

    public Integer getIsapp() {
        return isapp;
    }

    public void setIsapp(Integer isapp) {
        this.isapp = isapp;
    }

    public String getDomicileaddress() {
        return domicileaddress;
    }

    public void setDomicileaddress(String domicileaddress) {
        this.domicileaddress = domicileaddress;
    }

    public String getHeadphoto() {
        return headphoto;
    }

    public void setHeadphoto(String headphoto) {
        this.headphoto = headphoto;
    }
}
