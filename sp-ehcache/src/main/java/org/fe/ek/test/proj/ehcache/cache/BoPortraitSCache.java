package org.fe.ek.test.proj.ehcache.cache;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import org.fe.ek.test.common.exp.CmErrCode;
import org.fe.ek.test.common.exp.CmException;
import org.fe.ek.test.model.model.BoPortraitModel;
import org.fe.ek.test.proj.ehcache.util.EhcacheUtil;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class BoPortraitSCache {

    private static final String CACHE_NAME = "ORG.FE.EK.TEST.PROJ.BOSPTCACHE";

    private static final String BO_KEY_PREFIX = "BO_PORTRAIT_KEY";

    public void initCache() {
        EhcacheUtil.initCache(CACHE_NAME);
        log.info("[BoPortraitCache] cache initialized successfully");
    }

    public void putBo(BoPortraitModel bo) {
        Cache cache = getCache();
        String key = BO_KEY_PREFIX + getDataHash(bo);
        try {
            cache.acquireWriteLockOnKey(key);
            cache.put(new Element(key,bo));
            cache.flush();
            log.info("[BoPortraitCache].[put] ##### put bo with id {}",bo.getId());
            log.info("[BoPortraitCache].[put] ##### cache name {}, size {} ",CACHE_NAME, cache.getSize());
        }catch (Exception ex){
            Counter.increasePutFail();

            log.error("error to put bo, {}",ex.getMessage());
            ex.printStackTrace();
        }finally {
            cache.releaseWriteLockOnKey(key);
        }
    }

    public int size() {
        Cache cache = getCache();
        try {
            return cache.getSize();
        }catch (Exception ex){
            //do nothing
            ex.printStackTrace();
        }finally {
        }
        return 0;
    }

    public int getDataHash(BoPortraitModel bo) {
        String json = JSONObject.toJSONString(bo);
        return json.hashCode();
    }

    protected Cache getCache() {
        Cache cache = EhcacheUtil.getCache(CACHE_NAME);
        if (cache == null) {
            throw new CmException(CmErrCode.E100000,"cache is null");
        }
        return cache;
    }
}
