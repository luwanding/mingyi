package com.clom.my.dbconfig;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

/**
 * Created by clovermmmmmmmmmmmmm on 2018/3/21 0021.
 */
@Configuration
@EnableAutoConfiguration
@Slf4j
public class FactoryConfig {
    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor(){
        return new MethodValidationPostProcessor();
    }

}
