package com.youlai.boot.system.model.form;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 高低压柜明细表单对象
 *
* @author lijun
* @since 2026/04/23
 */
@Schema(description = "高低压柜明细表单")
@Data
public class CabinetDetailForm implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "开关柜SN号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "SN号不能为空")
    private String snCode;

    @Schema(description = "高压柜NCR描述")
    private String hNcrDiscription;

    @Schema(description = "低压柜NCR描述")
    private String lNcrDiscription;
}
