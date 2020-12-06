package com.ybs.service.excute;

import com.ybs.common.ExcuteMethodEnum;

/**
 * @ClassName: ExcuteHttpTest
 * @Author Paulson
 * @Date 2020/12/6
 * @Description:
 */
public class ExcuteHttpTest {

    public static void main(String[] args) {
        ExcuteHttp match = ExcuteMethodEnum.match("GET");
        if (match != null) {
            match.process();
        }
    }
}
