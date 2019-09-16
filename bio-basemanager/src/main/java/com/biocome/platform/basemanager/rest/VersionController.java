package com.biocome.platform.basemanager.rest;

import com.biocome.platform.common.msg.BaseResponse;
import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.common.rest.BaseController;
import com.biocome.platform.basemanager.biz.VersionBiz;
import com.biocome.platform.inter.basemanager.entity.Version;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author hxy
 * @date 2019/7/2 14:01
 */
@Controller
@RequestMapping("/version")
@Api(value = "客户端版本管理", tags = {"客户端版本管理"})
public class VersionController extends BaseController<VersionBiz, Version> {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    VersionBiz versionBiz;

    @RequestMapping(value = "/uploadClient", method = RequestMethod.POST)
    @ApiOperation(value = "上传客户端版本")
    @ResponseBody
    public BaseResponse uploadClient(@RequestPart("object") MultipartFile object
            , String filename, String version, String comment) {
        BaseResponse resp = new BaseResponse();
        String newVersion = baseBiz.verifyVersion(version);
        if (newVersion.equals("0")) {
            resp.setStatus(500);
            resp.setMessage("版本号有误");
            return resp;
        }
        try {
            versionBiz.uploadClient(object, filename, newVersion, comment);
        } catch (Exception e) {
            logger.error("上传客户端版本发生异常：{}", e.getMessage());
            resp.setMessage("上传失败");
            resp.setStatus(500);
            e.printStackTrace();
        }

        return resp;
    }

    @RequestMapping(value = "/getVersion", method = RequestMethod.POST)
    @ApiOperation(value = "获取客户端最新版本")
    @ResponseBody
    public ObjectRestResponse<List<Version>> getVersion() {
        List<Version> list = baseBiz.getVersionList();
        return new ObjectRestResponse<List<Version>>().data(list);
    }

}
