package com.youlai.boot.system.model.form;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 员工考勤记录表单（仅用于更新state）
 *
 * @author lijun
 * @since 2026/07/06
 */
@Data
@Schema(description = "员工考勤记录表单")
public class AttendanceRecordForm {

    @NotNull(message = "员工ID不能为空")
    @Schema(description = "员工ID")
    private String empId;

    @NotNull(message = "状态不能为空")
    @Schema(description = "状态:1-在岗 2-FAT 3-耐压 4-请假 5-离职")
    private Integer state;
}
