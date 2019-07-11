package com.biocome.platform.admin.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 机构用户
 * 
 * @author zengqiang
 * @email zengqiang724@163.com
 * @date 2019-06-05 20:02:19
 */
@Table(name = "base_institution_user")
public class InstitutionUser {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //用户名
    @Column(name = "username")
    private String username;
	
	    //姓名
    @Column(name = "name")
    private String name;
	
	    //机构编码
    @Column(name = "institutioncode")
    private String institutioncode;
	

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
	 * 设置：姓名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：姓名
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：机构编码
	 */
	public void setInstitutioncode(String institutioncode) {
		this.institutioncode = institutioncode;
	}
	/**
	 * 获取：机构编码
	 */
	public String getInstitutioncode() {
		return institutioncode;
	}
}
