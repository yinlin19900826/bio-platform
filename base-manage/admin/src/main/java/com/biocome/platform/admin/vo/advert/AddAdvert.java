package com.biocome.platform.admin.vo.advert;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author hxy
 * @date 2019/5/30 15:37
 */
@ApiModel(value = "广告远程添加")
public class AddAdvert {
    @ApiModelProperty(value = "请求token")
    private String token;
    @ApiModelProperty(value = "广告细节列表")
    private List<AdvertDetail> list;
    @ApiModelProperty(value = "设备编号")
    private String sn;

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

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }
}
