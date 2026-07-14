package com.youlai.boot.system.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 员工考勤记录分页视图对象
 *
 * @author lijun
 * @since 2026/07/06
 */
@Schema(description = "员工考勤记录分页VO")
@Data
public class AttendanceRecordPageVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "员工ID")
    private String empId;

    @Schema(description = "员工组别(A/B/C/D)")
    private String empTeam;

    @Schema(description = "员工姓名")
    private String empName;

    @Schema(description = "上午状态:1-在岗 2-FAT 3-耐压 4-请假 5-离职")
    private Integer amState;

    @Schema(description = "下午状态:1-在岗 2-FAT 3-耐压 4-请假 5-离职")
    private Integer pmState;

    @Schema(description = "考勤日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate recordDate;

    @Schema(description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}
