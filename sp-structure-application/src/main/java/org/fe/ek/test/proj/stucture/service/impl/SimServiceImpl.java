package org.fe.ek.test.proj.stucture.service.impl;

import org.fe.ek.test.proj.stucture.service.ISimService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

/**
 * @program: SimpleProj
 * @description: SimService
 * @author: Wang Zhenhua
 * @create: 2022-11-21
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2022-11-21
 **/
@Service
@RefreshScope
public class SimServiceImpl implements ISimService {

    /**
     * nacos string config value
     */
    @Value("${conf.nac.str.examp:}")
    private String nacosExamp;

    @Value("${conf.nac.integer.ivalue:0}")
    private int iValue;

    /**
     * get config from nacos
     *
     * @return
     */
    @Override
    public String getConfig() {
//        if (true)
//            return String.valueOf(iValue);
        return nacosExamp;
    }
}
