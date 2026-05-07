package com.youlai.boot.system.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 仪表盘统计视图对象
 *
* @author lijun
* @since 2026/04/23
 */
@Schema(description = "仪表盘统计VO")
@Data
public class DashboardStatsVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "今日总任务数")
    private Integer totalTasks;

    @Schema(description = "完成率")
    private Double completionRate;

    @Schema(description = "功能任务统计")
    private TaskTypeStats functionStats;

    /**
     * 任务类型统计内部类
     */
    @Data
    @Schema(description = "任务类型统计")
    public static class TaskTypeStats implements Serializable {
        @Serial
        private static final long serialVersionUID = 1L;

        @Schema(description = "总数")
        private Integer total;

        @Schema(description = "已完成")
        private Integer completed;

        @Schema(description = "未完成")
        private Integer notCompleted;
    }

}
