package com.biocome.platform.guard.vo.advert;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author hxy
 * @date 2019/6/6 16:24
 */
@ApiModel(value = "广告预览请求参数")
public class AddvertPreviewReq {
    @ApiModelProperty(value = "主键ID")
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
