package com.clom.my.aop;

import com.clom.my.dbconfig.DataSourceContextHolder;
import com.clom.my.dbconfig.DataSourceType;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

/**
 * Created by clovermmmmmmmmmmmmm on 2018/3/16 0016.
 */
@Aspect
@EnableAspectJAutoProxy(exposeProxy=true,proxyTargetClass=true)
@Component
public class DataSourceAopInService implements PriorityOrdered {

	@Before("execution(* com.clom.my.service..*.find*(..)) "
			+ " or execution(* com.clom.my.service..*.get*(..)) "
			+ " or execution(* com.clom.my.service..*.select*(..)) "
			+ " or execution(* com.clom.my.service..*.query*(..))")
    public void setReadDataSourceType() {
		//如果已经开启写事务了，那之后的所有读都从写库读
		if(!DataSourceType.write.getType().equals(DataSourceContextHolder.getReadOrWrite())){
			DataSourceContextHolder.setRead();
		}

    }

    @Before("execution(* com.clom.my.service..*.insert*(..)) "
    		+ " or execution(* com.clom.my.service..*.update*(..))"
    		+ " or execution(* com.clom.my.service..*.del*(..))"
    		+ " or execution(* com.clom.my.service..*.add*(..))")
    public void setWriteDataSourceType() {
        DataSourceContextHolder.setWrite();
    }


   /* @Before("execution(* com.clom.my.service..*.select*(..)) || " +
            "execution(* com.clom.my.service..*.find*(..)) || " +
            "execution(* com.clom.my.service..*.query*(..)) || " +
            "execution(* com.clom.my.service..*.query*(..)) || " +
            + " and @annotation(com.clom.my.annotation.ReadDataSource) ")
    public void setReadDataSourceType() {
        //如果已经开启写事务了，那之后的所有读都从写库读
        if(!DataSourceType.write.getType().equals(DataSourceContextHolder.getReadOrWrite())){
            DataSourceContextHolder.setRead();
        }

    }

    @Before("execution(* com.clom.my.service..*.update*(..)) || " +
            "execution(* com.clom.my.service..*.save*(..)) || " +
            "execution(* com.clom.my.service..*.del*(..)) ||"
            + "|| @annotation(com.clom.my.annotation.WriteDataSource) ")
    public void setWriteDataSourceType() {
        DataSourceContextHolder.setWrite();
    }*/

    @Override
    public int getOrder() {
        /**
         * 值越小，越优先执行
         * 要优于事务的执行
         * 在启动类中加上了@EnableTransactionManagement(order = 10)
         */
        return 1;
    }
}
