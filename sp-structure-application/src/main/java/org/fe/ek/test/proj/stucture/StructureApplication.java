package org.fe.ek.test.proj.stucture;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * @program: SimpleProj
 * @description: StuApplication
 * @author: Wang Zhenhua
 * @create: 2022-11-18
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2022-11-18
 **/
@SpringBootApplication
@Slf4j
@EnableDiscoveryClient
@RefreshScope
@MapperScan("org.fe.ek.test.proj.stucture.dao.*")
public class StructureApplication {

    public static void main(String[] args) {
        SpringApplication.run(StructureApplication.class, args);
        log.info("StructureApplication started successfully");
    }
}
