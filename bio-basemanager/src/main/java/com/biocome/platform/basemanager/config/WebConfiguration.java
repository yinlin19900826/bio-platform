package com.biocome.platform.basemanager.config;

import com.biocome.platform.auth.client.interceptor.ServiceAuthRestInterceptor;
import com.biocome.platform.auth.client.interceptor.UserAuthRestInterceptor;
import com.biocome.platform.common.handler.GlobalExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author ace
 * @date 2017/9/8
 */
@Configuration("admimWebConfig")
@Primary
public class WebConfiguration implements WebMvcConfigurer {
    @Bean
    GlobalExceptionHandler getGlobalExceptionHandler() {
        return new GlobalExceptionHandler();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getServiceAuthRestInterceptor()).
                addPathPatterns(getIncludePathPatterns()).addPathPatterns("/api/user/validate");
        registry.addInterceptor(getUserAuthRestInterceptor()).
                addPathPatterns(getIncludePathPatterns());
    }

    @Bean
    ServiceAuthRestInterceptor getServiceAuthRestInterceptor() {
        return new ServiceAuthRestInterceptor();
    }

    @Bean
    UserAuthRestInterceptor getUserAuthRestInterceptor() {
        return new UserAuthRestInterceptor();
    }

    /**
     * 需要用户和服务认证判断的路径
     * @return
     */
    private ArrayList<String> getIncludePathPatterns() {
        ArrayList<String> list = new ArrayList<>();
        String[] urls = {
                "/element/**",
                "/gateLog/**",
                "/group/**",
                "/groupType/**",
                "/menu/**",
//                "/user/**",
                "/api/permissions",
                "/api/user/un/**",
                "camera",
                "cameraGroup",
                "cameraPipeline",
                ""
        };
        Collections.addAll(list, urls);
        return list;
    }

    @Bean
    public Docket admin_api(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("管理员模块")
                .apiInfo(apiAdminInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.biocome.platform.basemanager.rest"))//api接口包扫描路径
                .paths(PathSelectors.regex("/basemanager.*/.*"))//可以根据url路径设置哪些请求加入文档，忽略哪些请求
                .build();
    }

    private ApiInfo apiAdminInfo(){
        return new ApiInfoBuilder()
                .title("管理员")
                .description("管理员操作")
                .version("V1.0.0").build();
    }

    @Bean
    public Docket camera_api(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("视频设备模块")
                .apiInfo(apiCameraInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.biocome.platform.basemanager.rest"))//api接口包扫描路径
                .paths(PathSelectors.regex("/camera.*/.*"))//可以根据url路径设置哪些请求加入文档，忽略哪些请求
                .build();
    }

    private ApiInfo apiCameraInfo(){
        return new ApiInfoBuilder()
                .title("视频设备")
                .description("视频设备")
                .version("V1.0.0").build();
    }
}
