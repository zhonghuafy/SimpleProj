package org.fe.ek.test.proj.stucture.feign;

import lombok.extern.slf4j.Slf4j;
import org.fe.ek.test.common.exp.CmErrCode;
import org.fe.ek.test.common.po.ResultPO;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @program: SimpleProj
 * @description: ServFeignClientFailFactory<br>
 *     ServbFeignClient feign调用的接口熔断实现方式二<br>
 *         实现FallbackFactory，可以处理异常
 * @author: Wang Zhenhua
 * @create: 2022-11-22
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2022-11-22
 **/
@Component
@Slf4j
public class ServFeignClientFailFactory implements FallbackFactory<ServbFeignClient> {

    @Override
    public ServbFeignClient create(Throwable cause) {
        return () -> {
            log.error("ServbFeignClient 接口开启熔断 ", cause);
            return new ResultPO(CmErrCode.E100007);
        };
    }
}
