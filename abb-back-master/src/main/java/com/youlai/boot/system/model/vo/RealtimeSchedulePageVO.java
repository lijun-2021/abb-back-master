package com.youlai.boot.system.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import lombok.Data;

/**
 * 实时调度分页视图对象
 */
@Schema(description = "实时调度分页对象")
@Data
public class RealtimeSchedulePageVO {

    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "故障编号")
    private String faultCode;

    @Schema(description = "故障信息")
    private String faultInfo;

    @Schema(description = "故障类型")
    private String faultType;

    @Schema(description = "故障状态")
    private String faultStatus;

    @Schema(description = "超时数量")
    private Integer timeoutCount;

    @Schema(description = "超时工件ID")
    private String timeoutWorkpieceId;

    @Schema(description = "超时工人ID")
    private String timeoutWorkerId;

    @Schema(description = "超时比例")
    private BigDecimal timeoutRatio;
}