package com.biocome.platform.file.biz;

import com.biocome.platform.common.util.JsonUtils;
import com.biocome.platform.file.constant.CommonConstant;
import com.biocome.platform.file.entity.AdvertResource;
import com.biocome.platform.file.entity.OpenDoorImages;
import com.biocome.platform.file.entity.UserImages;
import com.biocome.platform.file.mapper.AdvertResourceMapper;
import com.biocome.platform.file.mapper.OpenDoorImagesMapper;
import com.biocome.platform.file.mapper.UserImagesMapper;
import com.biocome.platform.file.vo.FileVo;
import com.biocome.platform.file.vo.MinioItem;
import io.minio.MinioClient;
import io.minio.ObjectStat;
import io.minio.Result;
import io.minio.messages.Bucket;
import io.minio.messages.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisCluster;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName: MinioTemplateBiz
 * @Author: shenlele
 * @Date: 2019/7/9 10:36
 * @Description:
 */
@Slf4j
public class MinioTemplateBiz {

    private static final String POLICY_PRE = "{\"Version\":\"2012-10-17\",\"Statement\":[{\"Action\":[\"s3:GetBucketLocation\",\"s3:ListBucket\",\"s3:ListBucketMultipartUploads\"],\"Effect\":\"Allow\",\"Principal\":{\"AWS\":[\"*\"]},\"Resource\":[\"arn:aws:s3:::";
    private static final String POLICY_CEN = "\"],\"Sid\":\"\"},{\"Action\":[\"s3:AbortMultipartUpload\",\"s3:DeleteObject\",\"s3:GetObject\",\"s3:ListMultipartUploadParts\",\"s3:PutObject\"],\"Effect\":\"Allow\",\"Principal\":{\"AWS\":[\"*\"]},\"Resource\":[\"arn:aws:s3:::";
    private static final String POLICY_SUF = "/*\"],\"Sid\":\"\"}]}";

    private static final SimpleDateFormat YYYYMMDDHH = new SimpleDateFormat("yyyyMMddHH");

    private String endpoint, accessKey, secretKey;

    @Autowired
    private UserImagesMapper userImagesMapper;
    @Autowired
    private AdvertResourceMapper advertResourceMapper;
    @Autowired
    private OpenDoorImagesMapper openDoorImagesMapper;
    @Autowired
    private JedisCluster jedisCluster;

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
     * @return java.util.List<com.github.wxiaoqi.security.admin.camera.minio.MinioItem>
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
     * 上传广告素材文件或升级文件，返回路径
     *
     * @param objectName  文件名称
     * @param stream      文件流
     * @param size        文件大小
     * @param contentType 文件类型
     * @return java.lang.String
     * @throws Exception 异常信息
     * @Author shenlele
     * @Date 2019/7/9 15:19
     */
    public String uploadAdvert(String objectName, InputStream stream, long size, String contentType, String type) throws Exception {
        //上传文件
        String path = this.uploadFile(CommonConstant.ADVERT_BUCKET_NAME, objectName, stream, size, contentType);
        AdvertResource resource = new AdvertResource(objectName, CommonConstant.ADVERT_BUCKET_NAME, path, type);
        advertResourceMapper.insertSelective(resource);
        return path;
    }

    /**
     * 上传用户注册图片，桶名按照社区编号
     *
     * @param estateCode  社区编号
     * @param objectName  文件名称
     * @param stream      文件流
     * @param size        文件大小
     * @param contentType 文件类型
     * @return java.lang.String
     * @throws Exception 异常信息
     * @Author shenlele
     * @Date 2019/7/17 15:09
     */
    public String uploadUser(String estateCode, String objectName, InputStream stream, long size, String contentType) throws Exception {
        String bucketName = CommonConstant.PRE + estateCode;
        String key = CommonConstant.MINIO_BUCKET_IF + bucketName;
        //拼接判断桶是否存在的key,没有则创建
        if (!jedisCluster.exists(key)) {
            this.createBucket(bucketName);
            jedisCluster.set(key, "yes");
        }
        //上传文件
        String path = this.uploadFile(bucketName, objectName, stream, size, contentType);
        UserImages model = new UserImages(objectName, bucketName, path);
        userImagesMapper.insertSelective(model);
        return path;
    }

