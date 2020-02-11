package org.fe.ek.test.model.cache;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @program: TestProj
 * @description: BoPortraitIDCache
 * @author: Wang Zhenhua
 * @create: 2020-01-20
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2020-01-20
 **/
public class BoPortraitIDCache {

    private BoPortraitIDCache(){}

    private static AtomicLong id = new AtomicLong(1L);

    public static Long nextId() {
        return id.getAndIncrement();
    }
}
