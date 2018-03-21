package com.clom.my.handler;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//WebMvcConfigurerAdapter InterceptorRegistry
@Slf4j
@Component
public class MobileInterceptorConfiguration implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        log.debug("进入移动拦截器");
        InterceptorRegistration ir = registry.addInterceptor(new MobileInterceptor());
        ir.addPathPatterns("/mobile/**");//设置拦截路径
        ir.excludePathPatterns("/");//设置不拦截路径
    }

}
