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
@SpringBootApplication(scanBasePackages = {"org.fe.ek.test.proj.jms","org.fe.ek.test.proj.service"})
@Slf4j
public class JmsApplication {

    public static void main(String[] args) {
//        SpringApplication.run(JmsApplication.class,args);
        SpringApplication app = new SpringApplication(JmsApplication.class);
        app.addListeners(new JmsStartup());
        app.run(args);
        log.info("EhCacheApplication started successfully");
        log.info("JmsApplication started successfully");
    }
}
