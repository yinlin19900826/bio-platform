package com.biocome.platform.admin.vo.tree;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @ClassName: TreeDistrictVO
 * @Author: yinlin
 * @Date: 2019/6/5 10:22
 * @Description:
 */
@ApiModel(value = "视频树实体类")
public class TreeVideoVO<T>{

    private Integer id;

    @ApiModelProperty(value = "行政区划编码")
    private String code;

    @ApiModelProperty(value = "行政区划名称")
    private String name;

    @ApiModelProperty(value = "类型(1:省,2:市,3:县/区,4:乡/街道,5:村/派出所,6:组/小区,7:楼栋)")
    private Integer type;

    @ApiModelProperty(value = "排序号")
    private Integer sortno;

    @ApiModelProperty(value = "行政区划全称")
    private String fullname;

    @ApiModelProperty(value = "上级行政区划编码")
    private String parentcode;

    @ApiModelProperty(value = "摄像机在线总数")
    private Integer countlivevideo;

    @ApiModelProperty(value = "摄像机总数")
    private Integer countcamera;

    private List<T> children;

    public Integer getCountlivevideo() {
        return countlivevideo;
    }

    public void setCountlivevideo(Integer countlivevideo) {
        this.countlivevideo = countlivevideo;
    }

    public Integer getCountcamera() {
        return countcamera;
    }

    public void setCountcamera(Integer countcamera) {
        this.countcamera = countcamera;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSortno() {
        return sortno;
    }

    public void setSortno(Integer sortno) {
        this.sortno = sortno;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getParentcode() {
        return parentcode;
    }

    public void setParentcode(String parentcode) {
        this.parentcode = parentcode;
    }


    public List<T> getChildren() {
        return children;
    }

    public void setChildren(List<T> children) {
        this.children = children;
    }
}
