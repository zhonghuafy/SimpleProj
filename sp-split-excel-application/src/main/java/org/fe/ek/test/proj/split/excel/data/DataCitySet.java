package org.fe.ek.test.proj.split.excel.data;

import com.alibaba.excel.ExcelWriter;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: bigdata-excel-split-application
 * @description: DataCityDto
 * @author: Wang Zhenhua
 * @create: 2023-02-14
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2023-02-14
 **/
public final class DataCitySet {

    private DataCitySet() {}

    /**
     * 城市列表
     *  城市名, 省名
     */
    private static Map<String, ExcelWriter> citySet = new HashMap<>(1024);

    public static boolean exist(String city) {
        return citySet.containsKey(city);
    }

    public static void addCity(String city, ExcelWriter writer) {
        citySet.put(city, writer);
    }

    public static ExcelWriter getWriter(String city) {
        return citySet.get(city);
    }

    public static Map<String, ExcelWriter> getAll() {
        return citySet;
    }

}
