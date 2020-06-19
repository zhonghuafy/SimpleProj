package org.fe.ek.test.web.socket.wsocket;

import lombok.extern.slf4j.Slf4j;
import org.fe.ek.test.web.socket.cache.SessionCache;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @program: Test-Project
 * @description: SocketPush
 * @author: Wang Zhenhua
 * @create: 2020-06-12
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2020-06-12
 **/
@ServerEndpoint("/wsocket/push")
@Component
@Slf4j
public class SocketPush {
    //a counter to count client number
    private static AtomicLong onlineCount = new AtomicLong();

    /**
     * 连接建立成功调用的方法
     * @param session 当前会话session
     */
    @OnOpen
    public void onOpen (@PathParam("userId")String userId, Session session){
        log.info(session.getId()+"有新链接加入，当前链接数为：" + onlineCount.incrementAndGet());
        SessionCache.put(session);
    }

    /**
     * 连接关闭
     */
    @OnClose
    public void onClose (Session session){
        log.info("有一链接关闭，当前链接数为：" + onlineCount.decrementAndGet());
        SessionCache.remove(session);
    }

    /**
     * 收到客户端消息
     * @param message 客户端发送过来的消息
     * @param session 当前会话session
     * @throws IOException
     */
    @OnMessage
    public void onMessage (String message, Session session) throws IOException {
        log.info("来终端的警情消息:" + message);
        session.getBasicRemote().sendText("Hello Client " + session.getId() + "!");
    }

    /**
     * 发生错误
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.info("wsClientMap发生错误!");
        error.printStackTrace();
    }

}
