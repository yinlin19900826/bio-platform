package com.biocome.platform.wechatapplet.config;

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
@Configuration("wechatAppletConfig")
@Primary
public class WebConfiguration implements WebMvcConfigurer {
    @Bean
    GlobalExceptionHandler getGlobalExceptionHandler() {
        return new GlobalExceptionHandler();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getUserAuthRestInterceptor())
                .excludePathPatterns(excludePatPatterns())
                .addPathPatterns(getIncludePathPatterns());
    }

    @Bean
    UserAuthRestInterceptor getUserAuthRestInterceptor(){
        return new UserAuthRestInterceptor();
    }

    /**
     * 需要用户和服务认证判断的路径
     * @return
     */
    private ArrayList<String> getIncludePathPatterns() {
        ArrayList<String> list = new ArrayList<>();
        String[] urls = {
                "/app/user/**",
                "/audit/**",
                "/buildDetail/**",
                "/card/**",
                "/cardmanage/**",
                "/deviceDetail/**",
                "/faultdeclare/**",
                "/openDoor/**",
                "/refund/**",
                "/userDetail/**",
                "/visit/**",
                "/sms/**",
        };
        Collections.addAll(list, urls);
        return list;
    }

    /**
     * 不需要用户和服务认证判断的路径
     * @return
     */
    private ArrayList<String> excludePatPatterns() {
        ArrayList<String> list = new ArrayList<>();
        String[] urls = {
                "/app/user/validate",
                "/app/user/resetPassword",
                "/app/user/createAppAccount",
                "/sms/sendResetSMS",
                "**/swagger**"
        };
        Collections.addAll(list, urls);
        return list;
    }
}
