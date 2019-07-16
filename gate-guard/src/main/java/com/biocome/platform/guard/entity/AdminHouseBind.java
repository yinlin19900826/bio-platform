package com.biocome.platform.guard.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 管理员房屋授权
 * 
 * @author Mr.AG
 * @email 463540703@qq.com
 * @date 2019-05-15 18:33:25
 */
@Table(name = "admin_house_bind")
public class AdminHouseBind {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //管理员编码
    @Column(name = "usercode")
    private String usercode;
	
	    //楼栋编码
    @Column(name = "build_code")
    private String buildCode;
	
	    //楼栋名称
    @Column(name = "build_name")
    private String buildName;
	
	    //房间编码
    @Column(name = "house_code")
    private String houseCode;
	
	    //房间名称
    @Column(name = "house_name")
    private String houseName;
	
	    //社区编码
    @Column(name = "community_code")
    private String communityCode;
	
	    //社区名称
    @Column(name = "community_name")
    private String communityName;
	

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
	 * 设置：管理员编码
	 */
	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}
	/**
	 * 获取：管理员编码
	 */
	public String getUsercode() {
		return usercode;
	}
	/**
	 * 设置：楼栋编码
	 */
	public void setBuildCode(String buildCode) {
		this.buildCode = buildCode;
	}
	/**
	 * 获取：楼栋编码
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
	 * 设置：房间编码
	 */
	public void setHouseCode(String houseCode) {
		this.houseCode = houseCode;
	}
	/**
	 * 获取：房间编码
	 */
	public String getHouseCode() {
		return houseCode;
	}
	/**
	 * 设置：房间名称
	 */
	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}
	/**
	 * 获取：房间名称
	 */
	public String getHouseName() {
		return houseName;
	}
	/**
	 * 设置：社区编码
	 */
	public void setCommunityCode(String communityCode) {
		this.communityCode = communityCode;
	}
	/**
	 * 获取：社区编码
	 */
	public String getCommunityCode() {
		return communityCode;
	}
	/**
	 * 设置：社区名称
	 */
	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}
	/**
	 * 获取：社区名称
	 */
	public String getCommunityName() {
		return communityName;
	}
}
