package com.youlai.boot.system.model.dto;

import cn.idev.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 当日资源状态导入对象
 */
@Data
public class ResourceStatusImportDTO {

    @ExcelProperty(value = "加工位ID")
    private String processStationId;

    @ExcelProperty(value = "加工位工人ID")
    private String processWorkerId;

    @ExcelProperty(value = "加工位状态")
    private String processStationStatus;

    @ExcelProperty(value = "立库ID")
    private String warehouseId;

    @ExcelProperty(value = "立库总空余存储数")
    private Integer warehouseTotalFree;

    @ExcelProperty(value = "立库当前空余存储数")
    private Integer warehouseCurrentFree;

    @ExcelProperty(value = "立库当前占用存储数")
    private Integer warehouseCurrentUsed;

    @ExcelProperty(value = "RGV标志位")
    private String rgvFlag;

    @ExcelProperty(value = "AGV标志位")
    private String agvFlag;

    @ExcelProperty(value = "立库机械臂标志位")
    private String robotArmFlag;
}
