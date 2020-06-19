package org.fe.ek.test.web.socket.cache;

import javax.websocket.Session;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: Test-Project
 * @description: SessionCache
 * @author: Wang Zhenhua
 * @create: 2020-06-15
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2020-06-15
 **/
public class SessionCache {

    private static List<Session> sessionList = new ArrayList<>();

    public synchronized static void put(Session session) {
        sessionList.add(session);
    }

    public synchronized static void remove(Session session) {
        sessionList.remove(session);
    }

    public synchronized static List<Session> getAll() {
        return sessionList;
    }

    public synchronized static boolean isEmpty() {
        return sessionList.isEmpty();
    }
}
