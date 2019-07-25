package com.biocome.platform.file.entity;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

/**
 * @ClassName: UserImages
 * @Author: shenlele
 * @Date: 2019/7/11 10:09
 * @Description:
 */
@Table(name = "bio_user_images")
public class UserImages {

    private Integer id;
    @Column(name = "object_name")
    private String objectName;
    @Column(name = "bucket_name")
    private String bucketName;
    private String url;
    private Date createtime;

    public  UserImages(){}

    public UserImages(String objectName, String bucketName, String url) {
        this.objectName = objectName;
        this.bucketName = bucketName;
        this.url = url;
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

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}
