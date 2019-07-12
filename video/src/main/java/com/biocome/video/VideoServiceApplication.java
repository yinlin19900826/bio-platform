package com.biocome.video;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class VideoServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(VideoServiceApplication.class, args);
    }
}
