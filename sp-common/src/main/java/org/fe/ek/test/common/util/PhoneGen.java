package org.fe.ek.test.common.util;

/**
 * @program: TestProj
 * @description: 生成手机号码工具
 * @author: Wang Zhenhua
 * @create: 2020-01-20
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2020-01-20
 **/
public class PhoneGen {

    private PhoneGen() {}

    private static String[] telFirst="134,135,136,137,138,139,150,151,152,157,158,159,130,131,132,155,156,133,153".split(",");

    public static String getTel() {
        int index=getNum(0,telFirst.length-1);
        String first=telFirst[index];
        String second=String.valueOf(getNum(1,888)+10000).substring(1);
        String third=String.valueOf(getNum(1,9100)+10000).substring(1);
        return first+second+third;
    }

    private static int getNum(int start,int end) {
        return (int)(Math.random()*(end-start+1)+start);
    }
}
