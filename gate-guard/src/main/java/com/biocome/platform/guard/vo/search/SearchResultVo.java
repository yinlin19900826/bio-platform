package com.biocome.platform.guard.vo.search;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: SearchResultVo
 * @Author: zengqiang
 * @Date: 2019/8/27
 * @Description:
 */
@ApiModel("检索结果")
public class SearchResultVo {
    @ApiModelProperty(value = "id")
    private int id;
    @ApiModelProperty(value = "code")
    private String code;
    @ApiModelProperty(value = "资源名称")
    private String name;
    @ApiModelProperty(value = "资源名称")
    private String type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
