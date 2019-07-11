package com.biocome.platform.guard.vo.syncho;

import com.biocome.platform.guard.vo.common.CommonVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: SynchoVo
 * @Author: shenlele
 * @Date: 2019/5/21 18:26
 * @Description:
 */
@ApiModel(value = "同步楼栋，房屋，小区，人员实体类")
public class SynchoVo extends CommonVo {
    @ApiModelProperty(value = "级别")
    private String type;
    @ApiModelProperty(value = "编号")
    private String code;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
