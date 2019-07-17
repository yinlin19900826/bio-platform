package com.biocome.platform.guard;

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
 * @ClassName: FileServiceBootstrap
 * @Author: shenlele
 * @Date: 2019/7/11 09:24
 * @Description:
 */

@EnableDiscoveryClient
@EnableCircuitBreaker
@SpringBootApplication(scanBasePackages = "com.biocome.platform.inter.basemanager.*")
@EnableFeignClients({"com.biocome.platform.auth.client.feign","com.biocome.platform.guard.rpc.service"})
@EnableScheduling
@EnableAceAuthClient
@EnableTransactionManagement
@MapperScan({"com.biocome.platform.guard.mapper", "com.biocome.platform.inter.basemanager.mapper"})
@EnableSwagger2Doc
@ComponentScan({"com.biocome.platform"})
public class GateGuardBootstrap {
    public static void main(String[] args) {
        new SpringApplicationBuilder(GateGuardBootstrap.class).run(args);
    }
}
