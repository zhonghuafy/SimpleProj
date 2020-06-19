package org.fe.ek.test.web.socket;

import org.fe.ek.test.web.socket.wsocket.SendThread;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

/**
 * @program: Test-Project
 * @description: MsgSender
 * @author: Wang Zhenhua
 * @create: 2020-06-15
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2020-06-15
 **/
public class MsgSender implements ApplicationRunner {
    /**
     * Callback used to run the bean.
     *
     * @param args incoming application arguments
     * @throws Exception on error
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        SendThread sender = new SendThread();
        sender.run();
    }
}
