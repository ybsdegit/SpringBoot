package com.ybs.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName: WebSocketServer
 * @Author Paulson
 * @Date 2020/12/5
 * @Description:
 */

@Slf4j
@ServerEndpoint("/socketserver/{taskId}")
@Component
public class WebSocketServer {
    /**
     * concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
     */
    private static ConcurrentHashMap<String, WebSocketServer> webSocketMap = new ConcurrentHashMap<>();
    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;
    /**
     * 接收taskId
     */
    private String taskId = "";

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("taskId") String taskId) {
        this.session = session;
        this.taskId = taskId;
        if (webSocketMap.containsKey(taskId)) {
            webSocketMap.remove(taskId);
            webSocketMap.put(taskId, this);
        } else {
            webSocketMap.put(taskId, this);
            log.info(webSocketMap.toString());
        }
        try {
            sendMessage("连接成功: " + taskId);
            log.info("建立连接:{}", taskId);
        } catch (IOException e) {
            log.error("socket>>" + taskId + ",网络异常!!!!!!");
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketMap.remove(taskId);
        log.info("断开连接:{}", taskId);
    }

    /**
     * 收到客户端消息后调用的方法
     * TODO 客户端交互使用，暂无用到
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        log.info("socket>>>:" + taskId + ",报文:" + message);
    }

    /**
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("用户错误:" + this.taskId + ",原因:" + error.getMessage());
        error.printStackTrace();
    }

    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        //加锁，否则会出现java.lang.IllegalStateException: The remote endpoint was in state [TEXT_FULL_WRITING] which is an invalid state for called method异常，并发使用session发送消息导致的
        synchronized (this.session) {
            this.session.getBasicRemote().sendText(message);
        }
    }


    public ConcurrentHashMap<String, WebSocketServer> getWebSocketMap() {
        return webSocketMap;
    }
}
