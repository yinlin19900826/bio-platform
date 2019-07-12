package com.biocome.platform.guard.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;



/**
 * 广告素材计划关联表
 *
 * @author hxy
 * @email 402795620@qq.com
 * @date 2019-05-31 18:07:14
 */
@Table(name = "base_material_plan")
public class MaterialPlan {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键编号
	 */
	@Id
	private Integer id;

	/**
	 * 素材ID
	 */
	@Column(name = "materialid")
	private Integer materialid;

	/**
	 * 广告计划ID
	 */
	@Column(name = "planid")
	private Integer planid;

	/**
	 * 备注
	 */
	@Column(name = "remark")
	private String remark;

	/**
	 * 数据有效(0有效1无效)
	 */
	@Column(name = "status")
	private Integer status;

	/**
	 * 是否删除（0未删除1已删除）
	 */
	@Column(name = "del")
	private Integer del;

	/**
	 * 创建时间
	 */
	@Column(name = "createtime")
	private Date createtime;

	/**
	 * 创建人账号
	 */
	@Column(name = "createcode")
	private String createcode;

	/**
	 * 创建人昵称
	 */
	@Column(name = "createname")
	private String createname;

	/**
	 * 更新时间
	 */
	@Column(name = "updatetime")
	private Date updatetime;

	/**
	 * 更新人账号
	 */
	@Column(name = "updatecode")
	private String updatecode;

	/**
	 * 更新人昵称
	 */
	@Column(name = "updatename")
	private String updatename;

	/**
	 * 设置：主键编号
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：主键编号
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：素材ID
	 */
	public void setMaterialid(Integer materialid) {
		this.materialid = materialid;
	}
	/**
	 * 获取：素材ID
	 */
	public Integer getMaterialid() {
		return materialid;
	}
	/**
	 * 设置：广告计划ID
	 */
	public void setPlanid(Integer planid) {
		this.planid = planid;
	}
	/**
	 * 获取：广告计划ID
	 */
	public Integer getPlanid() {
		return planid;
	}
	/**
	 * 设置：备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：备注
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * 设置：数据有效(0有效1无效)
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：数据有效(0有效1无效)
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：是否删除（0未删除1已删除）
	 */
	public void setDel(Integer del) {
		this.del = del;
	}
	/**
	 * 获取：是否删除（0未删除1已删除）
	 */
	public Integer getDel() {
		return del;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreatetime() {
		return createtime;
	}
	/**
	 * 设置：创建人账号
	 */
	public void setCreatecode(String createcode) {
		this.createcode = createcode;
	}
	/**
	 * 获取：创建人账号
	 */
	public String getCreatecode() {
		return createcode;
	}
	/**
	 * 设置：创建人昵称
	 */
	public void setCreatename(String createname) {
		this.createname = createname;
	}
	/**
	 * 获取：创建人昵称
	 */
	public String getCreatename() {
		return createname;
	}
	/**
	 * 设置：更新时间
	 */
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	/**
	 * 获取：更新时间
	 */
	public Date getUpdatetime() {
		return updatetime;
	}
	/**
	 * 设置：更新人账号
	 */
	public void setUpdatecode(String updatecode) {
		this.updatecode = updatecode;
	}
	/**
	 * 获取：更新人账号
	 */
	public String getUpdatecode() {
		return updatecode;
	}
	/**
	 * 设置：更新人昵称
	 */
	public void setUpdatename(String updatename) {
		this.updatename = updatename;
	}
	/**
	 * 获取：更新人昵称
	 */
	public String getUpdatename() {
		return updatename;
	}
}
