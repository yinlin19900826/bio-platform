package com.biocome.platform.file.rest;

import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.common.util.ValidateUtils;
import com.biocome.platform.file.biz.MinioTemplateBiz;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: MinioEndpointController
 * @Author: shenlele
 * @Date: 2019/7/9 10:36
 * @Description:
 */
@ConditionalOnProperty(name = "minio.endpoint.enable", havingValue = "true")
@RestController
@RequestMapping("${minio.endpoint.name:/minio}")
@Api(value = "MinIO操作", tags = {"MinIO操作"})
public class MinioEndpointController {

    private Logger log = LoggerFactory.getLogger(MinioEndpointController.class);

    private static final Integer ONE_DAY = 3600 * 24;

    @Autowired
    private MinioTemplateBiz template;

    @ApiOperation("创建新存储桶")
    @ApiImplicitParam(name = "bucketName", value = "桶名称", paramType = "path")
    @PostMapping("/bucket/{bucketName}")
    public ObjectRestResponse createBucker(@PathVariable String bucketName) {
        try {
            template.createBucket(bucketName);
            return new ObjectRestResponse().success();
        } catch (Exception e) {
            log.info("创建新储存桶失败，错误信息为：{}", e.getMessage());
            return new ObjectRestResponse().failure();
        }
    }

    @ApiOperation("获取所有存储桶信息")
    @GetMapping("/bucket")
    public ObjectRestResponse getBuckets() {
        try {
            return new ObjectRestResponse().success().data(template.getAllBuckets());
        } catch (Exception e) {
            log.info("获取所有存储桶信息失败，错误信息为：{}", e.getMessage());
            return new ObjectRestResponse().failure();
        }
    }

    @ApiOperation("获取指定存储桶信息")
    @ApiImplicitParam(name = "bucketName", value = "桶名称", paramType = "path")
    @GetMapping("/bucket/{bucketName}")
    public ObjectRestResponse getBucket(@PathVariable String bucketName) {
        try {
            return new ObjectRestResponse().success().data(template.getBucket(bucketName).orElseThrow(() -> new IllegalArgumentException("指定桶信息未找到!")));
        } catch (Exception e) {
            log.info("获取指定存储桶信息失败，错误信息为：{}", e.getMessage());
            return new ObjectRestResponse().failure();
        }
    }

    @ApiOperation("根据桶名称删除桶")
    @ApiImplicitParam(name = "bucketName", value = "桶名称", paramType = "path")
    @DeleteMapping("/bucket/{bucketName}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ObjectRestResponse deleteBucket(@PathVariable String bucketName) {
        try {
            template.removeBucket(bucketName);
            return new ObjectRestResponse().success();
        } catch (Exception e) {
            log.info("根据桶名称删除桶失败，错误信息为：{}", e.getMessage());
            return new ObjectRestResponse().failure();
        }
    }

    @ApiOperation("上传文件")
    @ApiImplicitParam(name = "bucketName", value = "桶名称", paramType = "path")
    @PostMapping("/object/{bucketName}")
    public ObjectRestResponse createObject(@RequestBody MultipartFile object, @PathVariable String bucketName, String type) {
        try {
            String name = object.getOriginalFilename();
            template.saveObject(bucketName, name, object.getInputStream(), object.getSize(), object.getContentType(), type);
            return new ObjectRestResponse().success();
        } catch (Exception e) {
            log.info("上传文件失败，错误信息为：{}", e.getMessage());
            return new ObjectRestResponse().failure();
        }

    }

    @ApiOperation("上传文件并重命名")
    @ApiImplicitParams({@ApiImplicitParam(name = "bucketName", value = "桶名称", paramType = "path"),
            @ApiImplicitParam(name = "objectName", value = "文件名", paramType = "path")})
    @PostMapping("/object/{bucketName}/{objectName}")
    public ObjectRestResponse createObject(@RequestBody MultipartFile object, @PathVariable String bucketName, @PathVariable String objectName, String type) {
        try {
            template.saveObject(bucketName, objectName, object.getInputStream(), object.getSize(), object.getContentType(), type);
            return new ObjectRestResponse().success();
        } catch (Exception e) {
            log.info("上传文件并重命名失败，错误信息为：{}", e.getMessage());
            return new ObjectRestResponse().failure();
        }
    }

