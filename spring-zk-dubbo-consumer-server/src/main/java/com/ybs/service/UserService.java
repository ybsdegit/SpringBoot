package com.ybs.service;

import com.ybs.service.TicketService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

/**
 * UserService
 *
 * @author Paulson
 * @date 2020/3/4 1:07
 */

@Service  // 放倒容器中
public class UserService {
    // 想拿到 provider 提供的票，要去注册中心拿到服务
    @Reference //远程引用指定的服务，他会按照全类名进行匹配，看谁给注册中心注册了这个全类名
    TicketService ticketService;
    public void buyTicket(){
        String ticket = ticketService.getTicket();
        System.out.println("在注册中心拿到 =》"+ ticket);
    }
}
