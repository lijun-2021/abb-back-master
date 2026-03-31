package com.youlai.boot.system.model.dto;

import cn.idev.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * Token导入对象
 */
@Data
public class TokenImportDTO {

    @ExcelProperty(value = "token名称")
    private String tokenName;

    @ExcelProperty(value = "tokenID")
    private String tokenCode;

    @ExcelProperty(value = "门板预装时间_预")
    private Integer doorPreinstallTime;

    @ExcelProperty(value = "门板时间_预")
    private Integer doorTime;

    @ExcelProperty(value = "网格预装时间_预")
    private Integer gridPreinstallTime;

    @ExcelProperty(value = "网格时间_预")
    private Integer gridTime;

    @ExcelProperty(value = "顶板时间_预")
    private Integer topPlateTime;

    @ExcelProperty(value = "底板时间_预")
    private Integer bottomPlateTime;

    @ExcelProperty(value = "壳体组装_预")
    private Integer shellAssemblyTime;

    @ExcelProperty(value = "总成时间_预")
    private Integer totalAssemblyTime;
}
