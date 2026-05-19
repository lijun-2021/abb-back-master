package com.youlai.boot.system.model.form;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

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
//
//    @Schema(description = "主键ID")
//    private Long id;


    @Schema(description = "开关柜ID列表（支持单个或批量）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "开关柜ID列表不能为空")
    private List<Long> ids;

    @Schema(description = "功能员工姓名列表（与ids一一对应，null或空字符串表示删除员工）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "功能员工姓名列表不能为空")
    private List<String> functionEmpNames;

    @Schema(description = "检测区域列表（与ids一一对应，null表示不修改）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "检测区域列表不能为空")
    private List<String> areas;

//    @Schema(description = "开关柜SN号", requiredMode = Schema.RequiredMode.REQUIRED)
//    private String snCode;
//
//    @Schema(description = "产线")
//    private String productionLine;
//
//    @Schema(description = "下线时间")
//    private String offlineTime;
//
//    @Schema(description = "功能检测开始时间")
//    private String functionStarttime;
//
//    @Schema(description = "功能检测结束时间")
//    private String functionEndtime;
//
//    @Schema(description = "功能员工姓名")
//    private String functionEmpName;
//
//
//    @Schema(description = "检测区域")
//    private String area;
}
