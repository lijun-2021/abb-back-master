package com.youlai.boot.system.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@Schema(description = "功能产出员工统计")
public class FunctionOutputEmployeeVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "员工ID")
    private String empId;

    @Schema(description = "员工姓名")
    private String empName;

    @Schema(description = "员工组别(A/B/C/D)")
    private String empTeam;

    @Schema(description = "分配任务数")
    private Long assignedCount;

    @Schema(description = "已完成数")
    private Long completedCount;

    @Schema(description = "未完成数")
    private Long incompleteCount;
}
