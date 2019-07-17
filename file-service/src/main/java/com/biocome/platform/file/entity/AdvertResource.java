package com.biocome.platform.file.entity;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

/**
 * @ClassName: AdvertResource
 * @Author: shenlele
 * @Date: 2019/7/11 10:09
 * @Description:
 */
@Table(name = "bio_advert_resource")
public class AdvertResource {

    private Integer id;
    @Column(name = "object_name")
    private String objectName;
    @Column(name = "bucket_name")
    private String bucketName;
    private String url;
    private String type;
    private Date createtime;

    public AdvertResource(String objectName, String bucketName, String url, String type) {
        this.objectName = objectName;
        this.bucketName = bucketName;
        this.url = url;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}
