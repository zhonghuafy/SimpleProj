package org.fe.ek.test.proj.split.excel.core;

import com.alibaba.excel.EasyExcel;
import lombok.extern.slf4j.Slf4j;
import org.fe.ek.test.proj.split.excel.config.AppConfig;
import org.fe.ek.test.proj.split.excel.core.process.AfterExportService;
import org.fe.ek.test.proj.split.excel.core.process.ExcelListener;
import org.fe.ek.test.proj.split.excel.dto.DataRowDto;
import org.fe.ek.test.proj.split.excel.util.FilePathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.File;

/**
 * @program: bigdata-excel-split-application
 * @description: SplitEntrance
 * @author: Wang Zhenhua
 * @create: 2023-02-13
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2023-02-13
 **/
@Component
@Slf4j
public class SplitEntrance {

    @Autowired
    private AppConfig appConfig;

    @Autowired
    private ExcelListener excelListener;

    @Autowired
    private AfterExportService afterExportService;

    public void startProcess() {
        check();
        try {
            String inputPath = appConfig.getInputFile();
            File inputFile = new File(inputPath);
            if (!inputFile.exists()) {
                log.error("文件 {} 不存在", inputPath);
            }
            createFolder();
            process(inputFile);
        } catch (Exception ex) {
            log.error("split error {}", ex.getMessage(), ex);
        } finally {
            afterExportService.closeAllExcel();
            log.info("closed all file");
        }
    }

    private void check() {
        if (!StringUtils.hasLength(appConfig.getInputFile())) {
            log.error("未指定excel文件");
            System.exit(-1);
        }
    }

    /**
     * read data and write to separate excel
     * @param inputExcel
     */
    private void process(File inputExcel) {
        EasyExcel.read(inputExcel, DataRowDto.class, excelListener).sheet().doRead();
        log.info("read excel finished");
    }

    /**
     * create folder
     */
    private void createFolder() {
        String outPath = FilePathUtil.getOutPath(appConfig.getInputFile(), appConfig.getOutputSubPath());
        File resultFilePath = new File(outPath);
        if (!resultFilePath.exists()) {
            resultFilePath.mkdirs();
        }
    }
}
