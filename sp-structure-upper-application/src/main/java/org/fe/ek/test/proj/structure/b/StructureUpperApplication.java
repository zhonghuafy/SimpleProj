package org.fe.ek.test.proj.structure.b;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @program: SimpleProj
 * @description: 模拟另一个服务 <br>
 *          与sp-structure-application形成AB服务，一个上游一个下游或服务调用同步
 * @author: Wang Zhenhua
 * @create: 2022-11-18
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2022-11-18
 **/
@SpringBootApplication
@EnableDiscoveryClient
@Slf4j
public class StructureUpperApplication {

    public static void main(String[] args) {
        SpringApplication.run(StructureUpperApplication.class, args);
        log.info("StructureUpperApplication started successfully");
    }
}
