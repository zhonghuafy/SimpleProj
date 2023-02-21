package org.fe.ek.test.proj.split.excel.start;

import lombok.extern.slf4j.Slf4j;
import org.fe.ek.test.proj.split.excel.core.SplitEntrance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @program: bigdata-excel-split-application
 * @description: 启动任务
 * @author: Wang Zhenhua
 * @create: 2023-02-14
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2023-02-14
 **/
@Component
@Slf4j
public class StartJob implements ApplicationRunner {

    @Autowired
    private SplitEntrance splitEntrance;

    /**
     * Callback used to run the bean. <br>
     *     excel格式：
     *     owner_name	risk_level	risk_score	total_vehicle	city_name	province_name  <br>
     *
     *     功能：根据第5列 city_name 分组切分，并保存为独立excel
     *
     * @param args incoming application arguments
     * @throws Exception on error
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("start job");
        splitEntrance.startProcess();
        log.info("job finished, application exist after 5 seconds");
        Thread.sleep(5000);
        System.exit(0);
    }
}
