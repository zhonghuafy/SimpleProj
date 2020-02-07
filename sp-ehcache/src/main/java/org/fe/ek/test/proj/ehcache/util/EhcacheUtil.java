package org.fe.ek.test.proj.ehcache.util;

import lombok.extern.slf4j.Slf4j;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * @program: TestProj
 * @description: Ehcache Util
 * @author: Wang Zhenhua
 * @version: v1.0.0 创建文件,
 *  * @create: 2020-01-21 Wang Zhenhua, 2020-01-21
 **/
@Slf4j
public class EhcacheUtil {

    private EhcacheUtil(){}

    private static CacheManager cacheManager = null;
    private static Cache cache = null;

    static {
        initCacheManager();
    }

    private static CacheManager initCacheManager() {
        try {
            if (cacheManager == null)
                cacheManager = CacheManager.getInstance().create(EhCacheConfig.DEFUALT_CONFIG_PATH);
        } catch (Exception e) {
            log.error("can not initialize cache manager");
            e.printStackTrace();
        }
        return cacheManager;
    }

    public static Cache initCache(String cacheName) {
        checkCacheManager();
        if (null == cacheManager.getCache(cacheName)) {
            cacheManager.addCache(cacheName);
        }
        cache = cacheManager.getCache(cacheName);
        return cache;
    }

    public static Cache getCache(String cacheName) {
        checkCacheManager();
        cache = cacheManager.getCache(cacheName);
        if (null == cache) {
            cache = initCache(cacheName);
        }
        return cache;
    }

    public static void removeCache(String cacheName) {
        checkCacheManager();
        cache = cacheManager.getCache(cacheName);
        if (null != cache) {
            cacheManager.removeCache(cacheName);
        }
    }

    public static void put(String cacheName, Object key, Object value) {
        Cache cache = cacheManager.getCache(cacheName);
        if (cache != null) {
            cache.put(new Element(key, value));
            log.info("[EhcacheUtil].[put] ##### cache name {} size {}",cacheName, cache.getSize());
        }
    }

    public static Object getValue(String cacheName, String key){
        try {
//            CacheManager cacheManager = CacheManager.create();
            Cache cache = cacheManager.getCache(cacheName);
            if (cache == null) {
                cache = initCache(cacheName);
            }
            return cache.get(key).getObjectValue();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(),e);
        }
    }

    public static boolean remove(String cacheName, Object key) {
        boolean flag = false;
        Cache cache = cacheManager.getCache(cacheName);
        if (cache != null) {
            flag =  cache.remove(key);
            log.info("[EhcacheUtil].[remove] ##### cache name {} size {}",cacheName, cache.getSize());
        }
        return flag;
    }

    private static void checkCacheManager() {
        if (null == cacheManager) {
            throw new IllegalArgumentException("CacheManager not initialized");
        }
    }
}
