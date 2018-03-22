package com.clom.my.util.result;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Map;

/**
 * 移动端报文解析实体类
 * @author luwanding
 * */
public class MobilePackageBeanTest implements Serializable {

    @Getter
    @Setter
    @NotNull(message = "请求数据格式错误,未找到head节点")
    @Valid // 该参数用来注释实体里面的属性是否需要验证
    private head head;

    @Getter
    @Setter
    @NotNull(message = "请求数据格式错误,未找到data节点")
    private Map<String,Object> data;


    public class head{
        @Getter
        @Setter
        @NotNull(message = "请输入交易码")
        private String transcode;
        @Getter
        @Setter
        @NotNull(message = "请输入请求终端类型")
        private String type;
    }

}
