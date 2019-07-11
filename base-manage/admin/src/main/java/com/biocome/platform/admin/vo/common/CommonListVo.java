package com.biocome.platform.admin.vo.common;

import java.util.List;

/**
 * @ClassName: CommonVo
 * @Author: shenlele
 * @Date: 2019/5/14 16:41
 * @Description:
 */
public class CommonListVo<T> {

    private String token;

    private List<T> list;

    public CommonListVo(String token, List<T> list) {
        this.setToken(token);
        this.setList(list);

    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
