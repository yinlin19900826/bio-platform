package com.biocome.platform.wechatapplet.rpc.service;

import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.wechatapplet.rpc.fallbackfactory.FileFallbackFactory;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author hxy
 * @date 2019/7/31 17:10
 */
@FeignClient(name = "file", url = "${fileservice.url}", configuration = FileRpc.MultipartSupportConfig.class)
public interface FileRpc {

    @PostMapping(value = "/object/userImg/{estateCode}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ObjectRestResponse userImg(@RequestPart("object") MultipartFile object,@PathVariable("estateCode") String estateCode);

    @Configuration
    class MultipartSupportConfig {
        @Bean
        public Encoder feignFormEncoder() {
            return new SpringFormEncoder();
        }
    }

}
