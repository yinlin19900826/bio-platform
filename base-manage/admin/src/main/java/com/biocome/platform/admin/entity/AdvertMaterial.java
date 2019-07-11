package com.biocome.platform.admin.entity;

import com.biocome.platform.common.entity.Base;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassName: AdvertMaterial
 * @Author: shenlele
 * @Date: 2019/5/30 15:05
 * @Description:
 */
@ApiModel(value = "广告素材实体类")
@Table(name = "base_advert_material")
public class AdvertMaterial extends Base {
    /**
     * 主键编号
     */
    @Id
    private Integer id;

    /**
     * 素材名称
     */
    @ApiModelProperty(value = "素材名称")
    private String materialname;

    /**
     * 素材原名称
     */
    @ApiModelProperty(value = "素材原名称")
    private String originalname;

    /**
     * 素材类型（0：图片，1：视频，2：文字，3：声音）
     */
    @ApiModelProperty(value = "素材类型（0：图片，1：视频，2：文字，3：声音）")
    private String type;

    /**
     * 图片/视频地址
     */
    @ApiModelProperty(value = "图片/视频地址")
    private String filepath;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMaterialname() {
        return materialname;
    }

    public void setMaterialname(String materialname) {
        this.materialname = materialname;
    }

    public String getOriginalname() {
        return originalname;
    }

    public void setOriginalname(String originalname) {
        this.originalname = originalname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }
}
