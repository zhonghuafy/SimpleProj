package org.fe.ek.test.proj.ehcache.cons;

import lombok.extern.slf4j.Slf4j;
import org.fe.ek.test.proj.ehcache.cache.BoPortraitCache;
import org.fe.ek.test.proj.ehcache.cache.Counter;
import org.fe.ek.test.model.model.BoPortraitModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @program: TestProj
 * @description: 消费者，从缓存消费数据
 * @author: Wang Zhenhua
 * @create: 2020-01-21
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2020-01-21
 **/
@Component
@Slf4j
public class Consumer implements Runnable {

    @Autowired
    private BoPortraitCache cache;

    @Override
    public void run() {
        log.info("########## i had tagnum {}", Thread.currentThread().getName());
        while (true) {
            try {
                digestBo();
                Thread.sleep(5000);
            }catch (Exception ex) {
                log.error("[consumer] error to consume data. {}",ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    private void digestBo() {
        BoPortraitModel bo = cache.getFirstBoAndRemove();
        if (bo != null) {
            log.info("[Consumer] get one bo from cache. id: {},order size: {}",bo.getId(), bo.getList().size());
            Counter.increaseConsume();
        }
    }
}
