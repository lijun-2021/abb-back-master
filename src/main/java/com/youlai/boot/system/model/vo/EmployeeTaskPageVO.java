package com.youlai.boot.system.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 员工任务分页视图对象
 *
* @author lijun
* @since 2026/04/23
 */
@Schema(description = "员工任务分页VO")
@Data
public class EmployeeTaskPageVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "员工ID")
    private String empId;

    @Schema(description = "员工姓名")
    private String empName;

    @Schema(description = "任务类型:1-耐压,2-功能")
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
    @Schema(description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}
