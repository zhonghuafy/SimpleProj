package org.fe.ek.test.proj.stucture.util;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.fe.ek.test.common.exp.CmErrCode;
import org.fe.ek.test.common.po.ResultPO;

/**
 * @program: SimpleProj
 * @description: BlockHandler
 * @author: Wang Zhenhua
 * @create: 2022-11-21
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2022-11-21
 **/
public class BlockHandler {

    /**
     * 限流
     * @param bEx
     * @return
     */
    public static ResultPO handleBlock(BlockException bEx) {
        return new ResultPO(CmErrCode.E100006);
    }
}
