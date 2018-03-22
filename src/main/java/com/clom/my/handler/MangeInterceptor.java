package com.clom.my.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 后台拦截请求
 * @author luwanding
 * */
@Slf4j
@Component
public class MangeInterceptor implements WebMvcConfigurer,HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object o ){
        log.debug("后台拦截请求");
        return true;
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        log.debug("进入后台拦截器");
        InterceptorRegistration ir = registry.addInterceptor(new MangeInterceptor());
        ir.addPathPatterns("/manage/**");//设置拦截路径
        ir.excludePathPatterns("/");//设置不拦截路径
    }
}
