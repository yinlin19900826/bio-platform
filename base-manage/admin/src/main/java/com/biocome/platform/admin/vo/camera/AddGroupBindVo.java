package com.biocome.platform.admin.vo.camera;

import io.swagger.annotations.ApiParam;

import java.util.List;

public class AddGroupBindVo {
    @ApiParam(value = "管理员用户名", required = true)
    private String username;
    @ApiParam(value = "分组id列表", required = true)
    private List<Integer> list;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Integer> getList() {
        return list;
    }

    public void setList(List<Integer> list) {
        this.list = list;
    }
}
