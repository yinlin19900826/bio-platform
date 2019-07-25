package com.biocome.platform.file.utils;

import com.biocome.platform.file.biz.MinioTemplateBiz;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: MinioAutoConfiguration
 * @Author: shenlele
 * @Date: 2019/7/9 10:36
 * @Description:
 */
@Configuration
public class MinioAutoConfiguration {

    @Value("${minio.url}")
    private String minioUrl;
    @Value("${minio.access-key}")
    private String minioAccess;
    @Value("${minio.secret-key}")
    private String minioSecret;

    @Bean
    @ConditionalOnMissingBean(MinioTemplateBiz.class)
    MinioTemplateBiz template() {
        return new MinioTemplateBiz(minioUrl, minioAccess, minioSecret);
    }
}
