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
public class MangeInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object o ){
        log.debug("后台拦截请求");
        return true;
    }
}
