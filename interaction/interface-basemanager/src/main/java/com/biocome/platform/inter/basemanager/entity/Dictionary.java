package com.biocome.platform.inter.basemanager.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author hxy
 * @date 2019/5/16 14:04
 */
@ApiModel(value = "字典模型")
@Table(name = "base_dict_detail")
public class Dictionary {
    @ApiModelProperty(value = "id", notes = "id")
    @Id
    private String id;
    @ApiModelProperty(value = "字典类型码", notes = "字典类型码")
    @Column(name = "dict_code")
    private String dictCode;
    @ApiModelProperty(value = "字典键", notes = "字典键")
    @Column(name = "dict_key")
    private String dictKey;
    @ApiModelProperty(value = "字典值", notes = "字典值")
    @Column(name = "dict_value")
    private String dictValue;
    @ApiModelProperty(value = "是否可变更", notes = "是否可变更")
    @Column(name = "ifvariable")
    private String ifvariable;
    @ApiModelProperty(value = "备注", notes = "备注")
    @Column(name = "comment")
    private String comment;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDictCode() {
        return dictCode;
    }

    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }

    public String getDictValue() {
        return dictValue;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue;
    }

    public String getIfvariable() {
        return ifvariable;
    }

    public void setIfvariable(String ifvariable) {
        this.ifvariable = ifvariable;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDictKey() {
        return dictKey;
    }

    public void setDictKey(String dictKey) {
        this.dictKey = dictKey;
    }
}
