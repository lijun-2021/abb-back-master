package com.youlai.boot.system.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.youlai.boot.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * 开关柜数量展示实体
 *
 * @author lijun
 * @since 2026/04/28
 */
@TableName("fqc_display")
@Getter
@Setter
public class Display extends BaseEntity {

    /**
     * 每天下线开关柜数量
     */
    private Long fqcDailyInput;

    /**
     * 每天挂证开关柜数量
     */
    private Long fqcDailyOutput;

    /**
     * 历史库存开关柜数量
     */
    private Long fqcQuantityInstock;

    /**
     * 下线待分配数量
     */
    private Long fqcWithstandUndistributed;

    /**
     * 功能完成待挂证数量
     */
    private Long fqcFunctionUndistributed;

    /**
     * 是否删除 0-未删除 1-已删除
     */
    private Integer isDeleted;
}
