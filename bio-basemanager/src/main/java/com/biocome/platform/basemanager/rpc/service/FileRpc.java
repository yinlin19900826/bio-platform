package com.biocome.platform.basemanager.rpc.service;

import com.biocome.platform.basemanager.rpc.fallbackfactory.FileFallbackFactory;
import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.inter.basemanager.vo.upload.FileVo;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author hxy
 * @date 2019/7/17 18:19
 */
@FeignClient(name = "file", url = "${file.service.uri}", fallbackFactory = FileFallbackFactory.class, configuration = FileRpc.MultipartSupportConfig.class)
public interface FileRpc {

    @RequestMapping(value = {"/object/deleteListObject"}, method = {RequestMethod.DELETE}, produces = {"application/json;charset=UTF-8"})
    ObjectRestResponse fileDel(@RequestBody List<FileVo> fileVos);

    @RequestMapping(value = {"/object/{type}"}, method = {RequestMethod.POST}, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ObjectRestResponse fileUpload(@RequestPart("object") MultipartFile object, @PathVariable("type") String type);

    @Configuration
    class MultipartSupportConfig {
        @Bean
        public Encoder feignFormEncoder() {
            return new SpringFormEncoder();
        }
    }
}
