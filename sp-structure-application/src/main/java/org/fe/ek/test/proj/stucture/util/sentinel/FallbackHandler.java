package org.fe.ek.test.proj.stucture.util.sentinel;

import lombok.extern.slf4j.Slf4j;
import org.fe.ek.test.common.exp.CmErrCode;
import org.fe.ek.test.common.po.ResultPO;

/**
 * @program: SimpleProj
 * @description: DefaultFallbackService
 * @author: Wang Zhenhua
 * @create: 2022-11-22
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2022-11-22
 **/
@Slf4j
public class FallbackHandler {

    public static ResultPO sentinelFallback(Throwable tr) {
        log.info("fallback", tr);
        return new ResultPO(CmErrCode.E100007);
    }

}
