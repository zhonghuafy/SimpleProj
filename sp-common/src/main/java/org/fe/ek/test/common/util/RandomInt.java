package org.fe.ek.test.common.util;

import java.util.Random;

/**
 * @program: TestProj
 * @description: 产生随机整数
 * @author: Wang Zhenhua
 * @create: 2020-01-20
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2020-01-20
 **/
public class RandomInt {

    private static Random rand = new Random();

    private RandomInt() {}

    public static Integer getIntWith(int start, int end) {
        return rand.nextInt(end) + start;
    }

}
