package org.fe.ek.test.proj.split.excel.core.process;

import lombok.extern.slf4j.Slf4j;
import org.fe.ek.test.proj.split.excel.dto.DataRowDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @program: bigdata-excel-split-application
 * @description: ExportData
 * @author: Wang Zhenhua
 * @create: 2023-02-13
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2023-02-13
 **/
@Component
@Slf4j
public class ExportDataService {

    @Autowired
    private ExportExcelService exportExcelService;

    /**
     * export excel
     * @param list
     */
    public void exportExcel(List<DataRowDto> list) {
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        log.info("export excel");
        Map<String, List<DataRowDto>> cityListMap = list.parallelStream().collect(Collectors.groupingBy(DataRowDto::getCityName));
        cityListMap.forEach(this::writeExcel);
    }

    /**
     * write list into excel
     * @param city
     * @param cityList
     */
    private void writeExcel(String city, List<DataRowDto> cityList) {
        if (CollectionUtils.isEmpty(cityList)) {
            return;
        }
        exportExcelService.writeExcel(city, cityList);
    }

}
