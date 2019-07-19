package com.biocome.platform.guard.rpc.service;

import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.guard.rpc.fallbackfactory.FileFallbackFactory;
import com.biocome.platform.inter.basemanager.vo.upload.FileVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.net.URI;
import java.util.List;

/**
 * @author hxy
 * @date 2019/7/17 18:19
 */
@FeignClient(name = "file", url = "${file.service.uri}", fallbackFactory = FileFallbackFactory.class)
public interface FileRpc {

    @RequestMapping(value = {"/object/deleteListObject"}, method = {RequestMethod.DELETE}, produces = {"application/json;charset=UTF-8"})
    ObjectRestResponse fileDel(@RequestBody List<FileVo> fileVos);
}
