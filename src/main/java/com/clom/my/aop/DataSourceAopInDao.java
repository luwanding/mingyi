package com.clom.my.aop;

import com.clom.my.dbconfig.DataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Created by clovermmmmmmmmmmmmm on 2018/3/16 0016.
 */
@Slf4j
/*@Aspect
@Component*/
public class DataSourceAopInDao {

    @Before("execution(* com.clom.my.dao..*.find*(..)) "
            + " or execution(* com.clom.my.dao..*.get*(..)) "
            + " or execution(* com.clom.my.dao..*.query*(..))")
    public void setReadDataSourceType() {
        DataSourceContextHolder.setRead();
    }

    @Before("execution(* com.clom.my.dao..*.insert*(..)) "
            + " or execution(* com.clom.my.dao..*.update*(..))"
            + " or execution(* com.clom.my.dao..*.add*(..))")
    public void setWriteDataSourceType() {
        DataSourceContextHolder.setWrite();
    }
}
