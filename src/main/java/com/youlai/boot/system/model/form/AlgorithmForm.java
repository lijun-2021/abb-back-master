package com.youlai.boot.system.model.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 算法表单对象
 */
@Schema(description = "算法表单对象")
@Data
public class AlgorithmForm {

    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "预计完工时间")
    private String estimatedFinishTime;

    @Schema(description = "调整次数")
    private Integer adjustCount;

    @Schema(description = "立体仓预警位")
    private String warehouseWarningFlag;

    @Schema(description = "壳体机器人利用率")
    private BigDecimal shellRobotUtilization;
}
