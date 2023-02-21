package org.fe.ek.test.proj.split.excel;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @program: SimpleProj
 * @description: SplitApplication
 * @author: Wang Zhenhua
 * @create: 2023-02-21
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2023-02-21
 **/
@SpringBootApplication
@Slf4j
public class SplitApplication {

    /**
     * 启动类，支持入参：
     *  java -jar .\bigdata-excel-split-application-1.0-SNAPSHOT.jar
     *      --inputFile="f:\bigdata\owner_result.xlsx" [--batchSize=10000] <br>
     * 或将输入文件配置到yml后启动 <br>
     * inputFile 必填
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(SplitApplication.class, args);
        log.info("SplitApplication started successfully");
    }
}
