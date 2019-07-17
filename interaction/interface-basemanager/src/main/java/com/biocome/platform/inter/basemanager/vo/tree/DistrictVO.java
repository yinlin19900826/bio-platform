package com.biocome.platform.inter.basemanager.vo.tree;

import com.biocome.platform.common.util.ValidateUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: DistrictVO
 * @Author: shenlele
 * @Date: 2019/5/17 10:18
 * @Description:
 */
@ApiModel(value = "行政区划树实体类")
public class DistrictVO {

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

    @ApiModelProperty(value = "楼栋总数")
    private String countbuild;

    @ApiModelProperty(value = "门禁机总数")
    private String countdevice;

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
        if (ValidateUtils.isEmpty(type)) {
            type = 7;
        }
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

    public String getCountbuild() {
        return countbuild;
    }

    public void setCountbuild(String countbuild) {
        this.countbuild = countbuild;
    }

    public String getCountdevice() {
        return countdevice;
    }

    public void setCountdevice(String countdevice) {
        this.countdevice = countdevice;
    }
}
