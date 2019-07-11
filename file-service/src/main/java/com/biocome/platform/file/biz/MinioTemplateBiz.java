package com.biocome.platform.file.biz;

import com.biocome.platform.file.entity.MinIoResource;
import com.biocome.platform.file.mapper.MinioTemplateMapper;
import com.biocome.platform.file.vo.MinioItem;
import io.minio.MinioClient;
import io.minio.ObjectStat;
import io.minio.Result;
import io.minio.messages.Bucket;
import io.minio.messages.Item;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @ClassName: MinioTemplateBiz
 * @Author: shenlele
 * @Date: 2019/7/9 10:36
 * @Description:
 */
public class MinioTemplateBiz {

    private static final String POLICY_PRE = "{\"Version\":\"2012-10-17\",\"Statement\":[{\"Action\":[\"s3:GetBucketLocation\",\"s3:ListBucket\",\"s3:ListBucketMultipartUploads\"],\"Effect\":\"Allow\",\"Principal\":{\"AWS\":[\"*\"]},\"Resource\":[\"arn:aws:s3:::";
    private static final String POLICY_CEN = "\"],\"Sid\":\"\"},{\"Action\":[\"s3:AbortMultipartUpload\",\"s3:DeleteObject\",\"s3:GetObject\",\"s3:ListMultipartUploadParts\",\"s3:PutObject\"],\"Effect\":\"Allow\",\"Principal\":{\"AWS\":[\"*\"]},\"Resource\":[\"arn:aws:s3:::";
    private static final String POLICY_SUF = "/*\"],\"Sid\":\"\"}]}";

    private String endpoint, accessKey, secretKey;

    @Autowired
    private MinioTemplateMapper mapper;

