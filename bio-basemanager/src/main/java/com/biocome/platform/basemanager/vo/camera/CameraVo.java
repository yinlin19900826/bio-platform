package com.biocome.platform.basemanager.vo.camera;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("视频设备")
public class CameraVo {
    @ApiModelProperty(value = "id")
    private Integer id;
    @ApiModelProperty(value = "机构编码")
    private String institutioncode;
    @ApiModelProperty(value = "省")
    private String province;
    @ApiModelProperty(value = "市")
    private String city;
    @ApiModelProperty(value = "县/区")
    private String county;
    @ApiModelProperty(value = "街道")
    private String street;
    @ApiModelProperty(value = "派出所")
    private String policestatio;
    @ApiModelProperty(value = "传输协议类型")
    private String transfertype;
    @ApiModelProperty(value = "设备型号")
    private String videotype;
    @ApiModelProperty(value = "设备型号名称")
    private String videotypeName;
    @ApiModelProperty(value = "加密类型")
    private String encodetype;
    @ApiModelProperty(value = "接入方式")
    private String jointype;
    @ApiModelProperty(value = "流媒体获取方式")
    private Integer streamtotal;
    @ApiModelProperty(value = "厂家名称")
    private String factory;
    @ApiModelProperty(value = "ip")
    private String lanIp;
    @ApiModelProperty(value = "端口")
    private String lanPort;
    @ApiModelProperty(value = "外网ip")
    private String wanIp;
    @ApiModelProperty(value = "外网端口")
    private String wanPort;
    @ApiModelProperty(value = "云台控制模式")
    private String ctrlModel;
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "域名解析 0 是 1 否")
    private Integer dnsParse;
    @ApiModelProperty(value = "网闸映射 0 是 1 否")
    private Integer gatewayMapping;
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "设备状态")
    private Integer status;
    @ApiModelProperty(value = "通道序号")
    private Integer totalChannels;
    @ApiModelProperty(value = "摄像机编码")
    private String cameracode;
    @ApiModelProperty(value = "父节点编码")
    private String parentcode;
    @ApiModelProperty(value = "监控摄像头名称")
    private String monitorname;

    public String getMonitorname() {
        return monitorname;
    }

    public void setMonitorname(String monitorname) {
        this.monitorname = monitorname;
    }

    public Integer getStreamtotal() {
        return streamtotal;
    }

    public void setStreamtotal(Integer streamtotal) {
        this.streamtotal = streamtotal;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPolicestatio() {
        return policestatio;
    }

    public void setPolicestatio(String policestatio) {
        this.policestatio = policestatio;
    }

    public String getTransfertype() {
        return transfertype;
    }

    public void setTransfertype(String transfertype) {
        this.transfertype = transfertype;
    }

    public String getVideotype() {
        return videotype;
    }

    public void setVideotype(String videotype) {
        this.videotype = videotype;
    }

    public String getVideotypeName() {
        return videotypeName;
    }

    public void setVideotypeName(String videotypeName) {
        this.videotypeName = videotypeName;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public Integer getTotalChannels() {
        return totalChannels;
    }

    public void setTotalChannels(Integer totalChannels) {
        this.totalChannels = totalChannels;
    }

    public String getCameracode() {
        return cameracode;
    }

    public void setCameracode(String cameracode) {
        this.cameracode = cameracode;
    }

    public String getParentcode() {
        return parentcode;
    }

    public void setParentcode(String parentcode) {
        this.parentcode = parentcode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInstitutioncode() {
        return institutioncode;
    }

    public void setInstitutioncode(String institutioncode) {
        this.institutioncode = institutioncode;
    }


    public String getEncodetype() {
        return encodetype;
    }

    public void setEncodetype(String encodetype) {
        this.encodetype = encodetype;
    }

    public String getJointype() {
        return jointype;
    }

    public void setJointype(String jointype) {
        this.jointype = jointype;
    }

    public String getLanIp() {
        return lanIp;
    }

    public void setLanIp(String lanIp) {
        this.lanIp = lanIp;
    }

    public String getLanPort() {
        return lanPort;
    }

    public void setLanPort(String lanPort) {
        this.lanPort = lanPort;
    }

    public String getWanIp() {
        return wanIp;
    }

    public void setWanIp(String wanIp) {
        this.wanIp = wanIp;
    }

    public String getWanPort() {
        return wanPort;
    }

    public void setWanPort(String wanPort) {
        this.wanPort = wanPort;
    }

    public String getCtrlModel() {
        return ctrlModel;
    }

    public void setCtrlModel(String ctrlModel) {
        this.ctrlModel = ctrlModel;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getDnsParse() {
        return dnsParse;
    }

    public void setDnsParse(Integer dnsParse) {
        this.dnsParse = dnsParse;
    }

    public Integer getGatewayMapping() {
        return gatewayMapping;
    }

    public void setGatewayMapping(Integer gatewayMapping) {
        this.gatewayMapping = gatewayMapping;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


}
