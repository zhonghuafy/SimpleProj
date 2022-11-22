package org.fe.ek.test.proj.stucture.util.sentinel;

import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.fe.ek.test.common.exp.CmErrCode;
import org.fe.ek.test.common.po.ResultPO;

/**
 * @program: SimpleProj
 * @description: FallbackHandler
 * @author: Wang Zhenhua
 * @create: 2022-11-22
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2022-11-22
 **/
@Slf4j
public class FallbackHandler {

    /**
     * sentinel default fallback
     * @param tr
     * @return
     */
    public static ResultPO sentinelFallback(Throwable tr) {
        try {
            DegradeException dEx = (DegradeException) tr;
            log.info("接口熔断 ruleLimitApp: {} rule: {}", dEx.getRuleLimitApp(), JSON.toJSONString(dEx.getRule()));
        } catch (Exception ignore) {}
        return new ResultPO(CmErrCode.E100007);
    }

}
