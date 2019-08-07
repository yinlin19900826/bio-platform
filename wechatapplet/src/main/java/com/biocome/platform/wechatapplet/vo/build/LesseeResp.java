package com.biocome.platform.wechatapplet.vo.build;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @ClassName: LesseeResp
 * @Author: shenlele
 * @Date: 2019/8/7 17:48
 * @Description:
 */
@ApiModel(value = "租户列表返回类")
public class LesseeResp {

    @ApiModelProperty(value = "负责人列表")
    private List<LesseeVo> principals;
    @ApiModelProperty(value = "租户列表")
    private List<LesseeVo> lessees;

    public LesseeResp(List<LesseeVo> principals, List<LesseeVo> lessees) {
        this.principals = principals;
        this.lessees = lessees;
    }

    public List<LesseeVo> getPrincipals() {
        return principals;
    }

    public void setPrincipals(List<LesseeVo> principals) {
        this.principals = principals;
    }

    public List<LesseeVo> getLessees() {
        return lessees;
    }

    public void setLessees(List<LesseeVo> lessees) {
        this.lessees = lessees;
    }
}
