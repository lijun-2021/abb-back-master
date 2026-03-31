package com.youlai.boot.system.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 工件分页视图对象
 */
@Schema(description = "工件分页视图对象")
@Data
public class WorkpiecePageVO {

    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "SN")
    private String sn;

    @Schema(description = "编号")
    private String code;

    @Schema(description = "型号")
    private String model;

    @Schema(description = "线束根数")
    private Integer harnessCount;

    @Schema(description = "线束类型")
    private String harnessType;

    @Schema(description = "端子类型")
    private String terminalType;

    @Schema(description = "长度")
    private String length;

    @Schema(description = "宽度")
    private String width;

    @Schema(description = "高度")
    private String height;

    @Schema(description = "加工流程")
    private String processFlow;

    @Schema(description = "当前状态")
    private String currentStatus;
}