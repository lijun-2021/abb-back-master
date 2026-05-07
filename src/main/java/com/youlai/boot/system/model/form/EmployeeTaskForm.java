package com.youlai.boot.system.model.form;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.io.Serial;
import java.io.Serializable;


/**
 * 员工任务表单对象
 *
 * @author lijun
 * @since 2026/04/23
 */
@Schema(description = "员工任务表单")
@Data
public class EmployeeTaskForm implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "员工ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "员工ID不能为空")
    private String empId;

    @Schema(description = "员工姓名", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "员工姓名不能为空")
    private String empName;

    @Schema(description = "任务类型: 1-耐压,2-功能", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "任务类型不能为空")
    private Integer taskType;

    @Schema(description = "分配SN1号")
    private String snCode1;
    @Schema(description = "分配SN2号")
    private String snCode2;
    @Schema(description = "分配SN3号")
    private String snCode3;
    @Schema(description = "分配SN4号")
    private String snCode4;
    @Schema(description = "分配SN5号")
    private String snCode5;
    @Schema(description = "分配SN6号")
    private String snCode6;
    @Schema(description = "分配SN7号")
    private String snCode7;
    @Schema(description = "分配SN8号")
    private String snCode8;
    @Schema(description = "分配SN9号")
    private String snCode9;
    @Schema(description = "分配SN10号")
    private String snCode10;

}