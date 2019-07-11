package com.biocome.platform.admin.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 
 * 
 * @author Mr.AG
 * @email 463540703@qq.com
 * @date 2019-05-09 20:46:07
 */
@Table(name = "base_user")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //
    @Column(name = "username")
    private String username;
	
	    //
    @Column(name = "password")
    private String password;
	
	    //
    @Column(name = "name")
    private String name;
	
	    //
    @Column(name = "birthday")
    private String birthday;
	
	    //
    @Column(name = "address")
    private String address;
	
	    //
    @Column(name = "mobile_phone")
    private String mobilePhone;
	
	    //
    @Column(name = "tel_phone")
    private String telPhone;
	
	    //
    @Column(name = "email")
    private String email;
	
	    //
    @Column(name = "sex")
    private String sex;
	
	    //
    @Column(name = "type")
    private String type;
	
	    //
    @Column(name = "status")
    private String status;
	
	    //
    @Column(name = "description")
    private String description;
	
	    //
    @Column(name = "crt_time")
    private Date crtTime;
	
	    //
    @Column(name = "crt_user")
    private String crtUser;
	
	    //
    @Column(name = "crt_name")
    private String crtName;
	
	    //
    @Column(name = "crt_host")
    private String crtHost;
	
	    //
    @Column(name = "upd_time")
    private Date updTime;
	
	    //
    @Column(name = "upd_user")
    private String updUser;
	
	    //
    @Column(name = "upd_name")
    private String updName;
	
	    //
    @Column(name = "upd_host")
    private String updHost;
	
	    //
    @Column(name = "crt_date")
	@DateTimeFormat(pattern="yyyy-MM-dd")//页面写入数据库时格式化
	@JSONField(format="yyyy-MM-dd")//数据库导出页面时json格式化
    private Date crtDate;
	
	    //
    @Column(name = "expire_date")
	@DateTimeFormat(pattern="yyyy-MM-dd")//页面写入数据库时格式化
	@JSONField(format="yyyy-MM-dd")//数据库导出页面时json格式化
    private Date expireDate;
	
	    //
    @Column(name = "remark")
    private String remark;
	
	    //身份证号
    @Column(name = "cert_id")
    private String certId;
	
	    //用户级别
    @Column(name = "level")
    private Integer level;
	
	    //优先级(1-10）
    @Column(name = "priority")
    private Integer priority;
	
	    //
    @Column(name = "street")
    private String street;
	
	    //街道id
    @Column(name = "street_id")
    private Integer streetId;
	
	    //派出所
    @Column(name = "station")
    private String station;
	
	    //派出所id
    @Column(name = "station_id")
    private Integer stationId;
	
	    //社区
    @Column(name = "community")
    private String community;
	
	    //社区id
    @Column(name = "community_id")
    private Integer communityId;
	
	    //村
    @Column(name = "village")
    private String village;
	
	    //村id
    @Column(name = "village_id")
    private Integer villageId;

	//角色id
	@Column(name = "role_id")
	private Integer roleId;

	//角色名称
	@Column(name = "role_name")
	private String roleName;

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
	 * 设置：
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * 获取：
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * 设置：
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 获取：
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * 设置：
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	/**
	 * 获取：
	 */
	public String getBirthday() {
		return birthday;
	}
	/**
	 * 设置：
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取：
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * 设置：
	 */
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	/**
	 * 获取：
	 */
	public String getMobilePhone() {
		return mobilePhone;
	}
	/**
	 * 设置：
	 */
	public void setTelPhone(String telPhone) {
		this.telPhone = telPhone;
	}
	/**
	 * 获取：
	 */
	public String getTelPhone() {
		return telPhone;
	}
	/**
	 * 设置：
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * 获取：
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * 设置：
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	/**
	 * 获取：
	 */
	public String getSex() {
		return sex;
	}
	/**
	 * 设置：
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置：
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * 设置：
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * 获取：
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * 设置：
	 */
	public void setCrtTime(Date crtTime) {
		this.crtTime = crtTime;
	}
	/**
	 * 获取：
	 */
	public Date getCrtTime() {
		return crtTime;
	}
	/**
	 * 设置：
	 */
	public void setCrtUser(String crtUser) {
		this.crtUser = crtUser;
	}
	/**
	 * 获取：
	 */
	public String getCrtUser() {
		return crtUser;
	}
	/**
	 * 设置：
	 */
	public void setCrtName(String crtName) {
		this.crtName = crtName;
	}
	/**
	 * 获取：
	 */
	public String getCrtName() {
		return crtName;
	}
	/**
	 * 设置：
	 */
	public void setCrtHost(String crtHost) {
		this.crtHost = crtHost;
	}
	/**
	 * 获取：
	 */
	public String getCrtHost() {
		return crtHost;
	}
	/**
	 * 设置：
	 */
	public void setUpdTime(Date updTime) {
		this.updTime = updTime;
	}
	/**
	 * 获取：
	 */
	public Date getUpdTime() {
		return updTime;
	}
	/**
	 * 设置：
	 */
	public void setUpdUser(String updUser) {
		this.updUser = updUser;
	}
	/**
	 * 获取：
	 */
	public String getUpdUser() {
		return updUser;
	}
	/**
	 * 设置：
	 */
	public void setUpdName(String updName) {
		this.updName = updName;
	}
	/**
	 * 获取：
	 */
	public String getUpdName() {
		return updName;
	}
	/**
	 * 设置：
	 */
	public void setUpdHost(String updHost) {
		this.updHost = updHost;
	}
	/**
	 * 获取：
	 */
	public String getUpdHost() {
		return updHost;
	}
	/**
	 * 设置：
	 */
	public void setCrtDate(Date crtDate) {
		this.crtDate = crtDate;
	}
	/**
	 * 获取：
	 */
	public Date getCrtDate() {
		return crtDate;
	}
	/**
	 * 设置：
	 */
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	/**
	 * 获取：
	 */
	public Date getExpireDate() {
		return expireDate;
	}
	/**
	 * 设置：
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * 设置：身份证号
	 */
	public void setCertId(String certId) {
		this.certId = certId;
	}
	/**
	 * 获取：身份证号
	 */
	public String getCertId() {
		return certId;
	}
	/**
	 * 设置：用户级别
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}
	/**
	 * 获取：用户级别
	 */
	public Integer getLevel() {
		return level;
	}
	/**
	 * 设置：优先级(1-10）
	 */
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	/**
	 * 获取：优先级(1-10）
	 */
	public Integer getPriority() {
		return priority;
	}
	/**
	 * 设置：
	 */
	public void setStreet(String street) {
		this.street = street;
	}
	/**
	 * 获取：
	 */
	public String getStreet() {
		return street;
	}
	/**
	 * 设置：街道id
	 */
	public void setStreetId(Integer streetId) {
		this.streetId = streetId;
	}
	/**
	 * 获取：街道id
	 */
	public Integer getStreetId() {
		return streetId;
	}
	/**
	 * 设置：派出所
	 */
	public void setStation(String station) {
		this.station = station;
	}
	/**
	 * 获取：派出所
	 */
	public String getStation() {
		return station;
	}
	/**
	 * 设置：派出所id
	 */
	public void setStationId(Integer stationId) {
		this.stationId = stationId;
	}
	/**
	 * 获取：派出所id
	 */
	public Integer getStationId() {
		return stationId;
	}
	/**
	 * 设置：社区
	 */
	public void setCommunity(String community) {
		this.community = community;
	}
	/**
	 * 获取：社区
	 */
	public String getCommunity() {
		return community;
	}
	/**
	 * 设置：社区id
	 */
	public void setCommunityId(Integer communityId) {
		this.communityId = communityId;
	}
	/**
	 * 获取：社区id
	 */
	public Integer getCommunityId() {
		return communityId;
	}
	/**
	 * 设置：村
	 */
	public void setVillage(String village) {
		this.village = village;
	}
	/**
	 * 获取：村
	 */
	public String getVillage() {
		return village;
	}
	/**
	 * 设置：村id
	 */
	public void setVillageId(Integer villageId) {
		this.villageId = villageId;
	}
	/**
	 * 获取：村id
	 */
	public Integer getVillageId() {
		return villageId;
	}
	/**
	 * 设置：角色id
	 */
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	/**
	 * 获取：角色id
	 */
	public Integer getRoleId() {
		return roleId;
	}
	/**
	 * 设置：角色名称
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	/**
	 * 获取：角色名称
	 */
	public String getRoleName() {
		return roleName;
	}
}
