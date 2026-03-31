package com.youlai.boot.system.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.youlai.boot.common.base.BaseEntity;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

/**
 * 实时调度数据实体
 */
@TableName("abb_realtime_schedule")
@Getter
@Setter
public class RealtimeSchedule extends BaseEntity {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 故障编号
     */
    private String faultCode;

    /**
     * 故障信息
     */
    private String faultInfo;

    /**
     * 故障类型
     */
    private String faultType;

    /**
     * 故障状态
     */
    private String faultStatus;

    /**
     * 超时数量
     */
    private Integer timeoutCount;

    /**
     * 超时工件ID
     */
    private String timeoutWorkpieceId;

    /**
     * 超时工人ID
     */
    private String timeoutWorkerId;

    /**
     * 超时比例
     */
    private BigDecimal timeoutRatio;

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