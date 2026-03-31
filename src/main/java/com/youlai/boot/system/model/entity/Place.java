package com.youlai.boot.system.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.youlai.boot.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * 库所数据实体
 */
@TableName("abb_place")
@Getter
@Setter
public class Place extends BaseEntity {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 库所名称
     */
    private String placeName;

    /**
     * 容量
     */
    private Integer capacity;

    /**
     * 前置变迁
     */
    private String preTransition;

    /**
     * 后置变迁
     */
    private String postTransition;

    /**
     * 库所类别
     */
    private String placeType;

    /**
     * 赋时库所标志位
     */
    private Boolean timedPlaceFlag;

    /**
     * 对应加工位ID
     */
    private String processStationId;

    /**
     * 阶段标志
     */
    private Integer stageFlag;

    /**
     * 库所颜色
     */
    private String placeColor;

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