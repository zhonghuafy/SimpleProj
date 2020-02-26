package org.fe.ek.test.common.util;

import java.io.File;

/**
 * @program: SimpleProj
 * @description: FontUtil
 * @author: Wang Zhenhua
 * @create: 2020-02-26
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2020-02-26
 **/
public class FontUtil {
    private final static String OS_NAME = "os.name";

    private final static String LINUX = "linux";

    private final static String DEFAULT_WIN_PATH = "C:/Windows/Fonts/";

    private final static String DEFAULT_LINUX_PATH = "/usr/share/fonts/";

    private final static String DEFAULT_SYS_PATH = "/font/";

    private final static String FONT_SIMSUN = "simsun.ttc";

    private FontUtil(){}

    /**
     * 默认使用系统自带的宋体，如果系统没有对应的宋体文件，则使用程序自带的宋体文件
     *
     * @return
     */
    public static String getDefault() {
        return getSimSun();
    }

    /**
     * 宋体
     * @return
     */
    public static String getSimSun() {
        // 宋体（对应css中的 属性 font-family: SimSun; /*宋体*/）
        String font = DEFAULT_WIN_PATH + FONT_SIMSUN;

        // 判断系统类型，加载字体文件
        java.util.Properties prop = System.getProperties();
        String osName = prop.getProperty(OS_NAME).toLowerCase();
        if (osName.contains(LINUX)) {
            font = DEFAULT_LINUX_PATH + FONT_SIMSUN;
        }
        if (!new File(font).exists()) {
            font = DEFAULT_SYS_PATH + FONT_SIMSUN;
        }
        return font;
    }
}
