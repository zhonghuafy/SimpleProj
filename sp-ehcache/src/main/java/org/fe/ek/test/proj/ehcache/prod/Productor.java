package org.fe.ek.test.proj.ehcache.prod;

import lombok.extern.slf4j.Slf4j;
import org.fe.ek.test.proj.ehcache.cache.*;
import org.fe.ek.test.model.model.BoPortraitModel;
import org.fe.ek.test.proj.service.local.BoPortraitGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @program: TestProj
 * @description: 生产者，模拟产生数据包并存放到缓存
 * @author: Wang Zhenhua
 * @create: 2020-01-20
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2020-01-20
 **/
@Component
@Slf4j
public class Productor implements Runnable {

    @Autowired
    private BoPortraitCache cache;

    @Autowired
    private BoPortraitGenerator boPortraitGenerator;

    @Override
    public void run() {
        while (true) {
            try {
                BoPortraitModel data = boPortraitGenerator.portrait();
                log.info("[productor] product a request package with 1000 order info");
                cache.putBo(data);

                Counter.increaseProduct();

                Thread.sleep(50);
            }catch (Exception ex) {
                log.error("[productor] error to produce data. {}",ex.getMessage());
                ex.printStackTrace();
            }
        }
    }


}
