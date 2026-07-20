package com.youlai.boot.system.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * FQC算法模拟数据分页视图对象
 *
 * @author lijun
 * @since 2026/07/15
 */
@Schema(description = "FQC算法模拟数据分页视图对象")
@Data
public class AlgorithmSimulPageVO {

    /**
     * 记录ID
     */
    @Schema(description = "记录ID")
    private Integer id;

    /**
     * 产线名称
     */
    @Schema(description = "产线名称")
    private String line;

    /**
     * 合同名称
     */
    @Schema(description = "合同名称")
    private String rowId;

    /**
     * 耐压区域
     */
    @Schema(description = "耐压区域")
    private String voltageZone;

    /**
     * 作业人员编号
     */
    @Schema(description = "作业人员编号")
    private Integer worker;

    /**
     * 功能区域
     */
    @Schema(description = "功能区域")
    private String functionZone;

    /**
     * 检验员编号
     */
    @Schema(description = "检验员编号")
    private String inspector;

    /**
     * 工序时长（分钟）
     */
    @Schema(description = "工序时长(分钟)")
    private Integer functionMinutes;
}