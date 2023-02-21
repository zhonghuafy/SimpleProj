package org.fe.ek.test.proj.split.excel.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @program: bigdata-excel-split-application
 * @description: AppConfig
 * @author: Wang Zhenhua
 * @create: 2023-02-14
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2023-02-14
 **/
@Configuration
@Data
public class AppConfig {

    /**
     * 输入文件
     */
    @Value("${inputFile}")
    private String inputFile;

    /**
     * 批量处理数据大小
     */
    @Value("${batchSize:5000}")
    private int batchSize;


    /**
     * 输出路径
     */
    private String outputSubPath = "result";

    /**
     * 扩展名
     */
    private String excelExtend = ".xlsx";

    /**
     * 城市名为空时的路径/文件名
     */
    private String emptyNamePath = "无";

}
