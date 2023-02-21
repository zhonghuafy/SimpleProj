package org.fe.ek.test.proj.split.excel.util;

import java.io.File;

/**
 * @program: bigdata-excel-split-application
 * @description: FilePathUtil
 * @author: Wang Zhenhua
 * @create: 2023-02-14
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2023-02-14
 **/
public class FilePathUtil {

    public static String getOutPath(String inputFile, String outputSubPath) {
        File file = new File(inputFile);
        String currentPath = file.getParent();
        currentPath = currentPath.replaceAll("\\\\", "/");
        return currentPath + "/" + outputSubPath + "/";
    }
}
