package org.fe.ek.test.proj.ehcache;

import lombok.extern.slf4j.Slf4j;
import org.fe.ek.test.proj.ehcache.cache.BoPortraitCache;
import org.fe.ek.test.proj.ehcache.cons.Consumer;
import org.fe.ek.test.proj.ehcache.prod.Productor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * @program: TestProj
 * @description: EhCacheAppStartup
 * @author: Wang Zhenhua
 * @create: 2020-01-20
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2020-01-20
 **/
@Slf4j
public class EhCacheAppStartup implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        ApplicationContext ac = contextRefreshedEvent.getApplicationContext();
        initCache(ac);
        startProducor(ac);
        startConsumer(ac);
    }

    private void initCache(ApplicationContext ac) {
        BoPortraitCache cache = ac.getBean(BoPortraitCache.class);
        cache.initCache();

//        BoPortraitSCache sCache = ac.getBean(BoPortraitSCache.class);
//        sCache.initCache();

    }

    private void startProducor(ApplicationContext ac) {
        Productor productor = ac.getBean(Productor.class);
        for (int i = 0; i < 2; i++) {
            Thread thread = new Thread(productor);
            thread.start();
            log.info("start producor {}",i);
        }
    }

    private void startConsumer(ApplicationContext ac) {
        Consumer consumer = ac.getBean(Consumer.class);
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(consumer,String.valueOf(i));
            thread.start();
            log.info("start consumer {}",i);
        }
    }
}
