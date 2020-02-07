package org.fe.ek.test.proj.ehcache.cache;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @program: TestProj
 * @description: 缓存计数器
 * @author: Wang Zhenhua
 * @create: 2020-01-21
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2020-01-21
 **/
public final class Counter {

    private static AtomicLong totalProduced = new AtomicLong();

    private static AtomicLong totalConsumed = new AtomicLong();

    private static AtomicInteger putFail = new AtomicInteger();

    private static AtomicInteger getFail = new AtomicInteger();

    private Counter() {}

    public static void increaseProduct() {
        totalProduced.getAndIncrement();
    }

    public static Long getProduct() {
        return totalProduced.get();
    }

    public static void increaseConsume() {
        totalConsumed.getAndIncrement();
    }

    public static Long getConsumed() {
        return totalConsumed.get();
    }

    public static void increasePutFail() {
        putFail.getAndIncrement();
    }

    public static Integer getPutFail() {
        return putFail.get();
    }

    public static void increaseGetFail() { getFail.getAndIncrement(); }

    public static Integer getGetFail() { return getFail.get(); }
}
