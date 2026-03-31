package com.youlai.boot.system.model.dto;

import cn.idev.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 库所导入对象
 */
@Data
public class PlaceImportDTO {

    @ExcelProperty(value = "库所名称")
    private String placeName;

    @ExcelProperty(value = "容量")
    private Integer capacity;

    @ExcelProperty(value = "前置变迁")
    private String preTransition;

    @ExcelProperty(value = "后置变迁")
    private String postTransition;

    @ExcelProperty(value = "库所类别")
    private String placeType;

    @ExcelProperty(value = "赋时库所标志位")
    private Boolean timedPlaceFlag;

    @ExcelProperty(value = "加工位ID")
    private String processStationId;

    @ExcelProperty(value = "阶段标志")
    private Integer stageFlag;

    @ExcelProperty(value = "库所颜色")
    private String placeColor;
}
