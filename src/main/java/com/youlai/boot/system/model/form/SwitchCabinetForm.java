package com.youlai.boot.system.model.form;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 开关柜表单对象
 *
* @author lijun
* @since 2026/04/23
 */
@Schema(description = "开关柜表单")
@Data
public class SwitchCabinetForm implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "开关柜SN号", requiredMode = Schema.RequiredMode.REQUIRED)
    private String snCode;

    @Schema(description = "产线")
    private String productionLine;

    @Schema(description = "下线时间")
    private String offlineTime;

    @Schema(description = "功能检测开始时间")
    private String functionStarttime;

    @Schema(description = "功能检测结束时间")
    private String functionEndtime;

    @Schema(description = "功能员工姓名")
    private String functionEmpName;

    @Schema(description = "检测区域")
    private String area;
}
