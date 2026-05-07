package com.youlai.boot.system.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.youlai.boot.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 开关柜实体
 *
 * @author lijun
 * @since 2026/04/23
 */
@TableName("fqc_switch_cabinet")
@Getter
@Setter
public class SwitchCabinet extends BaseEntity {

    /**
     * 开关柜SN号
     */
    private String snCode;

    /**
     * 产线
     */
    private String productionLine;

    /**
     * 下线时间
     */
    private LocalDateTime offlineTime;

    /**
     * 功能检测开始时间
     */
    private LocalDateTime functionStarttime;

    /**
     * 功能检测结束时间
     */
    private LocalDateTime functionEndtime;

    /**
     * 功能员工姓名
     */
    private String functionEmpName;

    /**
     * 检测区域
     */
    private String area;

    /**
     * 是否删除 0-未删除 1-已删除
     */
    private Integer isDeleted;
}
