package com.youlai.boot.system.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.youlai.boot.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * 工件实体
 */
@TableName("abb_workpiece")
@Getter
@Setter
public class Workpiece extends BaseEntity {

    /**
     * SN
     */
    private String sn;

    /**
     * 编号
     */
    private String code;

    /**
     * 型号
     */
    private String model;

    /**
     * 线束根数
     */
    private Integer harnessCount;

    /**
     * 线束类型
     */
    private String harnessType;

    /**
     * 端子类型
     */
    private String terminalType;

    /**
     * 长度
     */
    private String length;

    /**
     * 宽度
     */
    private String width;

    /**
     * 高度
     */
    private String height;

    /**
     * 加工流程
     */
    private String processFlow;

    /**
     * 当前状态
     */
    private String currentStatus;

    /**
     * 创建人 ID
     */
    @TableField(fill = com.baomidou.mybatisplus.annotation.FieldFill.INSERT)
    private Long createBy;

    /**
     * 更新人 ID
     */
    @TableField(fill = com.baomidou.mybatisplus.annotation.FieldFill.INSERT_UPDATE)
    private Long updateBy;

    /**
     * 是否删除(0-否 1-是)
     */
    private Integer isDeleted;
}