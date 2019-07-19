package com.biocome.platform.inter.basemanager.vo.upload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author hxy
 * @date 2019/7/19 09:06
 */
@ApiModel(value = "文件传递")
public class FileVo {
    @ApiModelProperty(value = "文件类型 0:广告或升级文件，1:用户图片，2:开门图片")
    private String type;
    @ApiModelProperty(value = "主题名")
    private String topic;
    @ApiModelProperty(value = "文件名")
    private String filename;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
