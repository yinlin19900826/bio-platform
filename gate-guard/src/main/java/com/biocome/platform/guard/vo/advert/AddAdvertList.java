package com.biocome.platform.guard.vo.advert;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author hxy
 * @date 2019/5/30 16:17
 */
@ApiModel(value = "广告远程批量添加")
public class AddAdvertList {
    @ApiModelProperty(value = "请求token")
    private String token;
    @ApiModelProperty(value = "广告细节列表")
    private List<AdvertDetail> list;
    @ApiModelProperty(value = "广告细节列表")
    private List<AdvertSnList> listsn;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<AdvertDetail> getList() {
        return list;
    }

    public void setList(List<AdvertDetail> list) {
        this.list = list;
    }

    public List<AdvertSnList> getListsn() {
        return listsn;
    }

    public void setListsn(List<AdvertSnList> listsn) {
        this.listsn = listsn;
    }
}
