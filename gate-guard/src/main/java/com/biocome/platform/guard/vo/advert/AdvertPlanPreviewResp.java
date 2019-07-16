package com.biocome.platform.guard.vo.advert;

import com.biocome.platform.inter.basemanager.entity.Build;
import com.biocome.platform.inter.basemanager.entity.Estate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author hxy
 * @date 2019/6/3 15:59
 */
@ApiModel("预览广告计划返回值")
public class AdvertPlanPreviewResp {
    @ApiModelProperty(value = "广告素材列表")
    private List<AdvertPreviewResp> advertPreviewRespList;
    @ApiModelProperty(value = "下发社区列表")
    private List<Estate> estateList;
    @ApiModelProperty(value = "下发楼栋列表")
    private List<Build> buildList;

    public List<AdvertPreviewResp> getAdvertPreviewRespList() {
        return advertPreviewRespList;
    }

    public void setAdvertPreviewRespList(List<AdvertPreviewResp> advertPreviewRespList) {
        this.advertPreviewRespList = advertPreviewRespList;
    }

    public List<Estate> getEstateList() {
        return estateList;
    }

    public void setEstateList(List<Estate> estateList) {
        this.estateList = estateList;
    }

    public List<Build> getBuildList() {
        return buildList;
    }

    public void setBuildList(List<Build> buildList) {
        this.buildList = buildList;
    }
}
