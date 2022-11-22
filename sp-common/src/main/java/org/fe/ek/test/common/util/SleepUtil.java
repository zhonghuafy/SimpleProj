package org.fe.ek.test.common.util;

/**
 * @program: SimpleProj
 * @description: SleepUtil
 * @author: Wang Zhenhua
 * @create: 2022-11-22
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2022-11-22
 **/
public class SleepUtil {

    private static final long DEFAULT_MILLIS = 10L;

    /**
     * thread sleep 10 milli-seconds
     */
    public static void sleep() {
        sleep(DEFAULT_MILLIS);
    }

    /**
     * thread sleep with try catch
     * @param millis
     */
    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ignore) {}
    }
}
