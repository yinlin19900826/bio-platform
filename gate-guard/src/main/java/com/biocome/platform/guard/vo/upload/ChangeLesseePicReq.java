package com.biocome.platform.guard.vo.upload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author hxy
 * @date 2019/5/29 09:23
 */
@ApiModel(value = "改变证件照")
public class ChangeLesseePicReq {
    @ApiModelProperty(value = "用户编号")
    private String usercode;
    @ApiModelProperty(value = "头像照")
    private String headphoto;
    @ApiModelProperty(value = "人像照")
    private String photo;
    @ApiModelProperty(value = "证件照")
    private String papersphoto;

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getHeadphoto() {
        return headphoto;
    }

    public void setHeadphoto(String headphoto) {
        this.headphoto = headphoto;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPapersphoto() {
        return papersphoto;
    }

    public void setPapersphoto(String papersphoto) {
        this.papersphoto = papersphoto;
    }
}