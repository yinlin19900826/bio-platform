package com.biocome.platform.basemanager.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 用户与镜头组关联
 * 
 * @author zengqiang
 * @email zengqiang724@163.com
 * @date 2019-06-14 15:45:27
 */
@Table(name = "user_camera_group_bind")
public class UserCameraGroupBind {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //用户名
    @Column(name = "username")
    private String username;
	
	    //分组id
    @Column(name = "group_ids")
    private String groupIds;
	

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
	 * 设置：分组id
	 */
	public void setGroupIds(String groupIds) {
		this.groupIds = groupIds;
	}
	/**
	 * 获取：分组id
	 */
	public String getGroupIds() {
		return groupIds;
	}
}
