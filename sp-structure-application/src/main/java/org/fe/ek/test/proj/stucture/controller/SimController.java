package org.fe.ek.test.proj.stucture.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import lombok.extern.slf4j.Slf4j;
import org.fe.ek.test.common.po.ResultPO;
import org.fe.ek.test.proj.stucture.feign.ServbFeignClient;
import org.fe.ek.test.proj.stucture.service.ISimService;
import org.fe.ek.test.proj.stucture.util.BlockHandler;
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

    @PostMapping("/strConf")
    @SentinelResource(value = "strConf", blockHandlerClass = BlockHandler.class, blockHandler = "handleBlock")
    public ResultPO getStrConfig() {
        String nacosExamp = simService.getConfig();
        log.info("nacos config: {}", nacosExamp);
        return ResultPO.success(nacosExamp);
    }

    @PostMapping("/upperStr")
    public ResultPO getBservStr() {
        return servbFeignClient.getBserResp();
    }

}
