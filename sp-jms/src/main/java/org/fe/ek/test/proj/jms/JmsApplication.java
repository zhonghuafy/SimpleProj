package org.fe.ek.test.proj.jms;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @program: SimpleProj
 * @description: JmsApplication
 * @author: Wang Zhenhua
 * @create: 2020-02-07
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2020-02-07
 **/
@SpringBootApplication
@Slf4j
public class JmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(JmsApplication.class,args);
        log.info("JmsApplication started successfully");
    }
}
