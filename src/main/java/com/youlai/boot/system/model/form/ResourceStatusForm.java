package com.youlai.boot.system.model.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 当日资源/状态表单对象
 */
@Schema(description = "当日资源/状态表单对象")
@Data
public class ResourceStatusForm {

    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "加工位ID")
    private String processStationId;

    @Schema(description = "加工位工人ID")
    private String processWorkerId;

    @Schema(description = "加工位状态")
    private String processStationStatus;

    @Schema(description = "立库ID")
    private String warehouseId;

    @Schema(description = "立库总空余存储数")
    private Integer warehouseTotalFree;

    @Schema(description = "立库当前空余存储数")
    private Integer warehouseCurrentFree;

    @Schema(description = "立库当前占用存储数")
    private Integer warehouseCurrentUsed;

    @Schema(description = "RGV标志位")
    private String rgvFlag;

    @Schema(description = "AGV标志位")
    private String agvFlag;

    @Schema(description = "立库机械臂标志位")
    private String robotArmFlag;
}