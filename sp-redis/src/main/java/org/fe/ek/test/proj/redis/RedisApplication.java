package org.fe.ek.test.proj.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @program: SimpleProj
 * @description: RedisApplication
 * @author: Wang Zhenhua
 * @create: 2020-03-06
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2020-03-06
 **/
@SpringBootApplication(scanBasePackages = {"org.fe.ek.test.proj.redis","org.fe.ek.test.proj.service"})
@Slf4j
public class RedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisApplication.class,args);
        log.info("RedisApplication started successfully");
    }
}
