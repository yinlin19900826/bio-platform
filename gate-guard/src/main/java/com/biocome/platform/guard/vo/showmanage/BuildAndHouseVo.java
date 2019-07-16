package com.biocome.platform.guard.vo.showmanage;

import com.biocome.platform.inter.basemanager.entity.Build;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @ClassName: BuildAndHouseVo
 * @Author: shenlele
 * @Date: 2019/5/20 15:09
 * @Description:
 */
@ApiModel(value = "门禁管理展示楼栋房屋信息")
public class BuildAndHouseVo {
    /**
     * 楼栋信息
     */
    @ApiModelProperty(value = "楼栋信息")
    private Build build;
    /**
     * 房屋信息（多个）
     */
    @ApiModelProperty(value = "房屋信息（多个）")
    private List<HouseVo> houses;

    public Build getBuild() {
        return build;
    }

    public void setBuild(Build build) {
        this.build = build;
    }

    public List<HouseVo> getHouses() {
        return houses;
    }

    public void setHouses(List<HouseVo> houses) {
        this.houses = houses;
    }
}
