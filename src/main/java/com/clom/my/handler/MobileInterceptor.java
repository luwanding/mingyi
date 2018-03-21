package com.clom.my.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 移动端请求拦截
 * @author luwanding
 * */
@Slf4j
public class MobileInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object o ){
        log.debug("移动端请求拦截");
        return true;
    }
}
