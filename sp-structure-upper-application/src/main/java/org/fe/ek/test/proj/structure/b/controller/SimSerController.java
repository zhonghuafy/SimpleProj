package org.fe.ek.test.proj.structure.b.controller;

import lombok.extern.slf4j.Slf4j;
import org.fe.ek.test.common.po.ResultPO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: SimpleProj
 * @description: SimSerController
 * @author: Wang Zhenhua
 * @create: 2022-11-21
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2022-11-21
 **/
@RestController
@RequestMapping("/bser")
@Slf4j
public class SimSerController {

    @PostMapping("/resp")
    public ResultPO resp() {
        log.info("upper b service");
        try {
            Thread.sleep(10);
        }catch (Exception ignore) {}
        return ResultPO.success("upper service server response");
    }
}
