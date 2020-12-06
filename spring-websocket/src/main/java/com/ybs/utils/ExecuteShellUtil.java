package com.ybs.utils;

import com.jcraft.jsch.*;
import com.ybs.websocket.WebSocketServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * @author weiyuanbao
 * 远程执行 shell
 * @email weiyuanbao@didiglobal.com
 * @date 2020/5/22 下午1:17
 */

@Component
@Slf4j
public class ExecuteShellUtil {

    public WebSocketServer webSocketServer;
    private static final int TIME_OUT = 10 * 60 * 1000; //设置超时为10分钟

    /**
     * 未调用初始化方法 错误提示信息
     */
    private static final String DONOT_INIT_ERROR_MSG = "please invoke init(...) first!";

    private Session session;

    private Channel channel;

    private ChannelExec channelExec;

    private ExecuteShellUtil() {
    }

    /**
     * 获取ExecuteShellUtil类实例对象
     *
     * @return 实例
     * @date 2019/4/29 16:58
     */
    public static ExecuteShellUtil getInstance() {
        return new ExecuteShellUtil();
    }

    public static ExecuteShellUtil getInstance(WebSocketServer webSocketServer) {
        ExecuteShellUtil executeShellUtil = new ExecuteShellUtil();
        executeShellUtil.webSocketServer = webSocketServer;
        return executeShellUtil;
    }

    /**
     * 初始化
     *
     * @param ip       远程Linux地址
     * @param port     端口
     * @param username 用户名
     * @param password 密码
     * @throws JSchException JSch异常
     * @date 2019/3/15 12:41
     */
    public void init(String ip, Integer port, String username, String password) throws JSchException {
        JSch jsch = new JSch();
        jsch.getSession(username, ip, port);
        session = jsch.getSession(username, ip, port);
        session.setPassword(password);
        Properties sshConfig = new Properties();
        sshConfig.put("StrictHostKeyChecking", "no");
        session.setConfig(sshConfig);
        session.connect(60 * 1000);
        session.setTimeout(TIME_OUT);
        log.info("Session connected!");
        // 打开执行shell指令的通道
        channel = session.openChannel("exec");
        channelExec = (ChannelExec) channel;
    }

    /**
     * 执行一条命令
     */
    public String execCmd(String command) throws Exception {
        if (session == null || channel == null || channelExec == null) {
            throw new Exception(DONOT_INIT_ERROR_MSG);
        }
        log.info("execCmd command - > {}", command);
        channelExec.setCommand(command);
        channel.setInputStream(null);
        channelExec.setErrStream(System.err);
        channel.connect();
        StringBuilder sb = new StringBuilder(16);
        try (InputStream in = channelExec.getInputStream();
             InputStreamReader isr = new InputStreamReader(in, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(isr)) {
            String buffer;
            while ((buffer = reader.readLine()) != null) {
                System.out.println(buffer);
                sb.append("\n").append(buffer);
            }
            // 释放资源
            close();
            log.info("execCmd result - > {}", sb);
            return sb.toString();
        }
    }

    /**
     * 执行一条命令
     */
    public void execCmdAndSendWebSocket(String taskId, String command) throws Exception {
        if (session == null || channel == null || channelExec == null) {
            throw new Exception(DONOT_INIT_ERROR_MSG);
        }
        log.info("execCmd command - > {}", command);
        channelExec.setCommand(command);
        channel.setInputStream(null);
        channelExec.setErrStream(System.err);
        channel.connect();

        try (InputStream in = channelExec.getInputStream();
             InputStreamReader isr = new InputStreamReader(in, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(isr)) {
            String buffer;
            while ((buffer = reader.readLine()) != null) {
                // 发送websocket信息
                sendMessage(taskId, buffer);
            }
            // 释放资源
            close();
        }
    }


    /**
     * 释放资源
     *
     * @date 2019/3/15 12:47
     */
    private void close() {
        if (channelExec != null && channelExec.isConnected()) {
            channelExec.disconnect();
        }
        if (channel != null && channel.isConnected()) {
            channel.disconnect();
        }
        if (session != null && session.isConnected()) {
            session.disconnect();
        }
    }


    /**
     * 连接远程机器执行 shell
     *
     * @param ip
     * @param username
     * @param password
     * @param cmd
     * @return
     * @throws Exception
     */
    public static String executeShell(String ip, String username, String password, String cmd) throws Exception {
        ExecuteShellUtil instance = ExecuteShellUtil.getInstance();
        instance.init(ip, 22, username, password);
        return instance.execCmd(cmd);
    }

    public static void executeShellAndSendWebSocket(String taskId, WebSocketServer webSocketServer, String ip, String username, String password, String cmd) throws Exception {
        ExecuteShellUtil instance = ExecuteShellUtil.getInstance(webSocketServer);
        instance.init(ip, 22, username, password);
        instance.execCmdAndSendWebSocket(taskId, cmd);
    }

    public void sendMessage(String taskId, String msg) {
        try {
            if (webSocketServer != null) {
                webSocketServer.sendMessage(msg);
            } else {
                log.warn("客户端已退出");
            }
        } catch (IOException e) {
            log.error("向客户端发送消息时出现异常，异常原因:{}", e.getMessage(), e);
        }
    }


    public static void main(String[] args) throws Exception {
        ExecuteShellUtil instance = ExecuteShellUtil.getInstance();
        instance.init("192.168.2.156", 22, "root", "mima");
        String result = instance.execCmd("cd /home/paulson/ybs; ls");
        instance.close();
        System.out.println(result);
    }

}