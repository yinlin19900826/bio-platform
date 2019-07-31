package com.biocome.platform.wechatapplet.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 
 * 
 * @author zengqiang
 * @email zengqiang724@163.com
 * @date 2019-07-31 18:22:58
 */
@Table(name = "app_user")
public class AppUser {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //用户名
    @Column(name = "username")
    private String username;
	
	    //密码
    @Column(name = "password")
    private String password;
	
	    //手机
    @Column(name = "phone")
    private String phone;
	
	    //手机是否验证
    @Column(name = "phone_checked")
    private Integer phoneChecked;
	
	    //户籍地址
    @Column(name = "residence_address")
    private String residenceAddress;
	
	    //婚姻状况
    @Column(name = "marriage")
    private Integer marriage;
	
	    //文化程度
    @Column(name = "education")
    private Integer education;
	
	    //职业
    @Column(name = "career")
    private String career;
	
	    //居住处所类型
    @Column(name = "residence_type")
    private Integer residenceType;
	
	    //工作单位
    @Column(name = "work_organization")
    private String workOrganization;
	
	    //来自地区
    @Column(name = "from_place")
    private String fromPlace;
	
	    //来深事由
    @Column(name = "come_reason")
    private String comeReason;
	
	    //与户主关系
    @Column(name = "realtionship")
    private String realtionship;
	
	    //身高
    @Column(name = "height")
    private Double height;
	
	    //政治面貌
    @Column(name = "politic_position")
    private Integer politicPosition;
	
	    //行业
    @Column(name = "profession")
    private Integer profession;
	
	    //居住方式
    @Column(name = "dwell_type")
    private Integer dwellType;
	
	    //职称
    @Column(name = "professional_title")
    private Integer professionalTitle;
	
	    //首次来深日期
    @Column(name = "arrive_date")
    private Date arriveDate;
	
	    //是否告知办理居住证
    @Column(name = "reside_permit_notify")
    private Integer residePermitNotify;
	
	    //是否告知地址改写
    @Column(name = "place_change_notify")
    private Integer placeChangeNotify;
	
	    //生育动态
    @Column(name = "reproduction")
    private Integer reproduction;
	
	    //避孕动态
    @Column(name = "contraception")
    private Integer contraception;
	
	    //持卡动态
    @Column(name = "card_dynamic")
    private Integer cardDynamic;
	
	    //创建时间
    @Column(name = "create_time")
    private Date createTime;
	
	    //创建人
    @Column(name = "create_user")
    private Date createUser;
	
	    //登录时间
    @Column(name = "login_time")
    private Date loginTime;
	
