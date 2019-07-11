package com.biocome.platform.admin.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 
 * 
 * @author zengqiang
 * @email zengqiang724@163.com
 * @date 2019-06-06 09:39:45
 */
@Table(name = "base_dict_detail")
public class DictDetail {
	private static final long serialVersionUID = 1L;
	
	    //主键ID
    @Id
    private Integer id;
	
	    //字典类型码
    @Column(name = "dict_code")
    private String dictCode;
	
	    //字典键
    @Column(name = "dict_key")
    private String dictKey;
	
	    //字典值
    @Column(name = "dict_value")
    private String dictValue;
	
	    //是否可以修改
    @Column(name = "ifvariable")
    private Integer ifvariable;
	
	    //备注
    @Column(name = "comment")
    private String comment;
	
	    //上一级key
    @Column(name = "parent_key")
    private String parentKey;
	

	/**
	 * 设置：主键ID
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：主键ID
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：字典类型码
	 */
	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}
	/**
	 * 获取：字典类型码
	 */
	public String getDictCode() {
		return dictCode;
	}
	/**
	 * 设置：字典键
	 */
	public void setDictKey(String dictKey) {
		this.dictKey = dictKey;
	}
	/**
	 * 获取：字典键
	 */
	public String getDictKey() {
		return dictKey;
	}
	/**
	 * 设置：字典值
	 */
	public void setDictValue(String dictValue) {
		this.dictValue = dictValue;
	}
	/**
	 * 获取：字典值
	 */
	public String getDictValue() {
		return dictValue;
	}
	/**
	 * 设置：是否可以修改
	 */
	public void setIfvariable(Integer ifvariable) {
		this.ifvariable = ifvariable;
	}
	/**
	 * 获取：是否可以修改
	 */
	public Integer getIfvariable() {
		return ifvariable;
	}
	/**
	 * 设置：备注
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
	/**
	 * 获取：备注
	 */
	public String getComment() {
		return comment;
	}
	/**
	 * 设置：上一级key
	 */
	public void setParentKey(String parentKey) {
		this.parentKey = parentKey;
	}
	/**
	 * 获取：上一级key
	 */
	public String getParentKey() {
		return parentKey;
	}
}
