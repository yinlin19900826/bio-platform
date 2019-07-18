package com.biocome.video;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDiscoveryClient
//@EnableFeignClients({"com.biocome.microservice.feign"})
@EnableFeignClients({"com.biocome.platform.auth.client.feign"})
@EnableCircuitBreaker
@MapperScan("com.biocome.video.mapper")
@EnableSwagger2Doc
//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class VideoServiceApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(VideoServiceApplication.class).run(args);
       // SpringApplication.run(VideoServiceApplication.class, args);
    }
}
