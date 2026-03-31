package com.youlai.boot.system.model.bo;

import lombok.Data;

/**
 * 当日资源/状态业务对象
 */
@Data
public class ResourceStatusBO {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 加工位ID
     */
    private String processStationId;

    /**
     * 加工位工人ID
     */
    private String processWorkerId;

    /**
     * 加工位状态
     */
    private String processStationStatus;

    /**
     * 立库ID
     */
    private String warehouseId;

    /**
     * 立库总空余存储数
     */
    private Integer warehouseTotalFree;

    /**
     * 立库当前空余存储数
     */
    private Integer warehouseCurrentFree;

    /**
     * 立库当前占用存储数
     */
    private Integer warehouseCurrentUsed;

    /**
     * RGV标志位
     */
    private String rgvFlag;

    /**
     * AGV标志位
     */
    private String agvFlag;

    /**
     * 立库机械臂标志位
     */
    private String robotArmFlag;
}