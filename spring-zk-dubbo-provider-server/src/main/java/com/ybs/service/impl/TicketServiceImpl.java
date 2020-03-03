package com.ybs.service.impl;

import com.ybs.service.TicketService;

/**
 * TicketServiceImpl
 *
 * @author Paulson
 * @date 2020/3/4 1:03
 */
public class TicketServiceImpl implements TicketService {
    @Override
    public String getTicket() {
        return "Paulson's dubbo-zk";
    }
}
