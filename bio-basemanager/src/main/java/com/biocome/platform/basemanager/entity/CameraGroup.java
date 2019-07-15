package com.biocome.platform.basemanager.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 镜头分组
 * 
 * @author zengqiang
 * @email zengqiang724@163.com
 * @date 2019-06-05 15:13:24
 */
@ApiModel("基础镜头分组")
@Table(name = "base_camera_group")
public class CameraGroup {
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "id")
    @Id
    private Integer id;

	@ApiModelProperty(value = "名称")
    @Column(name = "name")
    private String name;

	@ApiModelProperty(value = "父节点id")
    @Column(name = "parent_id")
    private Integer parentId;

	@ApiModelProperty(value = "节点层级")
    @Column(name = "node_level")
    private Integer nodeLevel;

	@ApiModelProperty(value = "节点类型（1.区划 2.设备）")
    @Column(name = "node_type")
    private Integer nodeType;

	@ApiModelProperty(value = "通道id（当节点为视频设备的时候不为空）")
	@Column(name = "pipeline_id")
	private Integer pipelineId;

	@ApiModelProperty(value = "视频设备id（当节点为视频设备的时候不为空）")
	@Column(name = "camera_id")
	private Integer cameraId;
	

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
	 * 设置：分组名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：分组名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：父id
	 */
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	/**
	 * 获取：父id
	 */
	public Integer getParentId() {
		return parentId;
	}
	/**
	 * 设置：节点层级
	 */
	public void setNodeLevel(Integer nodeLevel) {
		this.nodeLevel = nodeLevel;
	}
	/**
	 * 获取：节点层级
	 */
	public Integer getNodeLevel() {
		return nodeLevel;
	}
	/**
	 * 设置：节点类型
	 */
	public void setNodeType(Integer nodeType) {
		this.nodeType = nodeType;
	}
	/**
	 * 获取：节点类型
	 */
	public Integer getNodeType() {
		return nodeType;
	}

	public Integer getPipelineId() {
		return pipelineId;
	}

	public void setPipelineId(Integer pipelineId) {
		this.pipelineId = pipelineId;
	}

	public Integer getCameraId() {
		return cameraId;
	}

	public void setCameraId(Integer cameraId) {
		this.cameraId = cameraId;
	}
}
