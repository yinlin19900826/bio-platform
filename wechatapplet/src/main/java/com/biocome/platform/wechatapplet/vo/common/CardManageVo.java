package com.biocome.platform.wechatapplet.vo.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author yinlin
 * @date 2019/7/31 20:16
 */
@ApiModel(value = "门禁卡管理信息返回")
public class CardManageVo {
    /**
     * 主键id
     */
    @ApiModelProperty(value="id")
    private Integer id;
    /**
     * 逻辑卡号
     */
    @ApiModelProperty(value="逻辑卡号")
    private String logicCardno;
    /**
     * 物理卡号
     */
    @ApiModelProperty(value="物理卡号")
    private String physicalCardno;

    /**
     * 楼栋名
     */
    @ApiModelProperty(value="楼栋名")
    private String buildName;

    public String getBuildName() {
        return buildName;
    }

    public void setBuildName(String buildName) {
        this.buildName = buildName;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogicCardno() {
        return logicCardno;
    }

    public void setLogicCardno(String logicCardno) {
        this.logicCardno = logicCardno;
    }

    public String getPhysicalCardno() {
        return physicalCardno;
    }

    public void setPhysicalCardno(String physicalCardno) {
        this.physicalCardno = physicalCardno;
    }

}
