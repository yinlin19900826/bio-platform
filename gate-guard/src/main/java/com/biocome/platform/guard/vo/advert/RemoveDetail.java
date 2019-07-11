package com.biocome.platform.guard.vo.advert;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author hxy
 * @date 2019/5/30 16:47
 */
@ApiModel(value = "移除广告细节")
public class RemoveDetail {
    @ApiModelProperty(value = "广告文件下发ID（同一文件多次下发不一样）")
    private String adno;
    @ApiModelProperty(value = "广告名称")
    private String adname;
    @ApiModelProperty(value = "广告类型（0：图片，1：视频，2：文字，3：声音）")
    private String type;
    @ApiModelProperty(value = "图片/视频地址（http地址）")
    private String filepath;

    public String getAdno() {
        return adno;
    }

    public void setAdno(String adno) {
        this.adno = adno;
    }

    public String getAdname() {
        return adname;
    }

    public void setAdname(String adname) {
        this.adname = adname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }
}
