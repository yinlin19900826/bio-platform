package com.biocome.platform.file;

import com.biocome.platform.auth.client.EnableAceAuthClient;
import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @ClassName: FileServiceBootstrap
 * @Author: shenlele
 * @Date: 2019/7/11 09:24
 * @Description:
 */
@EnableDiscoveryClient
@EnableCircuitBreaker
@SpringBootApplication
@EnableFeignClients({"com.biocome.platform.auth.client.feign"})
@EnableAceAuthClient
@MapperScan("com.biocome.platform.file.mapper")
@EnableSwagger2Doc
public class FileServiceBootstrap {
    public static void main(String[] args) {
        new SpringApplicationBuilder(FileServiceBootstrap.class).run(args);
    }
}
