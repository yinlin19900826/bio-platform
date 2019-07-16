package com.biocome.platform.basemanager;

import com.ace.cache.EnableAceCache;
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
 * @date 2019/7/12 13:51
 */
@EnableDiscoveryClient
@EnableCircuitBreaker
@SpringBootApplication(scanBasePackages = "com.biocome.platform.*.basemanager.*")
@EnableFeignClients({"com.biocome.platform.auth.client.feign", "com.biocome.platform.basemanager.rpc.service"})
@EnableScheduling
@EnableAceAuthClient
@EnableAceCache
@EnableTransactionManagement
@MapperScan({"com.biocome.platform.basemanager.mapper", "com.biocome.platform.inter.basemanager.mapper"})
@EnableSwagger2Doc
@ComponentScan({"com.biocome.platform"})
public class BaseManagerBootstrap {
    public static void main(String[] args) {
        new SpringApplicationBuilder(BaseManagerBootstrap.class).run(args);
    }
}
