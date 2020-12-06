package com.ybs.common;

import com.ybs.service.excute.ExcuteGet;
import com.ybs.service.excute.ExcuteHttp;
import com.ybs.service.excute.ExcutePost;

/**
 * @ClassName: ExcuteMethodEnum
 * @Author Paulson
 * @Date 2020/12/6
 * @Description:
 */
public enum ExcuteMethodEnum {

    GET("GET", new ExcuteGet()), POST("POST", new ExcutePost());

    String method;
    ExcuteHttp excuteHttp;

    ExcuteMethodEnum(String method, ExcuteHttp excuteHttp) {
        this.method = method;
        this.excuteHttp = excuteHttp;
    }

    public static ExcuteHttp match(String methodName) {
        ExcuteMethodEnum[] methodEnums = ExcuteMethodEnum.values();
        for (ExcuteMethodEnum methodEnum : methodEnums) {
            String name = methodEnum.method;
            if (name != null && name.equals(methodName)) {
                return methodEnum.excuteHttp;
            }
        }
        return null;
    }
}
