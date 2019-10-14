package com.biocome.video;


import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
//import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.cloud.openfeign.EnableFeignClients;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients({"com.biocome.platform.auth.client.feign"})
@EnableCircuitBreaker
@MapperScan("com.biocome.video.mapper")
@EnableSwagger2Doc
public class VideoServiceApplication {
    public static void main(String[] args) {
       new SpringApplicationBuilder(VideoServiceApplication.class).run(args);
       // SpringApplication.run(VideoServiceApplication.class, args);
    }
}
