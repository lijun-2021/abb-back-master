package com.youlai.boot.system.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 工人分页视图对象
 */
@Schema(description = "工人分页对象")
@Data
public class WorkerPageVO {

    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "工人ID")
    private String workerCode;

    @Schema(description = "性别，1-男，2-女")
    private Integer gender;

    @Schema(description = "身高")
    private String height;

    @Schema(description = "工龄")
    private String workYears;

    @Schema(description = "熟练度/级别")
    private String skillLevel;

    @Schema(description = "打卡开始时间")
    private String clockInTime;

    @Schema(description = "打卡结束时间")
    private String clockOutTime;

    @Schema(description = "所在加工位ID")
    private String processStationId;

    @Schema(description = "当前加工工件ID")
    private String currentWorkpieceId;

    @Schema(description = "下一件加工工件ID")
    private String nextWorkpieceId;
}