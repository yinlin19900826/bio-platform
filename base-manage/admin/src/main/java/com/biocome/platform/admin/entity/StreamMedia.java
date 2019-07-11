package com.biocome.platform.admin.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 流媒体服务
 * 
 * @author zengqiang
 * @email zengqiang724@163.com
 * @date 2019-06-03 17:39:34
 */
@Table(name = "base_stream_media")
public class StreamMedia {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //名称
    @Column(name = "name")
    private String name;
	
	    //ip
    @Column(name = "ip")
    private String ip;
	
	    //端口
    @Column(name = "port")
    private String port;
	
	    //优先级
    @Column(name = "priority")
    private Integer priority;

	//关联设备id
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
	 * 设置：名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：ip
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	/**
	 * 获取：ip
	 */
	public String getIp() {
		return ip;
	}
	/**
	 * 设置：端口
	 */
	public void setPort(String port) {
		this.port = port;
	}
	/**
	 * 获取：端口
	 */
	public String getPort() {
		return port;
	}
	/**
	 * 设置：优先级
	 */
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	/**
	 * 获取：优先级
	 */
	public Integer getPriority() {
		return priority;
	}

	public Integer getCameraId() {
		return cameraId;
	}

	public void setCameraId(Integer cameraId) {
		this.cameraId = cameraId;
	}
}
