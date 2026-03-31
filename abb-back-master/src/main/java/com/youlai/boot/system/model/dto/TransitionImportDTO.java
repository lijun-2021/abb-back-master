package com.youlai.boot.system.model.dto;

import cn.idev.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 变迁数据导入对象
 */
@Data
public class TransitionImportDTO {

    @ExcelProperty(value = "变迁名称")
    private String transitionName;

    @ExcelProperty(value = "前置库所")
    private String prePlace;

    @ExcelProperty(value = "后置库所")
    private String postPlace;

    @ExcelProperty(value = "place_check标志位")
    private Boolean placeCheckFlag;

    @ExcelProperty(value = "place_lock标志位")
    private Boolean placeLockFlag;
}
