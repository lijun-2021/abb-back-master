package com.youlai.boot.system.model.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Token表单对象
 */
@Schema(description = "Token表单对象")
@Data
public class TokenForm {

    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "token名称")
    private String tokenName;

    @Schema(description = "tokenID")
    private String tokenCode;

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
}