package com.biocome.platform.wechatapplet.rpc.fallbackfactory;

import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.wechatapplet.rpc.service.FileRpc;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author hxy
 * @date 2019/7/31 17:25
 */
@Component
public class FileFallbackFactory implements FallbackFactory<FileRpc> {
    @Override
    public FileRpc create(Throwable throwable) {
        return new FileRpc() {
            @Override
            public ObjectRestResponse userImg(MultipartFile object,String estateCode) {
                return new ObjectRestResponse().failure();
            }
        };
    }
}
