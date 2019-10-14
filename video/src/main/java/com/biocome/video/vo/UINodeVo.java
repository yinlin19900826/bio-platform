package com.biocome.video.vo;

public class UINodeVo {
    private Integer id;
    private String name;
    private int level;
    private String attach;
    private Integer parentId;
    private Integer type;
    private Integer cameraId;
    private Integer pipeline_id;

    public Integer getCameraId() {
        return cameraId;
    }

    public void setCameraId(Integer cameraId) {
        this.cameraId = cameraId;
    }

    public Integer getPipeline_id() {
        return pipeline_id;
    }

    public void setPipeline_id(Integer pipeline_id) {
        this.pipeline_id = pipeline_id;
    }

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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
