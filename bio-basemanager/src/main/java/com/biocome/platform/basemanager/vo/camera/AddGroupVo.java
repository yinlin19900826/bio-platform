package com.biocome.platform.basemanager.vo.camera;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("添加到基础镜头组")
public class AddGroupVo {
    @ApiModelProperty(value = "视频设备id")
    private String fromParentId;
    @ApiModelProperty(value = "镜头列表")
    private String fromId;
    @ApiModelProperty(value = "要添加到的树的层数")
    private Integer toLevel;
    @ApiModelProperty(value = "名称")
    private String fromName;
    @ApiModelProperty(value = "类型")
    private Integer fromType;
    @ApiModelProperty(value = "要添加到的分组id")
    private String toParentId;
    @ApiModelProperty(value = "该参数不用传递")
    private Integer parentId;
    @ApiModelProperty(value = "该参数不用传递")
    private Integer id;
    @ApiModelProperty(value = "该参数不用传递")
    private Integer cameraId;
    @ApiModelProperty(value = "该参数不用传递")
    private Integer pipelineId;

    public String getFromParentId() {
        return fromParentId;
    }

    public void setFromParentId(String fromParentId) {
        this.fromParentId = fromParentId;
    }

    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public Integer getToLevel() {
        return toLevel;
    }

    public void setToLevel(Integer toLevel) {
        this.toLevel = toLevel;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public Integer getFromType() {
        return fromType;
    }

    public void setFromType(Integer fromType) {
        this.fromType = fromType;
    }

    public String getToParentId() {
        return toParentId;
    }

    public void setToParentId(String toParentId) {
        this.toParentId = toParentId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCameraId() {
        return cameraId;
    }

    public void setCameraId(Integer cameraId) {
        this.cameraId = cameraId;
    }

    public Integer getPipelineId() {
        return pipelineId;
    }

    public void setPipelineId(Integer pipelineId) {
        this.pipelineId = pipelineId;
    }
}
