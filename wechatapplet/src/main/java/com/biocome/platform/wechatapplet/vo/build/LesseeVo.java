package com.biocome.platform.wechatapplet.vo.build;

import com.biocome.platform.common.util.ValidateUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName: LesseeVo
 * @Author: shenlele
 * @Date: 2019/8/7 14:19
 * @Description:
 */
@ApiModel(value = "租户列表类")
public class LesseeVo {

    @ApiModelProperty(value = "用户编码")
    private String code;
    @ApiModelProperty(value = "用户姓名")
    private String name;
    @ApiModelProperty(value = "入住时间")
    private Date time;
    @ApiModelProperty(value = "头像照")
    private String photo;
    @ApiModelProperty(value = "判断字段")
    private Integer flag;
    @ApiModelProperty(value = "是否入住审核（0待审核1通过2未通过）")
    private Integer isAudit;
    @ApiModelProperty(value = "是否完善信息（0是1否）")
    private Integer isComplete;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        if (ValidateUtils.isNotEmpty(time)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
            return sdf.format(time);
        } else {
            return null;
        }
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Integer getIsAudit() {
        return isAudit;
    }

    public void setIsAudit(Integer isAudit) {
        this.isAudit = isAudit;
    }

    public Integer getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(Integer isComplete) {
        this.isComplete = isComplete;
    }

}
