package com.biocome.platform.basemanager.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 服务配置表（视频设备管理）
 * 
 * @author zengqiang
 * @email zengqiang724@163.com
 * @date 2019-06-04 11:10:46
 */
@Table(name = "base_service_config")
public class ServiceConfig {
	private static final long serialVersionUID = 1L;
	
	    //
    @Id
    private Integer id;
	
	    //名称
    @Column(name = "name")
    private String name;
	
	    //服务类型
    @Column(name = "type")
    private Integer type;
	
	    //子服务
    @Column(name = "sub_type")
    private Integer subType;
	
	    //内网ip
    @Column(name = "lan_ip")
    private String lanIp;
	
	    //内网端口
    @Column(name = "lan_port")
    private String lanPort;
	
	    //外网ip
    @Column(name = "wan_ip")
    private String wanIp;
	
	    //外网端口
    @Column(name = "wan_port")
    private String wanPort;
	
	    //最大连接数
    @Column(name = "max_conn")
    private Integer maxConn;
	
	    //最大分发路数
    @Column(name = "max_dispatch")
    private Integer maxDispatch;
	
	    //描述
    @Column(name = "description")
    private String description;

    //密码
    @Column(name = "password")
    private String password;

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
	 * 设置：服务类型
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：服务类型
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置：子服务
	 */
	public void setSubType(Integer subType) {
		this.subType = subType;
	}
	/**
	 * 获取：子服务
	 */
	public Integer getSubType() {
		return subType;
	}
	/**
	 * 设置：内网ip
	 */
	public void setLanIp(String lanIp) {
		this.lanIp = lanIp;
	}
	/**
	 * 获取：内网ip
	 */
	public String getLanIp() {
		return lanIp;
	}
	/**
	 * 设置：内网端口
	 */
	public void setLanPort(String lanPort) {
		this.lanPort = lanPort;
	}
	/**
	 * 获取：内网端口
	 */
	public String getLanPort() {
		return lanPort;
	}
	/**
	 * 设置：外网ip
	 */
	public void setWanIp(String wanIp) {
		this.wanIp = wanIp;
	}
	/**
	 * 获取：外网ip
	 */
	public String getWanIp() {
		return wanIp;
	}
	/**
	 * 设置：外网端口
	 */
	public void setWanPort(String wanPort) {
		this.wanPort = wanPort;
	}
	/**
	 * 获取：外网端口
	 */
	public String getWanPort() {
		return wanPort;
	}
	/**
	 * 设置：最大连接数
	 */
	public void setMaxConn(Integer maxConn) {
		this.maxConn = maxConn;
	}
	/**
	 * 获取：最大连接数
	 */
	public Integer getMaxConn() {
		return maxConn;
	}
	/**
	 * 设置：最大分发路数
	 */
	public void setMaxDispatch(Integer maxDispatch) {
		this.maxDispatch = maxDispatch;
	}
	/**
	 * 获取：最大分发路数
	 */
	public Integer getMaxDispatch() {
		return maxDispatch;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
