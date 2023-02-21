package org.fe.ek.test.proj.split.excel.core.process;

import com.alibaba.excel.ExcelWriter;
import lombok.extern.slf4j.Slf4j;
import org.fe.ek.test.proj.split.excel.data.DataCitySet;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Map;

/**
 * @program: bigdata-excel-split-application
 * @description: AfterExportService
 * @author: Wang Zhenhua
 * @create: 2023-02-14
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2023-02-14
 **/
@Service
@Slf4j
public class AfterExportService {

    public void closeAllExcel() {
        Map<String, ExcelWriter> writerMap = DataCitySet.getAll();
        if (!ObjectUtils.isEmpty(writerMap)) {
            writerMap.forEach((k, v) -> v.finish());
        }
    }
}
