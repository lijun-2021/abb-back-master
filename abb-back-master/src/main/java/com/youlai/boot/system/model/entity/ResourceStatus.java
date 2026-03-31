package com.youlai.boot.system.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.youlai.boot.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * 当日资源/状态实体
 */
@TableName("abb_daily_resource_status")
@Getter
@Setter
public class ResourceStatus extends BaseEntity {

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

    /**
     * 创建人 ID
     */
    private Long createBy;

    /**
     * 更新人 ID
     */
    private Long updateBy;

    /**
     * 是否删除(0-否 1-是)
     */
    private Integer isDeleted;
}