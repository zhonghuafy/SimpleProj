package org.fe.ek.test.proj.split.excel.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @program: bigdata-excel-split-application
 * @description: DataRowDto
 * @author: Wang Zhenhua
 * @create: 2023-02-13
 * @version: v1.0.0 创建文件, Wang Zhenhua, 2023-02-13
 **/
@Data
public class DataRowDto {

    @ExcelProperty(value = "owner_name", index = 0)
    private String ownerName;

    @ExcelProperty(value = "risk_level", index = 1)
    private String riskLevel;

    @ExcelProperty(value = "risk_score", index = 2)
    private String riskScore;

    @ExcelProperty(value = "total_vehicle", index = 3)
    private String totalVehicle;

    @ExcelProperty(value = "city_name", index = 4)
    private String cityName;

    @ExcelProperty(value = "province_name", index = 5)
    private String provinceName;
}
