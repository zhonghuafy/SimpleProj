package org.fe.ek.test.proj.ehcache;

import lombok.extern.slf4j.Slf4j;
import org.fe.ek.test.proj.ehcache.cache.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

/**
 * @program: TestProj
 * @description: 退出时执行
 * @author: Wang Zhenhua
 * @create: 2020-01-21
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2020-01-21
 **/
@Component
@Slf4j
public class ExitInfo {

    @Autowired
    private BoPortraitCache cache;

//    @Autowired
//    private BoPortraitSCache sCache;

    @PreDestroy
    public void printStatistic() {
        log.info("Total Produced: {}", Counter.getProduct());
        log.info("Total Consumed: {}", Counter.getConsumed());
        log.info("Put Fail Number: {}", Counter.getPutFail());
        log.info("Get Fail number: {}", Counter.getGetFail());
        printSize();
    }

    private void printSize() {
        log.info("cache size: {}", cache.size());
//        log.info("sCache size: {}",sCache.size());
    }
}
