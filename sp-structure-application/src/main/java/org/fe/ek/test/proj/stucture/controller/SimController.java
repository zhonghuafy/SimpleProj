package org.fe.ek.test.proj.stucture.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import lombok.extern.slf4j.Slf4j;
import org.fe.ek.test.common.po.ResultPO;
import org.fe.ek.test.common.util.SleepUtil;
import org.fe.ek.test.proj.stucture.feign.ServbFeignClient;
import org.fe.ek.test.proj.stucture.service.ISimService;
import org.fe.ek.test.proj.stucture.util.sentinel.BlockHandler;
import org.fe.ek.test.proj.stucture.util.sentinel.FallbackHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: pjms-construct
 * @description: SimController
 * @author: Wang Zhenhua
 * @create: 2022-11-07
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2022-11-07
 **/
@RestController
@RequestMapping("/sim")
@Slf4j
public class SimController {

    @Autowired
    private ISimService simService;

    @Autowired
    private ServbFeignClient servbFeignClient;

    /**
     * a very simple interface read config from nacos
     * @return
     */
    @PostMapping("/strConf")
    @SentinelResource(value = "strConf", blockHandlerClass = BlockHandler.class, blockHandler = "handleBlock")
    public ResultPO getStrConfig() {
        String nacosExamp = simService.getConfig();
        log.info("nacos config: {}", nacosExamp);
        return ResultPO.success(nacosExamp);
    }

    /**
     * a interface calls another service using feign with circuit breaker
     * @return
     */
    @PostMapping("/upperStr")
    public ResultPO getBservStr() {
        return servbFeignClient.getBserResp();
    }

    /**
     * test circuit breaker
     * @return
     */
    @PostMapping("/circuit")
    @SentinelResource(value = "circuit", fallbackClass = FallbackHandler.class, fallback = "sentinelFallback")
    public ResultPO circuit() {
        log.info("circuit breaker test");
        SleepUtil.sleep(500);
        return ResultPO.success("normal");
    }

}
