package com.biocome.platform.wechatapplet.vo.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author yinlin
 * @date 2019/8/2 18:16
 */
@ApiModel(value = "审核返回")
public class AuditOperateVo {
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

}
