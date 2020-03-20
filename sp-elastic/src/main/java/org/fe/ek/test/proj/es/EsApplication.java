package org.fe.ek.test.proj.es;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @program: SimpleProj
 * @description: EsApplication
 * @author: Wang Zhenhua
 * @create: 2020-03-18
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2020-03-18
 **/
@SpringBootApplication(scanBasePackages = {"org.fe.ek.test.proj.es","org.fe.ek.test.proj.service"})
@Slf4j
public class EsApplication {

    public static void main(String[] args) {
        SpringApplication.run(EsApplication.class,args);
        log.info("EsApplication started successfully");
    }
}