    @ApiOperation("获取给定存储桶中的对象信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "bucketName", value = "桶名称", paramType = "path"),
            @ApiImplicitParam(name = "objectName", value = "文件名", paramType = "path")})
    @GetMapping("/object/{bucketName}/{objectName}")
    public ObjectRestResponse filterObject(@PathVariable String bucketName, @PathVariable String objectName) {
        try {
            return new ObjectRestResponse().success().data(template.getAllObjectsByPrefix(bucketName, objectName, true));
        } catch (Exception e) {
            log.info("获取给定存储桶中的对象信息失败，错误信息为：{}", e.getMessage());
            return new ObjectRestResponse().failure();
        }
    }

    @ApiOperation("为文件生成预签名URL。此预签名URL可以具有相关的到期时间（以秒为单位），之后它将不再运行。默认到期时间设置为1天，最多为7天")
    @GetMapping("/object/{bucketName}/{objectName}/{expires}")
    public ObjectRestResponse getObject(@PathVariable String bucketName, @PathVariable String objectName, @PathVariable Integer expires) {
        if (ValidateUtils.isNotEmpty(expires)) {
            expires = ONE_DAY;
        }
        Map<String, Object> responseBody = new HashMap<>(4);
        // Put Object info
        responseBody.put("bucket", bucketName);
        responseBody.put("object", objectName);
        try {
            responseBody.put("url", template.getObjectURL(bucketName, objectName, expires));
        } catch (Exception e) {
            log.info("为文件生成预签名URL失败，错误信息为：{}", e.getMessage());
            return new ObjectRestResponse().failure();
        }
        responseBody.put("expires", expires);
        return new ObjectRestResponse().success().data(responseBody);
    }

    @ApiOperation("删除指定对象文件")
    @ApiImplicitParams({@ApiImplicitParam(name = "bucketName", value = "桶名称", paramType = "path"),
            @ApiImplicitParam(name = "objectName", value = "文件名", paramType = "path")})
    @DeleteMapping("/object/{bucketName}/{objectName}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ObjectRestResponse deleteObject(@PathVariable String bucketName, @PathVariable String objectName) {
        try {
            template.removeObject(bucketName, objectName);
            return new ObjectRestResponse().success();
        } catch (Exception e) {
            log.info("删除指定对象文件失败，错误信息为：{}", e.getMessage());
            return new ObjectRestResponse().failure();
        }
    }

    @ApiOperation("删除指定桶内全部文件")
    @ApiImplicitParam(name = "bucketName", value = "桶名称", paramType = "path")
    @DeleteMapping("/object/all/{bucketName}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ObjectRestResponse deleteObjectAll(@PathVariable String bucketName) {
        try {
            template.removeObjectAll(bucketName);
            return new ObjectRestResponse().success();
        } catch (Exception e) {
            log.info("删除指定桶内全部文件失败，错误信息为：{}", e.getMessage());
            return new ObjectRestResponse().failure();
        }
    }

    @ApiOperation("下载保存指定对象文件")
    @ApiImplicitParams({@ApiImplicitParam(name = "bucketName", value = "桶名称", paramType = "path"),
            @ApiImplicitParam(name = "objectName", value = "文件名", paramType = "path"),
            @ApiImplicitParam(name = "fileName", value = "下载文件名", paramType = "path")})
    @GetMapping("/object/save/{bucketName}/{objectName}/{fileName}")
    public ObjectRestResponse getObject(@PathVariable String bucketName, @PathVariable String objectName, @PathVariable String fileName) {
        try {
            template.getObjectToSave(bucketName, objectName, fileName);
            return new ObjectRestResponse().success();
        } catch (Exception e) {
            log.info("下载保存指定对象文件失败，错误信息为：{}", e.getMessage());
            return new ObjectRestResponse().failure();
        }
    }
}
