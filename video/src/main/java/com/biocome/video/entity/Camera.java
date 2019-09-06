package com.biocome.video.entity;

import io.swagger.annotations.ApiParam;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 视频设备表
 * 
 * @author zengqiang
 * @email zengqiang724@163.com
 * @date 2019-06-06 15:35:11
 */
@Table(name = "base_camera")
public class Camera {
	private static final long serialVersionUID = 1L;
	
	    //
	@ApiParam(name = "id", value = "id, 新增的时候id为空")
    @Id
    private Integer id;
	
	    //机构编码
	@ApiParam(name = "institutioncode", value = "机构编码")
    @Column(name = "institutioncode")
    private String institutioncode;

	    //
	@ApiParam(name = "videotype", value = "视频设备类型")
    @Column(name = "videotype")
    private String videotype;

	    //编码类型
	@ApiParam(name = "encodetype", value = "编码类型")
    @Column(name = "encodetype")
    private String encodetype;
	
	    //接入方式
	@ApiParam(name = "jointype", value = "接入方式")
    @Column(name = "jointype")
    private String jointype;
	
	    //
    @Column(name = "streamtotal")
    private Integer streamtotal;
	
	    //
    @Column(name = "factory")
    private String factory;
	
	    //ip
	@ApiParam(name = "lanIp", value = "内网ip")
    @Column(name = "lan_ip")
    private String lanIp;
	
	    //端口号
	@ApiParam(name = "lanPort", value = "内网端口号")
    @Column(name = "lan_port")
    private String lanPort;
	
	    //外网ip
	@ApiParam(name = "wanIp", value = "外网ip")
    @Column(name = "wan_ip")
    private String wanIp;
	
	    //外网端口
	@ApiParam(name = "wanPort", value = "外网端口")
    @Column(name = "wan_port")
    private String wanPort;
	
	    //云台控制模式
	@ApiParam(name = "ctrlModel", value = "云台控制模式")
    @Column(name = "ctrl_model")
    private String ctrlModel;
	
	    //用户名
	@ApiParam(name = "username", value = "用户名")
    @Column(name = "username")
    private String username;
	
	    //密码
	@ApiParam(name = "password", value = "密码")
    @Column(name = "password")
    private String password;
	
	    //是否启动域名解析（0 是 1 否）
	@ApiParam(name = "dnsParse", value = "是否启动域名解析（0 是 1 否）")
    @Column(name = "dns_parse")
    private Integer dnsParse;
	
	    //网闸映射
	@ApiParam(name = "gatewayMapping", value = "网闸映射")
    @Column(name = "gateway_mapping")
    private Integer gatewayMapping;
	
	    //备注
	@ApiParam(name = "remark", value = "备注")
    @Column(name = "remark")
    private String remark;
	
	    //在线状态
    @Column(name = "status")
    private Integer status;
	
	    //
    @Column(name = "total_channels")
    private Integer totalChannels;

	    //上级代码
    @Column(name = "parentcode")
    private String parentcode;

	    //摄像机代码
    @Column(name = "cameracode")
    private String cameracode;

	    //传输协议类型
    @Column(name = "transfertype")
    private String transfertype;

	    //省
    @Column(name = "province")
    private String province;

	    //市
    @Column(name = "city")
    private String city;

	    //县/区
    @Column(name = "county")
    private String county;

	    //街道
    @Column(name = "street")
    private String street;

	    //派出所
    @Column(name = "policestatio")
    private String policestatio;

	    //监控摄像头名字
    @Column(name = "monitorname")
    private String monitorname;

