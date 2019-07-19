package com.biocome.platform.basemanager.rpc.service;

import com.biocome.platform.basemanager.rpc.fallbackfactory.FileFallbackFactory;
import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.inter.basemanager.vo.upload.FileVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author hxy
 * @date 2019/7/17 18:19
 */
@FeignClient(name = "file", url = "${file.service.uri}", fallbackFactory = FileFallbackFactory.class)
public interface FileRpc {

    @RequestMapping(value = {"/object/deleteListObject"}, method = {RequestMethod.DELETE}, produces = {"application/json;charset=UTF-8"})
    ObjectRestResponse fileDel(@RequestBody List<FileVo> fileVos);

    @RequestMapping(value = {"/object/{type}"}, method = {RequestMethod.POST}, produces = {"application/json;charset=UTF-8"})
    ObjectRestResponse fileUpload(@RequestBody MultipartFile object, @PathVariable("type") String type);
}
