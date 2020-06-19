package org.fe.ek.test.web.socket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @program: Test-Project
 * @description: LCApplication
 * @author: Wang Zhenhua
 * @create: 2020-06-12
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2020-06-12
 **/
@SpringBootApplication
@Slf4j
public class LCApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(LCApplication.class);
        app.run(args);
        log.info("[LCApplication] started successfully.");
    }

    @Bean
    public MsgSender msgSender() {
        return new MsgSender();
    }
}