	    //视频监控编码
    @Column(name = "videocode")
    private String videocode;
	
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
	/**
	 * 设置：
	 */
	public void setVideotype(String videotype) {
		this.videotype = videotype;
	}
	/**
	 * 获取：
	 */
	public String getVideotype() {
		return videotype;
	}
	/**
	 * 设置：编码类型
	 */
	public void setEncodetype(String encodetype) {
		this.encodetype = encodetype;
	}
	/**
	 * 获取：编码类型
	 */
	public String getEncodetype() {
		return encodetype;
	}
	/**
	 * 设置：接入方式
	 */
	public void setJointype(String jointype) {
		this.jointype = jointype;
	}
	/**
	 * 获取：接入方式
	 */
	public String getJointype() {
		return jointype;
	}
	/**
	 * 设置：
	 */
	public void setStreamtotal(Integer streamtotal) {
		this.streamtotal = streamtotal;
	}
	/**
	 * 获取：
	 */
	public Integer getStreamtotal() {
		return streamtotal;
	}
	/**
	 * 设置：
	 */
	public void setFactory(String factory) {
		this.factory = factory;
	}
	/**
	 * 获取：
	 */
	public String getFactory() {
		return factory;
	}
	/**
	 * 设置：ip
	 */
	public void setLanIp(String lanIp) {
		this.lanIp = lanIp;
	}
	/**
	 * 获取：ip
	 */
	public String getLanIp() {
		return lanIp;
	}
	/**
	 * 设置：端口号
	 */
	public void setLanPort(String lanPort) {
		this.lanPort = lanPort;
	}
	/**
	 * 获取：端口号
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
	 * 设置：云台控制模式
	 */
	public void setCtrlModel(String ctrlModel) {
		this.ctrlModel = ctrlModel;
	}
	/**
	 * 获取：云台控制模式
	 */
	public String getCtrlModel() {
		return ctrlModel;
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
	 * 设置：密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 获取：密码
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * 设置：是否启动域名解析（0 是 1 否）
	 */
	public void setDnsParse(Integer dnsParse) {
		this.dnsParse = dnsParse;
	}
	/**
	 * 获取：是否启动域名解析（0 是 1 否）
	 */
	public Integer getDnsParse() {
		return dnsParse;
	}
	/**
	 * 设置：网闸映射
	 */
	public void setGatewayMapping(Integer gatewayMapping) {
		this.gatewayMapping = gatewayMapping;
	}
	/**
	 * 获取：网闸映射
	 */
	public Integer getGatewayMapping() {
		return gatewayMapping;
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
	 * 设置：在线状态
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：在线状态
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：
	 */


	/**
	 * 设置：上级代码
	 */
	public void setParentcode(String parentcode) {
		this.parentcode = parentcode;
	}
	/**
	 * 获取：上级代码
	 */
	public String getParentcode() {
		return parentcode;
	}
	/**
	 * 设置：摄像机代码
	 */
	public void setCameracode(String cameracode) {
		this.cameracode = cameracode;
	}
	/**
	 * 获取：摄像机代码
	 */
	public String getCameracode() {
		return cameracode;
	}
	/**
	 * 设置：传输协议类型
	 */
	public void setTransfertype(String transfertype) {
		this.transfertype = transfertype;
	}
	/**
	 * 获取：传输协议类型
	 */
	public String getTransfertype() {
		return transfertype;
	}
	/**
	 * 设置：省
	 */
	public void setProvince(String province) {
		this.province = province;
	}
	/**
	 * 获取：省
	 */
	public String getProvince() {
		return province;
	}
	/**
	 * 设置：市
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * 获取：市
	 */
	public String getCity() {
		return city;
	}
	/**
	 * 设置：县/区
	 */
	public void setCounty(String county) {
		this.county = county;
	}
	/**
	 * 获取：县/区
	 */
	public String getCounty() {
		return county;
	}
	/**
	 * 设置：街道
	 */
	public void setStreet(String street) {
		this.street = street;
	}
	/**
	 * 获取：街道
	 */
	public String getStreet() {
		return street;
	}
	/**
	 * 设置：派出所
	 */
	public void setPolicestatio(String policestatio) {
		this.policestatio = policestatio;
	}
	/**
	 * 获取：派出所
	 */
	public String getPolicestatio() {
		return policestatio;
	}
	/**
	 * 设置：监控摄像头名字
	 */
	public void setMonitorname(String monitorname) {
		this.monitorname = monitorname;
	}
	/**
	 * 获取：监控摄像头名字
	 */
	public String getMonitorname() {
		return monitorname;
	}
	/**
	 * 设置：视频监控编码
	 */
	public void setVideocode(String videocode) {
		this.videocode = videocode;
	}
	/**
	 * 获取：视频监控编码
	 */
	public String getVideocode() {
		return videocode;
	}

	public Integer getTotalChannels() {
		return totalChannels;
	}

	public void setTotalChannels(Integer totalChannels) {
		this.totalChannels = totalChannels;
	}
}
