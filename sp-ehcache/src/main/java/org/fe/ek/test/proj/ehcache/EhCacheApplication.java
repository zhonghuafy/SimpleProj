package org.fe.ek.test.proj.ehcache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @program: TestProj
 * @description: TPApplication
 * @author: Wang Zhenhua
 * @create: 2020-01-20
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2020-01-20
 **/
@SpringBootApplication
@Slf4j
public class EhCacheApplication {

    public static void main(String[] args) {
//        SpringApplication.run(TPApplication.class, args);
        SpringApplication app = new SpringApplication(EhCacheApplication.class);
        app.addListeners(new EhCacheAppStartup());
        app.run(args);
        log.info("EhCacheApplication started successfully");
    }
}
