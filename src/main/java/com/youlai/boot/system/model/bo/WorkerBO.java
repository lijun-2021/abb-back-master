package com.youlai.boot.system.model.bo;

import lombok.Data;

/**
 * 工人业务对象
 */
@Data
public class WorkerBO {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 工人ID
     */
    private String workerCode;

    /**
     * 性别，1-男，2-女
     */
    private Integer gender;

    /**
     * 身高
     */
    private String height;

    /**
     * 工龄
     */
    private String workYears;

    /**
     * 熟练度/级别
     */
    private String skillLevel;

    /**
     * 打卡开始时间
     */
    private String clockInTime;

    /**
     * 打卡结束时间
     */
    private String clockOutTime;

    /**
     * 所在加工位ID
     */
    private String processStationId;

    /**
     * 排程工件ID序列
     */
    private String scheduledWorkpieceIds;

    /**
     * 当前加工工件ID
     */
    private String currentWorkpieceId;

    /**
     * 下一件加工工件ID
     */
    private String nextWorkpieceId;
}