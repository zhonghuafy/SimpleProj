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
 * @description: cache for BoPortraitModel ehcache
 * @author: Wang Zhenhua
 * @create: 2020-01-21
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2020-01-21
 **/
@Service
@Slf4j
public class BoPortraitCache extends BaseBoPortraitCache {

    private static final String CACHE_NAME = "ORG.FE.EK.TEST.PROJ.BOPTCACHE";

    @Override
    public void initCache() {
        EhcacheUtil.initCache(CACHE_NAME);
        log.info("[BoPortraitCache] cache initialized successfully");
    }

    public int size() {
        Cache cache = getCache();
        try {
            cache.acquireReadLockOnKey(BO_KEY);
            Element element = cache.get(BO_KEY);
            if (element == null){
                return 0;
            }
            Object obj = element.getObjectValue();
            if (obj == null) {
                return 0;
            }
            return ((List)obj) .size();
        }catch (Exception ex){
            //do nothing
            ex.printStackTrace();
        }finally {
            cache.releaseReadLockOnKey(BO_KEY);
        }
        return 0;
    }

    @Override
    protected Cache getCache() {
        Cache cache = EhcacheUtil.getCache(CACHE_NAME);
        if (cache == null) {
            throw new CmException(CmErrCode.E100000,"cache is null");
        }
        return cache;
    }
}
