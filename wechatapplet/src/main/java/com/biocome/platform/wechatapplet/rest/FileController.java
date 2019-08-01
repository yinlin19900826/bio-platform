package com.biocome.platform.wechatapplet.rest;

import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.wechatapplet.rpc.service.FileRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author hxy
 * @date 2019/7/31 17:28
 */
@RestController
@RequestMapping("/file")
@Api(value = "文件相关", tags = {"文件相关"})
public class FileController {

    @Autowired
    private FileRpc rpc;

    @ApiOperation("上传用户图片")
    @PostMapping("/userImg")
    public ObjectRestResponse<String> userImg(@RequestPart("object") MultipartFile object) {
        return rpc.userImg(object,"appuserimg");
    }

}
