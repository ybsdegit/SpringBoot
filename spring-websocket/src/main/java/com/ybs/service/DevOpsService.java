package com.ybs.service;

import com.ybs.utils.ExecuteShellUtil;
import com.ybs.websocket.WebSocketServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName: DevOpsService
 * @Author Paulson
 * @Date 2020/12/5
 * @Description:
 */

@Service
@Slf4j
public class DevOpsService {

    @Autowired
    private WebSocketServer webSocketServer;

    @Async
    public void executeShellAndSendWebSocket(String taskId) throws Exception {
        ConcurrentHashMap<String, WebSocketServer> webSocketMap = webSocketServer.getWebSocketMap();
        WebSocketServer webSocketServer = webSocketMap.get(taskId);
        log.info("webSocketServer is {}, taskId is {}", webSocketServer, taskId);
        if (webSocketServer == null) {
            throw new Exception("请建立长链接!");
        }
        String cmd = "cd /home/paulson/ybs; " +
                "tail -fn300 marklog.log";
        ExecuteShellUtil.executeShellAndSendWebSocket(taskId, webSocketServer, "192.168.2.156", "root", "mima", cmd);
    }

    public void sendMessage(String taskId, String msg) {
        try {
            ConcurrentHashMap<String, WebSocketServer> map = webSocketServer.getWebSocketMap();
            WebSocketServer server = map.get(taskId);
            if (server != null) {
                server.sendMessage(msg);
            } else {
                log.warn("客户端已退出");
            }
        } catch (IOException e) {
            log.error("向客户端发送消息时出现异常，异常原因:{}", e.getMessage(), e);
        }
    }
}
