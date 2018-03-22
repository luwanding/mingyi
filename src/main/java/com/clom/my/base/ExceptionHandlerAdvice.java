package com.clom.my.base;

import com.clom.my.util.mathUtil;
import com.clom.my.util.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by clovermmmmmmmmmmmmm on 2018/3/22 0022.
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class ExceptionHandlerAdvice {
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        log.error("请求异常--",e);
        return mathUtil.error(APIConstants.API_RETURN_CODE_SERVER_ERROR,"网络异常,请稍后重试--"+e.getMessage());
    }
}
