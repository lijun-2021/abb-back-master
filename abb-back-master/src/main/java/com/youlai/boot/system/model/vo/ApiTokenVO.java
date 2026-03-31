package com.youlai.boot.system.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * API返回的Token视图对象
 */
@Schema(description = "API返回的Token视图对象")
@Data
public class ApiTokenVO {

    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "token名称")
    private String tokenName;

    @Schema(description = "tokenID")
    private Integer tokenCode;

    @Schema(description = "门板预装时间_预")
    private Integer doorPreinstallTime;

    @Schema(description = "门板时间_预")
    private Integer doorTime;

    @Schema(description = "网格预装时间_预")
    private Integer gridPreinstallTime;

    @Schema(description = "网格时间_预")
    private Integer gridTime;

    @Schema(description = "顶板时间_预")
    private Integer topPlateTime;

    @Schema(description = "底板时间_预")
    private Integer bottomPlateTime;

    @Schema(description = "壳体组装_预")
    private Integer shellAssemblyTime;

    @Schema(description = "总成时间_预")
    private Integer totalAssemblyTime;

    @Schema(description = "创建时间")
    private Long createTime;

    @Schema(description = "更新时间")
    private Long updateTime;
}