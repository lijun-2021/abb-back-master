package com.youlai.boot.system.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.youlai.boot.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 排程操作记录实体
 *
 * @author lijun
 * @since 2026/04/23
 */
@TableName("fqc_schedule_record")
@Getter
@Setter
public class ScheduleRecord extends BaseEntity {

    /**
     * SN号
     */
    private String snCode;

    /**
     * 操作类型 1-保存耐压员工 2-保存功能员工 3-状态更新
     */
    private Integer operateType;

    /**
     * 修改前值
     */
    private String beforeValue;

    /**
     * 修改后值
     */
    private String afterValue;

    /**
     * 操作人ID
     */
    private Long operateUser;

    /**
     * 操作人姓名
     */
    private String operateUserName;

    /**
     * 排除 updateTime 字段（数据库表中不存在）
     */
    @TableField(exist = false)
    private LocalDateTime updateTime;
}
