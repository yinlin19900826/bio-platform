package com.biocome.platform.inter.basemanager.rpc.service;

import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.inter.basemanager.vo.upload.FileVo;
import com.biocome.platform.inter.basemanager.rpc.fallbackfactory.FileFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author hxy
 * @date 2019/7/17 18:19
 */
@FeignClient(name = "file", url = "${file.service.uri}", fallbackFactory = FileFallbackFactory.class)
public interface FileRpc {

    /**
     * 远程调用批量删除广告文件
     *
     * @param fileVos
     * @return com.biocome.platform.common.msg.ObjectRestResponse
     * @Author shenlele
     * @Date 2019/7/22 10:07
     */
    @RequestMapping(value = {"/object/deleteListObject"}, method = {RequestMethod.DELETE}, produces = {"application/json;charset=UTF-8"})
    ObjectRestResponse fileDel(@RequestBody List<FileVo> fileVos);
}
