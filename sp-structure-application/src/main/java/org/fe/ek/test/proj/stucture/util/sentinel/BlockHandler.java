package org.fe.ek.test.proj.stucture.util.sentinel;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.fe.ek.test.common.exp.CmErrCode;
import org.fe.ek.test.common.po.ResultPO;

/**
 * @program: SimpleProj
 * @description: BlockHandler
 * @author: Wang Zhenhua
 * @create: 2022-11-21
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2022-11-21
 **/
@Slf4j
public class BlockHandler {

    /**
     * 限流
     * @param bEx
     * @return
     */
    public static ResultPO handleBlock(BlockException bEx) {
        log.info("接口限流 ruleLimitApp: {} rule: {}", bEx.getRuleLimitApp(), JSON.toJSONString(bEx.getRule()));
        return new ResultPO(CmErrCode.E100006);
    }
}
