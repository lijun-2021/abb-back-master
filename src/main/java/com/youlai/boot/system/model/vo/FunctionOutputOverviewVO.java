package com.youlai.boot.system.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Schema(description = "功能产出概览")
public class FunctionOutputOverviewVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "统计日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate statDate;

    @Schema(description = "当日下线总数")
    private Long offlineTotal;

    @Schema(description = "当日功能产出总数")
    private Long functionOutputTotal;

    @Schema(description = "数据更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    @Schema(description = "班组统计")
    private List<GroupVO> groups;

    @Data
    @Schema(description = "功能产出班组统计")
    public static class GroupVO implements Serializable {

        @Serial
        private static final long serialVersionUID = 1L;

        @Schema(description = "组别(A/B/C/D)")
        private String empTeam;

        @Schema(description = "分配任务数")
        private Long assignedCount;

        @Schema(description = "已完成数")
        private Long completedCount;

        @Schema(description = "未完成数")
        private Long incompleteCount;

        @Schema(description = "组内员工统计")
        private List<FunctionOutputEmployeeVO> employees;
    }
}
