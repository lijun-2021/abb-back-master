package com.youlai.boot.system.model.bo;

import java.math.BigDecimal;
import lombok.Data;

/**
 * 实时调度业务对象
 */
@Data
public class RealtimeScheduleBO {

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
}