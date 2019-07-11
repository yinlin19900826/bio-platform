package com.biocome.platform.admin.vo.advert;

import com.biocome.platform.admin.vo.common.CommonVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author hxy
 * @date 2019/5/30 16:44
 */
@ApiModel("批量删除广告")
public class RemoveAdvertList extends CommonVo {
    @ApiModelProperty(value = "广告细节列表")
    private List<RemoveDetail> list;
    @ApiModelProperty(value = "设备编号列表")
    private List<AdvertSnList> listsn;

    public RemoveAdvertList(String token, List<RemoveDetail> list, List<AdvertSnList> listsn) {
        this.setToken(token);
        this.list = list;
        this.listsn = listsn;
    }

    public List<RemoveDetail> getList() {
        return list;
    }

    public void setList(List<RemoveDetail> list) {
        this.list = list;
    }

    public List<AdvertSnList> getListsn() {
        return listsn;
    }

    public void setListsn(List<AdvertSnList> listsn) {
        this.listsn = listsn;
    }
}
