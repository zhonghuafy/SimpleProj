package org.fe.ek.test.common.util;

import org.springframework.cglib.beans.BeanMap;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: usercenter-portrait
 * @description: BeanMapUtils
 * @author: Wang Zhenhua
 * @create: 2020-04-16
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2020-04-16
 **/
public class BeanMapUtils {

    /**
     * 对象转map
     * @param t
     * @param <T>
     * @return
     */
    public static <T> Map<String, Object> beanToMap(T t) {
        Map<String, Object> map = new HashMap<>();
        if (t != null) {
            BeanMap beanMap = BeanMap.create(t);
            for (Object key : beanMap.keySet()) {
                if (beanMap.get(key) != null) {
                    map.put(key + "", beanMap.get(key));
                }
            }
        }
        return map;
    }
}
