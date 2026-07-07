package com.youlai.boot.system.model.form;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

/**
 * 员工考勤记录表单（支持单条和批量更新state）
 *
 * @author lijun
 * @since 2026/07/06
 */
@Data
@Schema(description = "员工考勤记录表单")
public class AttendanceRecordForm {

    @Schema(description = "单条更新时的员工ID")
    private String empId;

    @Schema(description = "单条更新时的状态:1-在岗 2-FAT 3-耐压 4-请假 5-离职")
    private Integer state;

    @Valid
    @NotEmpty(message = "批量更新时列表不能为空")
    @Schema(description = "批量更新时的员工状态列表")
    private List<AttendanceStateItem> items;

    /**
     * 内部类：单个员工状态项
     */
    @Data
    @Schema(description = "员工状态项")
    public static class AttendanceStateItem {
        
        @jakarta.validation.constraints.NotNull(message = "员工ID不能为空")
        @Schema(description = "员工ID")
        private String empId;

        @jakarta.validation.constraints.NotNull(message = "状态不能为空")
        @Schema(description = "状态:1-在岗 2-FAT 3-耐压 4-请假 5-离职")
        private Integer state;
    }
}
