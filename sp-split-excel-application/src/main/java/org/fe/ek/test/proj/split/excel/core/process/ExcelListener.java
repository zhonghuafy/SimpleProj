package org.fe.ek.test.proj.split.excel.core.process;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.extern.slf4j.Slf4j;
import org.fe.ek.test.proj.split.excel.config.AppConfig;
import org.fe.ek.test.proj.split.excel.data.DataHeadMap;
import org.fe.ek.test.proj.split.excel.dto.DataRowDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @program: bigdata-excel-split-application
 * @description: ExcelListener
 * @author: Wang Zhenhua
 * @create: 2023-02-13
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2023-02-13
 **/
@Component
@Slf4j
public class ExcelListener extends AnalysisEventListener<DataRowDto> {

    @Autowired
    private AppConfig appConfig;

    @Autowired
    private ExportDataService exportDataService;

    private List<DataRowDto> list;

    public ExcelListener() {
        list = new ArrayList<>();
    }

    /**
     * When analysis one row trigger invoke function.
     *
     * @param dataRowDto    one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param analysisContext analysis context
     */
    @Override
    public void invoke(DataRowDto dataRowDto, AnalysisContext analysisContext) {
        if (dataRowDto != null) {
            if (!StringUtils.hasLength(dataRowDto.getCityName())) {
                dataRowDto.setCityName(appConfig.getEmptyNamePath());
            }
            list.add(dataRowDto);
        }
        if (list.size() >= appConfig.getBatchSize()) {
            exportDataService.exportExcel(list);
            list.clear();
        }
    }

    /**
     * Returns the header as a map.Override the current method to receive header data.
     *
     * @param headMap
     * @param context
     */
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        log.info("表头：{}", headMap);
        DataHeadMap.headMap = headMap;
    }

    /**
     * if have something to do after all analysis
     *
     * @param analysisContext
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.info("读取完成");
        exportDataService.exportExcel(list);
    }
}
