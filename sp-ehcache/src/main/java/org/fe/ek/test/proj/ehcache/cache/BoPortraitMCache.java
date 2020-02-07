package org.fe.ek.test.proj.ehcache.cache;

import lombok.extern.slf4j.Slf4j;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import org.fe.ek.test.common.exp.CmErrCode;
import org.fe.ek.test.common.exp.CmException;
import org.fe.ek.test.model.model.BoPortraitModel;
import org.fe.ek.test.proj.ehcache.util.EhcacheUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: TestProj
 * @description: cache for BoPortraitModel ehcache, including multiple cache. save into different based by modulus
 * @author: Wang Zhenhua
 * @create: 2020-01-22
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2020-01-22
 **/
@Deprecated
@Service
@Slf4j
public class BoPortraitMCache {//} extends BaseBoPortraitCache {

    protected static final String BO_KEY = "BO_PORTRAIT_KEY";
    private static final String[] CACHE_NAME = {"ORG.FE.EK.TEST.PROJ.BOPTCACHE1","ORG.FE.EK.TEST.PROJ.BOPTCACHE2"};

    private BoPortraitMCache() {}

//    @Override
    public void initCache() {
        for (String cname : CACHE_NAME) {
            EhcacheUtil.initCache(cname);
        }
        log.info("[BoPortraitCache] cache initialized successfully");
    }

//    @Override
    protected int calIndex(BoPortraitModel bo) {
        return (int) (bo.getId() % 2);
    }

//    @Override
    protected Cache getCache(int index) {
        Cache cache = EhcacheUtil.getCache(CACHE_NAME[index]);
        if (cache == null) {
            throw new CmException(CmErrCode.E100000,"cache is null");
        }
        return cache;
    }

    public int[] size() {
        int[] cacheDataSize = new int[CACHE_NAME.length];
        for (int i = 0; i < CACHE_NAME.length; i++) {
            Cache cache = getCache(i);
            try {
                cache.acquireReadLockOnKey(BO_KEY);
                Element element = cache.get(BO_KEY);
                if (element == null) {
                    cacheDataSize[i] = 0;
                }else {
                    cacheDataSize[i] = element.getObjectValue() == null ? 0 : ((List) element.getObjectValue()).size();
                }
            }catch (Exception ex){
                log.error("error to get cache size");
                ex.printStackTrace();
            }finally {
                cache.releaseReadLockOnKey(BO_KEY);
            }
        }
        return cacheDataSize;
    }

}
