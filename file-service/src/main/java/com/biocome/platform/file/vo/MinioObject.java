package com.biocome.platform.file.vo;

import io.minio.ObjectStat;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: MinioObject
 * @Author: shenlele
 * @Date: 2019/7/9 10:36
 * @Description:
 */
public class MinioObject {

    private String bucketName;
    private String name;
    private Date createdTime;
    private long length;
    private String etag;
    private String contentType;
    private Map<String,List<String>> httpHeaders;

    public MinioObject(String bucketName, String name, Date createdTime, long length, String etag, String contentType, Map<String,List<String>> httpHeaders) {
        this.bucketName = bucketName;
        this.name = name;
        this.createdTime = createdTime;
        this.length = length;
        this.etag = etag;
        this.contentType = contentType;
        this.httpHeaders = httpHeaders;
    }

    public MinioObject(ObjectStat os) {
        this.bucketName = os.bucketName();
        this.name = os.name();
        this.createdTime = os.createdTime();
        this.length = os.length();
        this.etag = os.etag();
        this.contentType = os.contentType();
        this.httpHeaders = os.httpHeaders();
    }

    public String getBucketName() {
        return bucketName;
    }

    public String getName() {
        return name;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public long getLength() {
        return length;
    }

    public String getEtag() {
        return etag;
    }

    public String getContentType() {
        return contentType;
    }

    public Map<String,List<String>> getHttpHeaders() {
        return httpHeaders;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setHttpHeaders(Map<String,List<String>> httpHeaders) {
        this.httpHeaders = httpHeaders;
    }
}
