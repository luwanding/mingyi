package com.clom.my.annotation;

/**
 * Created by clovermmmmmmmmmmmmm on 2018/3/16 0016.
 */

import java.lang.annotation.*;

/**
 * 使用主库的注解
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface ReadDataSource {

}