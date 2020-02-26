package org.fe.ek.test.proj.jms;

import lombok.extern.slf4j.Slf4j;
import org.fe.ek.test.proj.jms.productor.Producer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * @program: SimpleProj
 * @description: JmsStartup
 * @author: Wang Zhenhua
 * @create: 2020-02-26
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2020-02-26
 **/
@Slf4j
public class JmsStartup implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        ApplicationContext ac = contextRefreshedEvent.getApplicationContext();
        startProducer(ac);
    }

    private void startProducer(ApplicationContext ac) {
        Producer producer = ac.getBean(Producer.class);
        Thread thread = new Thread(producer);
        thread.start();
        log.info("start producer ");
    }
}
