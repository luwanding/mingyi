package com.clom.my.util.result;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 移动端报文解析实体类
 * @author luwanding
 * */
public class MobilePackageBean implements Serializable {
    @Getter
    @Setter
    private String transCode;
    @Getter
    @Setter
    private String packageFormat;
    @Getter
    @Setter
    private int length;
    @Getter
    @Setter
    private String p;
    @Getter
    @Setter
    private String terminalType;
    @Getter
    @Setter
    private Object content;
    @Getter
    @Setter
    private String returnCode = "AAAAAAA";

}