    /**
     * 上传用户开门图片，根据每小时分桶
     *
     * @param objectName  文件名称
     * @param stream      文件流
     * @param size        文件大小
     * @param contentType 文件类型
     * @return java.lang.String
     * @throws Exception 异常信息
     * @Author shenlele
     * @Date 2019/7/17 16:43
     */
    public String uploadOpenDoor(String objectName, InputStream stream, long size, String contentType) throws Exception {
        String bucketName = CommonConstant.PRE + YYYYMMDDHH.format(new Date());
        String key = CommonConstant.MINIO_BUCKET_IF + bucketName;
        //拼接判断桶是否存在的key,没有则创建
        if (!jedisCluster.exists(key)) {
            this.createBucket(bucketName);
            jedisCluster.set(key, "yes");
        }
        //上传文件
        String path = this.uploadFile(bucketName, objectName, stream, size, contentType);
        OpenDoorImages model = new OpenDoorImages(objectName, bucketName, path);
        openDoorImagesMapper.insertSelective(model);
        return path;
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
     * 删除指定文件,放到redis
     *
     * @param bucketName 桶名称
     * @param objectName 文件名称
     * @param type       删除的类型（0:广告或升级文件，1:用户图片，2:开门图片）
     * @return void
     * @throws Exception 异常信息
     * @Author shenlele
     * @Date 2019/7/9 15:20
     */
    public void removeObjectToRedis(String bucketName, String objectName, String type) throws Exception {
        //数据库删除
        if (type.equals(CommonConstant.DEFAULT_ZERO)) {
            AdvertResource model = new AdvertResource(objectName, bucketName, null, null);
            advertResourceMapper.delete(model);
        } else if (type.equals(CommonConstant.DEFAULT_ONE)) {
            UserImages model = new UserImages(objectName, bucketName, null);
            userImagesMapper.delete(model);
        } else if (type.equals(CommonConstant.DEFAULT_TWO)) {
            OpenDoorImages model = new OpenDoorImages(objectName, bucketName, null);
            openDoorImagesMapper.delete(model);
        } else {
            throw new Exception("删除文件类型未知，所传数据类型为：type:" + type);
        }
        //文件地址放入到redis
        FileVo fileVo = new FileVo(type, bucketName, objectName);
        jedisCluster.lpush(CommonConstant.DELETE_KEY, JsonUtils.beanToJson(fileVo));
    }

    /**
     * 删除文件服务器文件
     *
     * @param bucketName 桶名
     * @param objectName 文件名
     * @return void
     * @throws Exception 异常信息
     * @Author shenlele
     * @Date 2019/7/22 9:53
     */
    public void removeObject(String bucketName, String objectName) throws Exception {
        getMinioClient().removeObject(bucketName, objectName);
    }

    /**
     * 删除指定桶内全部文件
     *
     * @param bucketName 桶名称
     * @param type       删除的类型（0:广告或升级文件，1:用户图片，2:开门图片）
     * @return void
     * @throws Exception 异常信息
     * @Author shenlele
     * @Date 2019/7/9 15:20
     */
    public void removeObjectAll(String bucketName, String type) throws Exception {
        MinioClient client = getMinioClient();
        List<String> list = new LinkedList<>();
        Iterable<Result<Item>> iterable = client.listObjects(bucketName, null, true);
        iterable.forEach(itemResult -> {
            try {
                list.add(itemResult.get().objectName());
            } catch (Exception e) {
                new Exception(e);
            }
        });
        client.removeObjects(bucketName, list);
        //数据库删除
        if (type.equals(CommonConstant.DEFAULT_ZERO)) {
            AdvertResource model = new AdvertResource(null, bucketName, null, null);
            advertResourceMapper.delete(model);
        } else if (type.equals(CommonConstant.DEFAULT_ONE)) {
            UserImages model = new UserImages(null, bucketName, null);
            userImagesMapper.delete(model);
        } else if (type.equals(CommonConstant.DEFAULT_TWO)) {
            OpenDoorImages model = new OpenDoorImages(null, bucketName, null);
            openDoorImagesMapper.delete(model);
        } else {
            throw new Exception("删除文件类型未知，所传数据类型为：type:" + type);
        }
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

    private String uploadFile(String bucketName, String objectName, InputStream stream, long size, String contentType) throws Exception {
//        不过时的方法所需密匙生成
//        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
//        keyGen.init(256);
//        ServerSideEncryption sse = ServerSideEncryption.withCustomerKey(keyGen.generateKey());
//        getMinioClient().client.putObject(bucketName, objectName, stream, size, null, sse contentType);
        MinioClient client = getMinioClient();
        log.info("------------------------------------------------------------------------");
        log.info("--------------文件服务器上传文件，准备好了，让我们开始吧！--------------");
        client.putObject(bucketName, objectName, stream, size, contentType);
        log.info("--------------文件服务器上传文件，上传完了，让我们结束吧！--------------");
        log.info("------------------------------------------------------------------------");
        //返回路径
        return client.getObjectUrl(bucketName, objectName);
    }
}
