package org.fe.ek.test.proj.split.excel.core.process;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import lombok.extern.slf4j.Slf4j;
import org.fe.ek.test.proj.split.excel.config.AppConfig;
import org.fe.ek.test.proj.split.excel.data.DataCitySet;
import org.fe.ek.test.proj.split.excel.dto.DataRowDto;
import org.fe.ek.test.proj.split.excel.util.FilePathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @program: bigdata-excel-split-application
 * @description: ExportExcelService
 * @author: Wang Zhenhua
 * @create: 2023-02-14
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2023-02-14
 **/
@Service
@Slf4j
public class ExportExcelService {

    @Autowired
    private AppConfig appConfig;

    public void writeExcel(String city, List<DataRowDto> cityList) {
        String fileName = getExcelFileName(city);
        ExcelWriter excelWriter = getExcelWriter(city, fileName);
        excelWriter.write(cityList, EasyExcel.writerSheet(city).head(DataRowDto.class).build());
    }

    private ExcelWriter getExcelWriter(String city, String fileName) {
        if (DataCitySet.exist(city)) {
            return DataCitySet.getWriter(city);
        }
        ExcelWriter excelWriter = EasyExcel.write(fileName, DataRowDto.class).build();
        DataCitySet.addCity(city, excelWriter);
        return excelWriter;
    }

    /**
     * get excel file path and name
     * @param city
     * @return
     */
    private String getExcelFileName(String city) {
        return FilePathUtil.getOutPath(appConfig.getInputFile(), appConfig.getOutputSubPath())
                + (StringUtils.hasLength(city) ? city : appConfig.getEmptyNamePath())
                + appConfig.getExcelExtend();
    }

}
