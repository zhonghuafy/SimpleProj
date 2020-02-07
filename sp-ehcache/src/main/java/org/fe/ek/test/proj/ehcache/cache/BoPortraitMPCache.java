package org.fe.ek.test.proj.ehcache.cache;

import lombok.extern.slf4j.Slf4j;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import org.fe.ek.test.common.exp.CmErrCode;
import org.fe.ek.test.common.exp.CmException;
import org.fe.ek.test.model.model.BoPortraitModel;
import org.fe.ek.test.proj.ehcache.util.EhcacheUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: SimpleProj
 * @description: 单缓存多链表的缓存
 * @author: Wang Zhenhua
 * @create: 2020-02-04
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2020-02-04
 **/
@Deprecated
@Service
@Slf4j
public class BoPortraitMPCache {

    private static final String CACHE_NAME = "ORG.FE.EK.TEST.PROJ.BOMPPTCACHE";

    private static final String BO_KEY_PREFIX = "BO_PORTRAIT_KEY";

    private static final int MAX_THRESHOLD = 100;

    private static final int MAX_LIST_NUM = 300000;

    private static final int MAX_PUT_FAIL_NUM = 3;

    private static AtomicInteger LIST_NUM = new AtomicInteger();

    public void initCache() {
        EhcacheUtil.initCache(CACHE_NAME);
        initFirstCacheList();
        log.info("[BoPortraitMPCache] cache initialized successfully");
    }

    public void putBo(BoPortraitModel bo, int current) {
        if (current++ > MAX_PUT_FAIL_NUM) {
            log.error("@@@@@@@@ error to put bo. recurve reached {} times", MAX_PUT_FAIL_NUM);
            return;
        }
        Cache cache = getCache();
        boolean needNewList = true;
        for (int i = 0; i < getListNum(); i++) {
            String key = BO_KEY_PREFIX + i;
            try {
                cache.acquireWriteLockOnKey(key);
                Element element = cache.get(key);
                if (element == null) {
                    element = new Element(key, new LinkedList<BoPortraitModel>());
                }
                List<BoPortraitModel> data = (List<BoPortraitModel>) element.getObjectValue();
                if (data.size() >= MAX_THRESHOLD) {
                    continue;
                }
                data.add(bo);
                cache.put(new Element(key,data));
                cache.flush();
                needNewList = false;
                log.info("[BoPortraitMPCache].[put] ##### cache name {}, size {} data size {}",i, cache.getSize(), data.size());
            }catch (Exception ex){
                Counter.increasePutFail();
                log.error("[BoPortraitMPCache].[put] error to put bo, {}",ex.getMessage());
                ex.printStackTrace();
            }finally {
                cache.releaseWriteLockOnKey(key);
            }
        }
        if (needNewList) {
            if (getListNum() > MAX_LIST_NUM) {
                log.error("[BoPortraitMPCache].[put] 达到容量上限，数据被丢弃");
                return;
            }
            initNewCacheList(getListNum());
            putBo(bo, current);
        }
    }

    public BoPortraitModel getFirstAndRemove() {
        Cache cache = getCache();
        for (int i = 0; i < getListNum(); i++) {
            String key = BO_KEY_PREFIX + i;
            try {
                cache.acquireWriteLockOnKey(key);
                Element element = cache.get(key);
                if (element == null) {
                    continue;
                }
                List<BoPortraitModel> data = (List<BoPortraitModel>) element.getObjectValue();
                if (!CollectionUtils.isEmpty(data)) {
                    BoPortraitModel bo = data.remove(0);
                    log.info("[BoPortraitMPCache].[remove] ##### cache removed {}",bo.getId());
                    return bo;
                }
            }catch (Exception ex){
                Counter.increaseGetFail();

                log.error("error to get bo, {}",ex.getMessage());
                ex.printStackTrace();
            }finally {
                cache.releaseWriteLockOnKey(key);
            }
        }
        return null;
    }

    /**
     * 获取链表的数量
     * @return
     */
    public int getListNum() {
        return LIST_NUM.get();
    }

    public int[] getSize() {
        int[] sizearr = new int[getListNum() + 1];
        sizearr[sizearr.length - 1] = getListNum();
        for (int i = 0; i < getListNum(); i++) {
            String key = BO_KEY_PREFIX + i;
            Cache cache = getCache();
            try {
                cache.acquireReadLockOnKey(key);
                Element element = cache.get(key);
                if (element == null) {
                    sizearr[i] = 0;
                }else {
                    sizearr[i] = ((List) element.getObjectValue()).size();
                }
            }finally {
                cache.releaseReadLockOnKey(key);
            }
        }
        return sizearr;
    }

    private Cache getCache() {
        Cache cache = EhcacheUtil.getCache(CACHE_NAME);
        if (cache == null) {
            throw new CmException(CmErrCode.E100000,"cache is null");
        }
        return cache;
    }

    private void initFirstCacheList() {
        initNewCacheList(0);
    }

    /**
     * 初始化一个新的数据缓存区，从0开始
     * @param index
     */
    private void initNewCacheList(int index) {
        if (getListNum() > index) {
            throw new CmException(CmErrCode.E100000,"已存在序号为 " + index + " 的列表");
        }
        Cache cache = getCache();
        String key = BO_KEY_PREFIX + index;
        try {
            cache.acquireWriteLockOnKey(key);
            cache.put(new Element(key,new LinkedList<>()));
            cache.flush();
            LIST_NUM.getAndIncrement();
            log.info("[BoPortraitMPCache].[initNewCacheList] ##### initialized {} ", index);
        }catch (Exception ex){
            log.error("[BoPortraitMPCache].[initNewCacheList] error to get cache size");
            ex.printStackTrace();
        }finally {
            cache.releaseWriteLockOnKey(key);
        }
    }

}
