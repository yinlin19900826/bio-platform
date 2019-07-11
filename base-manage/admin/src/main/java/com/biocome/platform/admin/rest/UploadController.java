package com.biocome.platform.admin.rest;

import com.biocome.platform.admin.biz.ResourceBiz;
import com.biocome.platform.admin.entity.Resource;
import com.biocome.platform.admin.fastdfs.FastDFSClientUtil;
import com.biocome.platform.admin.vo.upload.UploadResp;
import com.biocome.platform.common.msg.auth.BaseRpcResponse;
import com.biocome.platform.common.util.DateUtils;
import com.biocome.platform.common.util.UUIDUtils;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 * @ClassName: UploadController
 * @Author: shenlele
 * @Date: 2019/5/16 17:36
 * @Description:
 */
@Controller
@RequestMapping("upload")
@Api(value = "文件上传下载", tags = {"文件上传下载操作"})
public class UploadController {
    Logger logger = LoggerFactory.getLogger(UploadController.class);
    @Autowired
    FastDFSClientUtil fastDFSClientUtil;
    @Autowired
    ResourceBiz resourceBiz;

    @ResponseBody
    @ApiOperation(value = "上传文件")
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public UploadResp getUploadFile(@ApiParam(name = "file", value = "文件内容", required = true) @RequestParam("file") MultipartFile file,String filetype) {
        UploadResp resp = new UploadResp();
        try {
            String str = fastDFSClientUtil.uploadFile(file);
            Resource resource = new Resource(UUIDUtils.generateShortUuid(), str, filetype, DateUtils.getCurrentDate());
            resourceBiz.insert(resource);
            resp.setGroupname("biocome");
            resp.setPath(str);
            resp.setErrorcode("");
            resp.setMessage("上传成功");
            resp.setResult("1");
        } catch (Exception e) {
            logger.info("------获取设备列表时发生异常------");
            logger.info("异常信息:{}", e.getMessage());
            resp.setGroupname("biocome");
            resp.setErrorcode("500");
            resp.setMessage("上传失败或存库失败");
            resp.setResult("0");
        }
        return resp;
    }

    @ResponseBody
    @ApiOperation(value = "上传文件")
    @RequestMapping(value = "/file", method = RequestMethod.POST)
    @ApiImplicitParams({@ApiImplicitParam(name = "accesskey", value = "厂商编号", paramType = "query"),
            @ApiImplicitParam(name = "filetype", value = "文件类型（0：图片 1：视频 2 其他 ）", paramType = "query"),
            @ApiImplicitParam(name = "sn", value = "设备编号", paramType = "query"),
            @ApiImplicitParam(name = "filename", value = "文件名称", paramType = "query")
    })
    public BaseRpcResponse file(@ApiParam(name = "file", value = "文件内容", required = true) @RequestParam("file") MultipartFile file,
                                String accesskey,
                                String sn,
                                String filetype,
                                String filename) {
        try {
            String url = fastDFSClientUtil.uploadFile(file);
            Resource resource = new Resource(filename, url, filetype, DateUtils.getCurrentDate());
            resourceBiz.insert(resource);
            return new BaseRpcResponse(url, "1", "");
        } catch (Exception e) {
            logger.info("------获取设备列表时发生异常------");
            logger.info("异常信息:{}", e.getMessage());
            return new BaseRpcResponse().failure();
        }
    }

    @ResponseBody
    @ApiOperation(value = "删除文件")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public BaseRpcResponse delete(String fileUrl) {
        try {
            fastDFSClientUtil.deleteFile(fileUrl);
            return new BaseRpcResponse().success();
        } catch (Exception e) {
            logger.info("异常信息:{}", e.getMessage());
            return new BaseRpcResponse().failure();
        }
    }

}
