package org.fe.ek.test.proj.stucture.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.fe.ek.test.common.po.ResultPO;
import org.fe.ek.test.proj.stucture.dto.MbGroupInfoDto;
import org.fe.ek.test.proj.stucture.service.IMbGroupInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: pjms-construct
 * @description: MbGroupController
 * @author: Wang Zhenhua
 * @create: 2022-11-07
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2022-11-07
 **/
@RestController
@RequestMapping("/group")
@Slf4j
public class MbGroupController {

    @Autowired
    private IMbGroupInfoService mbGroupInfoService;

    @PostMapping("/list")
    @SentinelResource(value = "group-list", blockHandler = "exceptionHandler")//, fallback = "fallback")
    public ResultPO list() {
        List<MbGroupInfoDto> list = mbGroupInfoService.getList();
        return ResultPO.success(list);
    }

    public void exceptionHandler(BlockException ex) {
        log.error("blockHandler：", ex);
    }

}
