package org.fe.ek.test.proj.stucture.feign;

import lombok.extern.slf4j.Slf4j;
import org.fe.ek.test.common.exp.CmErrCode;
import org.fe.ek.test.common.po.ResultPO;
import org.springframework.stereotype.Component;

/**
 * @program: SimpleProj
 * @description: ServbFeignClientImpl<br>
 *      ServbFeignClient feign调用的接口熔断实现方式一
 * @author: Wang Zhenhua
 * @create: 2022-11-22
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2022-11-22
 **/
@Component
@Slf4j
public class ServbFeignClientImpl implements ServbFeignClient {

    @Override
    public ResultPO getBserResp() {
        log.error("ServbFeignClient 接口开启熔断");
        return new ResultPO(CmErrCode.E100007);
    }
}
