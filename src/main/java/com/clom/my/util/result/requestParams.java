package com.clom.my.util.result;

import lombok.Getter;
import lombok.Setter;

public class requestParams<T> {
    @Setter
    @Getter
    private head head;

    @Setter
    @Getter
    private T  data;

    public class head{
        @Getter
        @Setter
        private String transcode;
        @Getter
        @Setter
        private String type;
    }

}
