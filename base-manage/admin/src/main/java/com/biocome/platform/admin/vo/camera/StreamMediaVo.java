package com.biocome.platform.admin.vo.camera;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("流媒体服务")
public class StreamMediaVo {
    @ApiModelProperty("ID")
    private Integer id;
    @ApiModelProperty("名称")
    private String name;
    @ApiModelProperty("IP")
    private String ip;
    @ApiModelProperty("端口")
    private String port;
    @ApiModelProperty("优先级")
    private Integer priority;
    @ApiModelProperty("视频设备id")
    private Integer cameraId;

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

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getCameraId() {
        return cameraId;
    }

    public void setCameraId(Integer cameraId) {
        this.cameraId = cameraId;
    }
}
