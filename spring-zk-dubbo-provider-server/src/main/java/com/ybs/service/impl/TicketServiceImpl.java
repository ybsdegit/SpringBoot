package com.ybs.service.impl;

import com.ybs.service.TicketService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

/**
 * TicketServiceImpl
 * 服务注册于发现
 * @author Paulson
 * @date 2020/3/4 1:03
 */

@Service
@Component  // 使用了Dubbo 尽量不使用 @Service 注解
public class TicketServiceImpl implements TicketService {
    @Override
    public String getTicket() {
        return "Paulson's dubbo-zk";
    }
}
