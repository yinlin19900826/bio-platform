package com.biocome.platform.admin.vo.camera;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("服务配置")
public class ServiceConfigVo {
    @ApiModelProperty(value = "id")
    private Integer id;
    @ApiModelProperty(value = "名称")
    private String name;
    @ApiModelProperty(value = "类型")
    private Integer type;
    @ApiModelProperty(value = "类型名称")
    private String typeName;
    @ApiModelProperty(value = "子类型")
    private Integer subType;
    @ApiModelProperty(value = "子类型名称")
    private String subTypeName;
    @ApiModelProperty(value = "内网ip")
    private String lanIp;
    @ApiModelProperty(value = "内网端口")
    private String lanPort;
    @ApiModelProperty(value = "外网ip")
    private String wanIp;
    @ApiModelProperty(value = "外网端口")
    private String wanPort;
    @ApiModelProperty(value = "最大连接数")
    private Integer maxConn;
    @ApiModelProperty(value = "最大分发数")
    private Integer maxDispatch;
    @ApiModelProperty(value = "描述")
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getSubType() {
        return subType;
    }

    public void setSubType(Integer subType) {
        this.subType = subType;
    }

    public String getSubTypeName() {
        return subTypeName;
    }

    public void setSubTypeName(String subTypeName) {
        this.subTypeName = subTypeName;
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

    public Integer getMaxConn() {
        return maxConn;
    }

    public void setMaxConn(Integer maxConn) {
        this.maxConn = maxConn;
    }

    public Integer getMaxDispatch() {
        return maxDispatch;
    }

    public void setMaxDispatch(Integer maxDispatch) {
        this.maxDispatch = maxDispatch;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
