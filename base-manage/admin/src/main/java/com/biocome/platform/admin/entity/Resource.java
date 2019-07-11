package com.biocome.platform.admin.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 资源表
 *
 * @author hxy
 * @email 402795620@qq.com
 * @date 2019-05-21 11:55:54
 */
@ApiModel(value = "资源模型")
@Table(name = "base_resource")
public class Resource {
    private static final long serialVersionUID = 1L;

    public Resource(String resourceName, String url, String resourceType, Date createtime) {
        this.resourceName = resourceName;
        this.url = url;
        this.resourceType = resourceType;
        this.createtime = createtime;
    }

    //主键ID
    @ApiModelProperty(value = "id")
    @Id
    private Integer id;

    //文件名
    @ApiModelProperty(value = "文件名")
    @Column(name = "resource_name")
    private String resourceName;

    //资源URL
    @ApiModelProperty(value = "资源URL")
    @Column(name = "url")
    private String url;

    //资源类型（0：图片 1：视频 2 其他 ）
    @ApiModelProperty(value = "资源类型（0：图片 1：视频 2 其他 )")
    @Column(name = "resource_type")
    private String resourceType;

    //创建时间
    @ApiModelProperty(value = "创建时间")
    @Column(name = "createtime")
    private Date createtime;


    /**
     * 设置：主键ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取：主键ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置：文件名
     */
    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    /**
     * 获取：文件名
     */
    public String getResourceName() {
        return resourceName;
    }

    /**
     * 设置：资源URL
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取：资源URL
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置：资源类型（0：图片 1：视频 2 其他 ）
     */
    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    /**
     * 获取：资源类型（0：图片 1：视频 2 其他 ）
     */
    public String getResourceType() {
        return resourceType;
    }

    /**
     * 设置：创建时间
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * 获取：创建时间
     */
    public Date getCreatetime() {
        return createtime;
    }
}
