package com.clom.my.util.result;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by clovermmmmmmmmmmmmm on 2018/3/21 0021.
 */
public class Result<T> {

    /**
     * 错误码
     */
    @Getter
    @Setter
    private String code;
    /**
     * 提示信息
     */
    @Getter
    @Setter
    private String msg;

    /**
     * 返回的具体内容
     */
    @Getter
    @Setter
    private T data;
}
