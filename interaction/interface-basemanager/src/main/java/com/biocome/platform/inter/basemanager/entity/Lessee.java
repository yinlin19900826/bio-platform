package com.biocome.platform.inter.basemanager.entity;

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
     * 职业 (0：国家公务员1：专业技术人员2：职员3：企业管理人员4：工人5：农名6：学生7：现役军人8：自由职业者9：个体经营者10：无业人员11：离(退休)人员12：其他）
     */
    @ApiModelProperty(value = "职业 (0：国家公务员1：专业技术人员2：职员3：企业管理人员4：工人5：农名6：学生7：现役军人8：自由职业者9：个体经营者10：无业人员11：离(退休)人员12：其他）")
    private Integer occupation;

    /**
     * 行业（0：工业1：商业2：服务业3：交通运输业4：农业养殖业5：建筑业6：其他7：无业8：非劳动年龄）
     */
    @ApiModelProperty(value = "行业（0：工业1：商业2：服务业3：交通运输业4：农业养殖业5：建筑业6：其他7：无业8：非劳动年龄）")
    private Integer industry;

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
     * 户口所在地类型（0：本地城镇1：本地农村2：省内城镇3：省内农村4：省外城镇5：省外农村）
     */
    @ApiModelProperty(value = "户口所在地类型（0：本地城镇1：本地农村2：省内城镇3：省内农村4：省外城镇5：省外农村）")
    private Integer domiciletype;

    /**
     * 婚姻状况（0：未婚1：已婚2：离异）
     */
    @ApiModelProperty(value = "婚姻状况（0：未婚1：已婚2：离异）")
    private Integer maritalstatus;

    /**
     * 文化程度（0：博士1：硕士2：本科3：大专4：中专5：中技6：技工学校7：高中8：初中9：小学10：文盲11：半文盲）
     */
    @ApiModelProperty(value = "文化程度（0：博士1：硕士2：本科3：大专4：中专5：中技6：技工学校7：高中8：初中9：小学10：文盲11：半文盲）")
    private Integer culture;

    /**
     * 工作单位
     */
    @ApiModelProperty(value = "工作单位")
    private String workunit;

    /**
     * 来深圳事由（0：工作1：嫁娶2：旅游）
     */
    @ApiModelProperty(value = "来深圳事由（0：工作1：嫁娶2：旅游）")
    private Integer reason;

    /**
     * 与户主关系（0：合租人1：直系亲属2：承租人）
     */
    @ApiModelProperty(value = "与户主关系（0：合租人1：直系亲属2：承租人）")
    private Integer relation;

    /**
     * 身高
     */
    @ApiModelProperty(value = "身高")
    private String high;

    /**
     * 居住方式（0：合租1：整租2：购买）
     */
    @ApiModelProperty(value = "居住方式（0：合租1：整租2：购买）")
    private Integer residentmanner;

    /**
     * 首次来深日期
     */
    @ApiModelProperty(value = "首次来深日期")
    private Date firsttime;

    /**
     * 生育动态(0：一孩1：二孩2：三孩3：其他4：无)
     */
    @ApiModelProperty(value = "生育动态(0：一孩1：二孩2：三孩3：其他4：无)")
    private Integer fertility;

    /**
     * 避孕动态(0：药具1：结扎2：上环3：无)
     */
    @ApiModelProperty(value = "避孕动态(0：药具1：结扎2：上环3：无)")
    private Integer birthcontrol;

    /**
     * 是否告知办理居住证(0是1否)
     */
    @ApiModelProperty(value = "是否告知办理居住证(0是1否)")
    private Integer sfgzbljzz;

    /**
     * 是否告知地址改写(0是1否)
     */
    @ApiModelProperty(value = "是否告知地址改写(0是1否)")
    private Integer sfgzdzgx;

    /**
     * 是否审核(0待审核1通过2未通过)
     */
    @ApiModelProperty(value = "是否审核(0待审核1通过2未通过)")
    private Integer isaudit;

    /**
     * 无证原因
     */
    @ApiModelProperty(value = "无证原因")
    private String wzyy;

    /**
     * 有无证件(0有1无)
     */
    @ApiModelProperty(value = "有无证件(0有1无)")
    private Integer ispapers;

    /**
     * 居住处所类型 （0：租赁房屋1：旅店2：居民家中3：工地现场4：其他5：自购房6：单位/学校）
     */
    @ApiModelProperty(value = "居住处所类型 （0：租赁房屋1：旅店2：居民家中3：工地现场4：其他5：自购房6：单位/学校）")
    private Integer jzcslx;

    /**
     * 来自地区（0：大陆1：香港2：澳门3：台湾4：外国5：无国籍）
     */
    @ApiModelProperty(value = "来自地区（0：大陆1：香港2：澳门3：台湾4：外国5：无国籍）")
    private Integer lzdq;

    /**
     * 职称（0：正高级职称1：副高级职称2：中级职称3：初级职称4：技术员5：无职称6：其他）
     */
    @ApiModelProperty(value = "职称（0：正高级职称1：副高级职称2：中级职称3：初级职称4：技术员5：无职称6：其他）")
    private Integer zc;

    /**
     * 持卡动态（0：服务证1：婚育证2：无证）
     */
    @ApiModelProperty(value = "持卡动态（0：服务证1：婚育证2：无证）")
    private Integer ckdt;

    /**
     * 手机型号
     */
    @ApiModelProperty(value = "手机型号")
    private String phonemodel;

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
    public Integer getOccupation() {
        return occupation;
    }

    /**
     * 设置职业
     *
     * @param occupation 职业
     */
    public void setOccupation(Integer occupation) {
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

    public Integer getDomiciletype() {
        return domiciletype;
    }

    public void setDomiciletype(Integer domiciletype) {
        this.domiciletype = domiciletype;
    }

    public Integer getMaritalstatus() {
        return maritalstatus;
    }

    public void setMaritalstatus(Integer maritalstatus) {
        this.maritalstatus = maritalstatus;
    }

    public Integer getCulture() {
        return culture;
    }

    public void setCulture(Integer culture) {
        this.culture = culture;
    }

    public String getWorkunit() {
        return workunit;
    }

    public void setWorkunit(String workunit) {
        this.workunit = workunit;
    }

    public Integer getReason() {
        return reason;
    }

    public void setReason(Integer reason) {
        this.reason = reason;
    }

    public Integer getRelation() {
        return relation;
    }

    public void setRelation(Integer relation) {
        this.relation = relation;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public Integer getResidentmanner() {
        return residentmanner;
    }

    public void setResidentmanner(Integer residentmanner) {
        this.residentmanner = residentmanner;
    }

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    public Date getFirsttime() {
        return firsttime;
    }

    public void setFirsttime(Date firsttime) {
        this.firsttime = firsttime;
    }

    public Integer getFertility() {
        return fertility;
    }

    public void setFertility(Integer fertility) {
        this.fertility = fertility;
    }

    public Integer getBirthcontrol() {
        return birthcontrol;
    }

    public void setBirthcontrol(Integer birthcontrol) {
        this.birthcontrol = birthcontrol;
    }

    public Integer getSfgzbljzz() {
        return sfgzbljzz;
    }

    public void setSfgzbljzz(Integer sfgzbljzz) {
        this.sfgzbljzz = sfgzbljzz;
    }

    public Integer getSfgzdzgx() {
        return sfgzdzgx;
    }

    public void setSfgzdzgx(Integer sfgzdzgx) {
        this.sfgzdzgx = sfgzdzgx;
    }

    public Integer getIsaudit() {
        return isaudit;
    }

    public void setIsaudit(Integer isaudit) {
        this.isaudit = isaudit;
    }

    public String getWzyy() {
        return wzyy;
    }

    public void setWzyy(String wzyy) {
        this.wzyy = wzyy;
    }

    public Integer getIspapers() {
        return ispapers;
    }

    public void setIspapers(Integer ispapers) {
        this.ispapers = ispapers;
    }

    public Integer getJzcslx() {
        return jzcslx;
    }

    public void setJzcslx(Integer jzcslx) {
        this.jzcslx = jzcslx;
    }

    public Integer getLzdq() {
        return lzdq;
    }

    public void setLzdq(Integer lzdq) {
        this.lzdq = lzdq;
    }

    public Integer getZc() {
        return zc;
    }

    public void setZc(Integer zc) {
        this.zc = zc;
    }

    public Integer getCkdt() {
        return ckdt;
    }

    public void setCkdt(Integer ckdt) {
        this.ckdt = ckdt;
    }

    public Integer getIndustry() {
        return industry;
    }

    public void setIndustry(Integer industry) {
        this.industry = industry;
    }

    public String getPhonemodel() {
        return phonemodel;
    }

    public void setPhonemodel(String phonemodel) {
        this.phonemodel = phonemodel;
    }
}
