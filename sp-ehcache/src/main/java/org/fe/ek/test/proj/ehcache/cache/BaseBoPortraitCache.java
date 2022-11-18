package org.fe.ek.test.proj.ehcache.cache;

import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import org.fe.ek.test.common.exp.CmErrCode;
import org.fe.ek.test.common.exp.CmException;
import org.fe.ek.test.model.model.BoPortraitModel;
import org.springframework.util.StringUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * @program: TestProj
 * @description: ehcache缓存基类
 * @author: Wang Zhenhua
 * @create: 2020-01-22
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2020-01-22
 **/
@Slf4j
public abstract class BaseBoPortraitCache {

    protected static final String BO_KEY = "BO_PORTRAIT_KEY";
    protected static final String BO_DATA_KEY_PREFIX = "BO_PORTRAIT_DATA_KEY_";

    /**
     * 初始化缓存
     */
    public abstract void initCache();

    /**
     * 将数据保存到缓存
     * 数据包单独保存，取固定前缀+数据包hash做key；所有的key统一保存
     * @param bo
     */
    public void putBo(BoPortraitModel bo) {
        Cache cache = getCache();
        String datakey = null;
        try {
            cache.acquireWriteLockOnKey(BO_KEY);
            Element element = cache.get(BO_KEY);
            if (element == null) {
                element = new Element(BO_KEY, new LinkedList<String>());
            }
            List<Integer> data = (List<Integer>) element.getObjectValue();
            int dataHash = boHash(bo);
            data.add(dataHash);
//            cache.put(element);
            datakey = BO_DATA_KEY_PREFIX + dataHash;
            cache.acquireWriteLockOnKey(datakey);
            cache.put(new Element(datakey, bo));
            cache.put(new Element(BO_KEY,data));
            cache.flush();
            log.info("[BoPortraitCache].[put] ##### put bo with id {}, key {}",bo.getId(),datakey);
            log.info("[BoPortraitCache].[put] ##### cache size {} data size {}", cache.getSize(), data.size());
        }catch (Exception ex){
            Counter.increasePutFail();

            log.error("error to put bo, {}",ex.getMessage());
            ex.printStackTrace();
        }finally {
            cache.releaseWriteLockOnKey(BO_KEY);
            if (!StringUtils.isEmpty(datakey)) {
                cache.releaseWriteLockOnKey(datakey);
            }
        }
    }

    /**
     * 从缓存获取第一个数据并从缓存删除该数据
     * @return
     */
    public BoPortraitModel getFirstBoAndRemove() {
        Cache cache = getCache();
        String datakey = null;
        try {
            cache.acquireWriteLockOnKey(BO_KEY);
            Element element = cache.get(BO_KEY);
            if (element == null) {
                return null;
            }
            List<Integer> data = (List<Integer>) element.getObjectValue();
            if (data.isEmpty()) {
                log.info("[BoPortraitCache].[remove] ##### cache empty");
                return null;
            }
            Integer dataHash = data.remove(0);
            datakey = BO_DATA_KEY_PREFIX + dataHash;
            cache.acquireWriteLockOnKey(datakey);
            Element dataElement = cache.get(datakey);
            if (dataElement == null) {
                throw new CmException(CmErrCode.E100000,"dataElement is null for key " + datakey);
            }
            BoPortraitModel bo = (BoPortraitModel) dataElement.getObjectValue();
            cache.remove(BO_DATA_KEY_PREFIX + dataHash);
            log.info("[BoPortraitCache].[remove] ##### cache removed {}",bo.getId());
            log.info("[BoPortraitCache].[remove] ##### cache size {} data size {}", cache.getSize(), data.size());
            return bo;
        }catch (Exception ex){
            Counter.increaseGetFail();

            log.error("error to get bo, {}",ex.getMessage());
            ex.printStackTrace();
        }finally {
            cache.releaseWriteLockOnKey(BO_KEY);
            if (!StringUtils.isEmpty(datakey)) {
                cache.releaseWriteLockOnKey(datakey);
            }
        }
        return null;
    }

    /**
     * 获取指定序号的缓存
     * @return
     */
    protected abstract Cache getCache();

    protected int boHash(BoPortraitModel bo) {
        String json = JSONObject.toJSONString(bo);
        return json.hashCode();
    }
}