	    //登录ip
    @Column(name = "login_ip")
    private String loginIp;
	

	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：用户名
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * 获取：用户名
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * 设置：密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 获取：密码
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * 设置：手机
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * 获取：手机
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * 设置：手机是否验证
	 */
	public void setPhoneChecked(Integer phoneChecked) {
		this.phoneChecked = phoneChecked;
	}
	/**
	 * 获取：手机是否验证
	 */
	public Integer getPhoneChecked() {
		return phoneChecked;
	}
	/**
	 * 设置：户籍地址
	 */
	public void setResidenceAddress(String residenceAddress) {
		this.residenceAddress = residenceAddress;
	}
	/**
	 * 获取：户籍地址
	 */
	public String getResidenceAddress() {
		return residenceAddress;
	}
	/**
	 * 设置：婚姻状况
	 */
	public void setMarriage(Integer marriage) {
		this.marriage = marriage;
	}
	/**
	 * 获取：婚姻状况
	 */
	public Integer getMarriage() {
		return marriage;
	}
	/**
	 * 设置：文化程度
	 */
	public void setEducation(Integer education) {
		this.education = education;
	}
	/**
	 * 获取：文化程度
	 */
	public Integer getEducation() {
		return education;
	}
	/**
	 * 设置：职业
	 */
	public void setCareer(String career) {
		this.career = career;
	}
	/**
	 * 获取：职业
	 */
	public String getCareer() {
		return career;
	}
	/**
	 * 设置：居住处所类型
	 */
	public void setResidenceType(Integer residenceType) {
		this.residenceType = residenceType;
	}
	/**
	 * 获取：居住处所类型
	 */
	public Integer getResidenceType() {
		return residenceType;
	}
	/**
	 * 设置：工作单位
	 */
	public void setWorkOrganization(String workOrganization) {
		this.workOrganization = workOrganization;
	}
	/**
	 * 获取：工作单位
	 */
	public String getWorkOrganization() {
		return workOrganization;
	}
	/**
	 * 设置：来自地区
	 */
	public void setFromPlace(String fromPlace) {
		this.fromPlace = fromPlace;
	}
	/**
	 * 获取：来自地区
	 */
	public String getFromPlace() {
		return fromPlace;
	}
	/**
	 * 设置：来深事由
	 */
	public void setComeReason(String comeReason) {
		this.comeReason = comeReason;
	}
	/**
	 * 获取：来深事由
	 */
	public String getComeReason() {
		return comeReason;
	}
	/**
	 * 设置：与户主关系
	 */
	public void setRealtionship(String realtionship) {
		this.realtionship = realtionship;
	}
	/**
	 * 获取：与户主关系
	 */
	public String getRealtionship() {
		return realtionship;
	}
	/**
	 * 设置：身高
	 */
	public void setHeight(Double height) {
		this.height = height;
	}
	/**
	 * 获取：身高
	 */
	public Double getHeight() {
		return height;
	}
	/**
	 * 设置：政治面貌
	 */
	public void setPoliticPosition(Integer politicPosition) {
		this.politicPosition = politicPosition;
	}
	/**
	 * 获取：政治面貌
	 */
	public Integer getPoliticPosition() {
		return politicPosition;
	}
	/**
	 * 设置：行业
	 */
	public void setProfession(Integer profession) {
		this.profession = profession;
	}
	/**
	 * 获取：行业
	 */
	public Integer getProfession() {
		return profession;
	}
	/**
	 * 设置：居住方式
	 */
	public void setDwellType(Integer dwellType) {
		this.dwellType = dwellType;
	}
	/**
	 * 获取：居住方式
	 */
	public Integer getDwellType() {
		return dwellType;
	}
	/**
	 * 设置：职称
	 */
	public void setProfessionalTitle(Integer professionalTitle) {
		this.professionalTitle = professionalTitle;
	}
	/**
	 * 获取：职称
	 */
	public Integer getProfessionalTitle() {
		return professionalTitle;
	}
	/**
	 * 设置：首次来深日期
	 */
	public void setArriveDate(Date arriveDate) {
		this.arriveDate = arriveDate;
	}
	/**
	 * 获取：首次来深日期
	 */
	public Date getArriveDate() {
		return arriveDate;
	}
	/**
	 * 设置：是否告知办理居住证
	 */
	public void setResidePermitNotify(Integer residePermitNotify) {
		this.residePermitNotify = residePermitNotify;
	}
	/**
	 * 获取：是否告知办理居住证
	 */
	public Integer getResidePermitNotify() {
		return residePermitNotify;
	}
	/**
	 * 设置：是否告知地址改写
	 */
	public void setPlaceChangeNotify(Integer placeChangeNotify) {
		this.placeChangeNotify = placeChangeNotify;
	}
	/**
	 * 获取：是否告知地址改写
	 */
	public Integer getPlaceChangeNotify() {
		return placeChangeNotify;
	}
	/**
	 * 设置：生育动态
	 */
	public void setReproduction(Integer reproduction) {
		this.reproduction = reproduction;
	}
	/**
	 * 获取：生育动态
	 */
	public Integer getReproduction() {
		return reproduction;
	}
	/**
	 * 设置：避孕动态
	 */
	public void setContraception(Integer contraception) {
		this.contraception = contraception;
	}
	/**
	 * 获取：避孕动态
	 */
	public Integer getContraception() {
		return contraception;
	}
	/**
	 * 设置：持卡动态
	 */
	public void setCardDynamic(Integer cardDynamic) {
		this.cardDynamic = cardDynamic;
	}
	/**
	 * 获取：持卡动态
	 */
	public Integer getCardDynamic() {
		return cardDynamic;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：创建人
	 */
	public void setCreateUser(Date createUser) {
		this.createUser = createUser;
	}
	/**
	 * 获取：创建人
	 */
	public Date getCreateUser() {
		return createUser;
	}
	/**
	 * 设置：登录时间
	 */
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	/**
	 * 获取：登录时间
	 */
	public Date getLoginTime() {
		return loginTime;
	}
	/**
	 * 设置：登录ip
	 */
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	/**
	 * 获取：登录ip
	 */
	public String getLoginIp() {
		return loginIp;
	}
}
