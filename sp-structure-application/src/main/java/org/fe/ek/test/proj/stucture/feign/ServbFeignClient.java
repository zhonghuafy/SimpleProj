package org.fe.ek.test.proj.stucture.feign;

import org.fe.ek.test.common.po.ResultPO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @program: SimpleProj
 * @description: ServbFeignClient
 * @author: Wang Zhenhua
 * @create: 2022-11-21
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2022-11-21
 **/
//@FeignClient(value = "sp-structure-upper-application", fallback = ServbFeignClientImpl.class) 实现方式1
@FeignClient(value = "sp-structure-upper-application", fallbackFactory = ServFeignClientFailFactory.class)
public interface ServbFeignClient {

    @PostMapping(value = "/sp-structure-upper/bser/resp", consumes = "application/json")
//    @SentinelResource(value = "getBserResp")
    ResultPO getBserResp();
}
