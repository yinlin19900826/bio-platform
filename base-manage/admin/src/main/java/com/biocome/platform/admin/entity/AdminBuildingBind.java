package com.biocome.platform.admin.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 管理员楼栋授权
 * 
 * @author Mr.AG
 * @email 463540703@qq.com
 * @date 2019-05-15 18:33:25
 */
@ApiModel("管理员绑定楼栋信息")
@Table(name = "admin_building_bind")
public class AdminBuildingBind {
	private static final long serialVersionUID = 1L;
	
	    //
	@ApiModelProperty(value = "id")
    @Id
    private Integer id;
	
	    //用户名称
	@ApiModelProperty(value = "用户名称")
    @Column(name = "username")
    private String username;
	
	    //管理员code
	@ApiModelProperty(value = "管理员编码")
    @Column(name = "usercode")
    private String usercode;
	
	    //用户类型
	@ApiModelProperty(value = "管理员类型")
    @Column(name = "user_type")
    private Integer userType;
	
	    //社区编码
	@ApiModelProperty(value = "社区编码")
	@Column(name = "comunity_code")
    private String comunityCode;
	
	    //社区名称
	@ApiModelProperty(value = "社区名称")
    @Column(name = "comunity_name")
    private String comunityName;
	
	    //楼栋code
	@ApiModelProperty(value = "楼栋编码")
	@Column(name = "build_code")
    private String buildCode;
	
	    //楼栋名称
	@ApiModelProperty(value = "楼栋名称")
    @Column(name = "build_name")
    private String buildName;
	
	    //电话号码
	@ApiModelProperty(value = "电话号码")
    @Column(name = "phone")
    private String phone;
	
	    //是否默认电话开门 (0 是 1 否)
	@ApiModelProperty(value = "是否默认电话开门 (0 是 1 否)")
    @Column(name = "default_phone_open")
    private Integer defaultPhoneOpen;
	

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
	 * 设置：用户名称
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * 获取：用户名称
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * 设置：管理员code
	 */
	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}
	/**
	 * 获取：管理员code
	 */
	public String getUsercode() {
		return usercode;
	}
	/**
	 * 设置：用户类型
	 */
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	/**
	 * 获取：用户类型
	 */
	public Integer getUserType() {
		return userType;
	}
	/**
	 * 设置：社区编码
	 */
	public void setComunityCode(String comunityCode) {
		this.comunityCode = comunityCode;
	}
	/**
	 * 获取：社区编码
	 */
	public String getComunityCode() {
		return comunityCode;
	}
	/**
	 * 设置：社区名称
	 */
	public void setComunityName(String comunityName) {
		this.comunityName = comunityName;
	}
	/**
	 * 获取：社区名称
	 */
	public String getComunityName() {
		return comunityName;
	}
	/**
	 * 设置：楼栋code
	 */
	public void setBuildCode(String buildCode) {
		this.buildCode = buildCode;
	}
	/**
	 * 获取：楼栋code
	 */
	public String getBuildCode() {
		return buildCode;
	}
	/**
	 * 设置：楼栋名称
	 */
	public void setBuildName(String buildName) {
		this.buildName = buildName;
	}
	/**
	 * 获取：楼栋名称
	 */
	public String getBuildName() {
		return buildName;
	}
	/**
	 * 设置：电话号码
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * 获取：电话号码
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * 设置：是否默认电话开门 (0 是 1 否)
	 */
	public void setDefaultPhoneOpen(Integer defaultPhoneOpen) {
		this.defaultPhoneOpen = defaultPhoneOpen;
	}
	/**
	 * 获取：是否默认电话开门 (0 是 1 否)
	 */
	public Integer getDefaultPhoneOpen() {
		return defaultPhoneOpen;
	}
}
