package com.youlai.boot.system.model.query;

import com.youlai.boot.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * FQC算法模拟数据分页查询对象
 *
 * @author lijun
 * @since 2026/07/15
 */
@Schema(description = "FQC算法模拟数据分页查询对象")
@Data
@EqualsAndHashCode(callSuper = false)
public class AlgorithmSimulPageQuery extends BasePageQuery {

    /**
     * 产线名称（模糊查询）
     */
    @Schema(description = "产线名称")
    private String line;

    /**
     * 合同名称（模糊查询）
     */
    @Schema(description = "合同名称")
    private String rowId;

    /**
     * 耐压区域（模糊查询）
     */
    @Schema(description = "耐压区域")       
    private String voltageZone;

    /**
     * 作业人员编号（精确查询）
     */
    @Schema(description = "作业人员编号")
    private Integer worker;

    /**
     * 功能区域（模糊查询）
     */
    @Schema(description = "功能区域")
    private String functionZone;

    /**
     * 检验员编号（模糊查询）
     */
    @Schema(description = "检验员编号")
    private String inspector;
}