    /**
     * 构造方法
     *
     * @param endpoint  endPoint是URL，域名，IPv4地址或IPv6地址
     * @param accessKey accessKey就像用户ID一样，可以唯一标识的帐户
     * @param secretKey secretKey是帐户的密码
     * @Author shenlele
     * @Date 2019/7/9 14:30
     */
    public MinioTemplateBiz(String endpoint, String accessKey, String secretKey) {
        this.endpoint = endpoint;
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    /**
     * 给定桶名称创建桶对象
     *
     * @param bucketName 桶名称
     * @return void
     * @throws Exception 异常信息
     * @Author shenlele
     * @Date 2019/7/9 14:31
     */
    public void createBucket(String bucketName) throws Exception {
        MinioClient client = getMinioClient();
        if (!client.bucketExists(bucketName)) {
            client.makeBucket(bucketName);
            //设置桶策略
            client.setBucketPolicy(bucketName, POLICY_PRE + bucketName + POLICY_CEN + bucketName + POLICY_SUF);
        }
    }

    /**
     * 获取所有桶对象信息
     *
     * @return java.util.List<io.minio.messages.Bucket>
     * @throws Exception 异常信息
     * @Author shenlele
     * @Date 2019/7/9 14:32
     */
    public List<Bucket> getAllBuckets() throws Exception {
        return getMinioClient().listBuckets();
    }

    /**
     * 根据给定桶名称，获取桶信息
     *
     * @param bucketName 桶名称
     * @return java.util.Optional<io.minio.messages.Bucket>
     * @throws Exception 异常信息
     * @Author shenlele
     * @Date 2019/7/9 14:32
     */
    public Optional<Bucket> getBucket(String bucketName) throws Exception {
        return getMinioClient().listBuckets().stream().filter(b -> b.name().equals(bucketName)).findFirst();
    }

    /**
     * 根据桶名称删除桶对象
     *
     * @param bucketName 桶名称
     * @return void
     * @throws Exception 异常信息
     * @Author shenlele
     * @Date 2019/7/9 14:33
     */
    public void removeBucket(String bucketName) throws Exception {
        getMinioClient().removeBucket(bucketName);
    }

    /**
     * 获取给定存储桶中的对象信息
     *
     * @param bucketName 桶的名称
     * @param prefix     前缀字符串
     * @param recursive  如果为false，则模拟一个目录结构，其中返回的每个列表都是完整对象或对象键的一部分，直到第一个'/'。具有相同前缀直到第一个'/'的所有对象将合并为一个条目
     * @return java.util.List<com.github.wxiaoqi.security.admin.vo.minio.MinioItem>
     * @throws Exception 异常信息
     * @Author shenlele
     * @Date 2019/7/9 14:33
     */
    public List<MinioItem> getAllObjectsByPrefix(String bucketName, String prefix, boolean recursive) throws Exception {
        List<MinioItem> objectList = new ArrayList<>();
        Iterable<Result<Item>> objectsIterator = getMinioClient().listObjects(bucketName, prefix, recursive);
        objectsIterator.forEach(i -> {
            try {
                objectList.add(new MinioItem(i.get()));
            } catch (Exception e) {
                new Exception(e);
            }
        });
        return objectList;
    }

    /**
     * 为文件生成预签名URL。即使存储桶是私有的，浏览器/移动客户端也可以指向此URL以直接下载对象。此预签名URL可以具有相关的到期时间（以秒为单位），之后它将不再运行。默认到期时间设置为7天
     *
     * @param bucketName 桶名称
     * @param objectName 文件名称
     * @param expires    有效时间（秒）
     * @return java.lang.String
     * @throws Exception 异常信息
     * @Author shenlele
     * @Date 2019/7/9 14:36
     */
    public String getObjectURL(String bucketName, String objectName, Integer expires) throws Exception {
        return getMinioClient().presignedGetObject(bucketName, objectName, expires);
    }

    /**
     * 上传文件，返回路径
     *
     * @param bucketName  桶名称
     * @param objectName  文件名称
     * @param stream      文件流
     * @param size        文件大小
     * @param contentType 文件类型
     * @return java.lang.String
     * @throws Exception 异常信息
     * @Author shenlele
     * @Date 2019/7/9 15:19
     */
    public void saveObject(String bucketName, String objectName, InputStream stream, long size, String contentType, String type) throws Exception {
//        不过时的方法所需密匙生成
//        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
//        keyGen.init(256);
//        ServerSideEncryption sse = ServerSideEncryption.withCustomerKey(keyGen.generateKey());
//        getMinioClient().client.putObject(bucketName, objectName, stream, size, null, sse contentType);
        MinioClient client = getMinioClient();
        client.putObject(bucketName, objectName, stream, size, contentType);
        //返回路径
        String path = client.getObjectUrl(bucketName, objectName);
        MinIoResource minIo = new MinIoResource(objectName, bucketName, path, type);
        mapper.insertSelective(minIo);

    }

    /**
     * 获取对象的元数据
     *
     * @param bucketName 桶名称
     * @param objectName 文件名称
     * @return io.minio.ObjectStat
     * @throws Exception 异常信息
     * @Author shenlele
     * @Date 2019/7/9 15:20
     */
    public ObjectStat getObjectInfo(String bucketName, String objectName) throws Exception {
        return getMinioClient().statObject(bucketName, objectName);
    }

    /**
     * 删除指定文件
     *
     * @param bucketName 桶名称
     * @param objectName 文件名称
     * @return void
     * @throws Exception 异常信息
     * @Author shenlele
     * @Date 2019/7/9 15:20
     */
    public void removeObject(String bucketName, String objectName) throws Exception {
        getMinioClient().removeObject(bucketName, objectName);
    }

    /**
     * 删除指定桶内全部文件
     *
     * @param bucketName 桶名称
     * @return void
     * @throws Exception 异常信息
     * @Author shenlele
     * @Date 2019/7/9 15:20
     */
    public void removeObjectAll(String bucketName) throws Exception {
        MinioClient client = getMinioClient();
        List<String> list = new ArrayList<>();
        Iterable<Result<Item>> iterable = client.listObjects(bucketName, null, true);
        iterable.forEach(itemResult -> {
            try {
                list.add(itemResult.get().objectName());
            } catch (Exception e) {
                new Exception(e);
            }
        });
        client.removeObjects(bucketName, list);
    }

    /**
     * 将对象下载并保存为本地文件系统中的文件
     *
     * @param bucketName 桶名称
     * @param objectName 存储桶中的对象名称
     * @param fileName   文件名
     * @return void
     * @throws Exception 异常信息
     * @Author shenlele
     * @Date 2019/7/9 15:22
     */
    public void getObjectToSave(String bucketName, String objectName, String fileName) throws Exception {
        getMinioClient().getObject(bucketName, objectName, fileName);
    }

    /**
     * 初始化MinioClient对象
     *
     * @return io.minio.MinioClient
     * @throws Exception 异常信息
     * @Author shenlele
     * @Date 2019/7/9 15:26
     */
    private MinioClient getMinioClient() throws Exception {
        return new MinioClient(endpoint, accessKey, secretKey);
    }
}