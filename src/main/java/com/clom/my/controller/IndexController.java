package com.clom.my.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Random;

/**
 * Created by clovermmmmmmmmmmmmm on 2018/3/12 0012.
 */
@RestController
@Slf4j
public class IndexController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping("/")
    public String index(){
        return "index.html111";
    }
    @RequestMapping("/index")
    public ModelAndView indexs(HttpServletRequest req, HttpServletResponse res){
        //log.debug("redis-->"+redisTemplate.opsForValue().get("123"));
        //redisTemplate.opsForValue().set("123",""+ Math.random());
        ModelAndView mv=new ModelAndView("index");//模板文件的名称，不需要指定后缀
        mv.addObject("key","what's you name ?");
        mv.addObject("key1","what's you name ?");
        return mv;
    }
}
