package com.biocome.platform.wechatapplet;

import com.biocome.platform.auth.client.EnableAceAuthClient;
import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author hxy
 * @date 2019/7/29 11:35
 */
@EnableDiscoveryClient
@EnableCircuitBreaker
@SpringBootApplication
@EnableFeignClients({"com.biocome.platform.auth.client.feign", "com.biocome.platform.wechatapplet.rpc.service"})
@EnableScheduling
@EnableAceAuthClient
@EnableTransactionManagement
@MapperScan({"com.biocome.platform.wechatapplet.mapper", "com.biocome.platform.inter.*.mapper"})
@EnableSwagger2Doc
@ComponentScan({"com.biocome.platform"})
public class WechatAppletBootstrap {
    public static void main(String[] args) {
        new SpringApplicationBuilder(WechatAppletBootstrap.class).run(args);
    }
}
