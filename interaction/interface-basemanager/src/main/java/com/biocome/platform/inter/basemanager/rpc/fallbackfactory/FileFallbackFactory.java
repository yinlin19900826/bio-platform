package com.biocome.platform.inter.basemanager.rpc.fallbackfactory;

import com.biocome.platform.common.msg.ObjectRestResponse;
import com.biocome.platform.inter.basemanager.rpc.service.FileRpc;
import com.biocome.platform.inter.basemanager.vo.upload.FileVo;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author hxy
 * @date 2019/7/19 09:36
 */
public class FileFallbackFactory implements FallbackFactory<FileRpc> {
    private Logger log = LoggerFactory.getLogger(FileFallbackFactory.class);

    @Override
    public FileRpc create(Throwable throwable) {
        return new FileRpc() {
            @Override
            public ObjectRestResponse fileDel(List<FileVo> fileVos) {
                log.error("删除文件失败，被限流");
                return new ObjectRestResponse().failure();
            }
        };
    }
}
