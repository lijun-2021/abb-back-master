package com.youlai.boot.system.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 开关柜数量展示分页视图对象
 *
 * @author lijun
 * @since 2026/04/28
 */
@Schema(description = "开关柜数量展示分页VO")
@Data
public class DisplayPageVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "每天下线开关柜数量")
    private Long fqcDailyInput;

    @Schema(description = "每天挂证开关柜数量")
    private Long fqcDailyOutput;

    @Schema(description = "历史库存开关柜数量")
    private Long fqcQuantityInstock;

    @Schema(description = "下线待分配数量")
    private Long fqcWithstandUndistributed;

    @Schema(description = "功能完成待挂证数量")
    private Long fqcFunctionUndistributed;

    @Schema(description = "已分配数量（每天下线数量-待分配数量）")
    private Long fqcAllocated;

    @Schema(description = "功能试验产出(每天挂证数量+功能待分配数量)")
    private Long fqcFunctionalTestOutput;

    @Schema(description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createTime;

    @Schema(description = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate updateTime;
}
