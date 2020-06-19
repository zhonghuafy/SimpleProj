package org.fe.ek.test.web.socket.wsocket;

import lombok.extern.slf4j.Slf4j;
import org.fe.ek.test.web.socket.cache.SessionCache;

import javax.websocket.Session;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @program: Test-Project
 * @description: SendThread
 * @author: Wang Zhenhua
 * @create: 2020-06-15
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2020-06-15
 **/
@Slf4j
public class SendThread implements Runnable {

    private AtomicLong number = new AtomicLong();

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
       try {
           while (true) {
               if (SessionCache.isEmpty()) {
                   log.info("no client session");
                   Thread.sleep(5000);
                   continue;
               }
               SessionCache.getAll().forEach(this::sendMsg);
               Thread.sleep(5000);
           }
       } catch (InterruptedException iex) {
           iex.printStackTrace();
       }
    }

    /**
     * send message to given client
     * @param session
     */
    private void sendMsg(Session session) {
        try {
            session.getBasicRemote().sendText("message from server: " + number.getAndIncrement());
        }catch (Exception ex) {
            log.error("send message to session {} failed! error: {}", session.getId(), ex.getMessage());
        }
    }
}
