package com.youlai.boot.system.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.youlai.boot.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * Petri 库所数据实体
 */
@TableName("petri_place")
@Getter
@Setter
public class PetriPlace extends BaseEntity {

    /**
     * 库所名称
     */
    private String placeName;

    /**
     * 容量
     */
    private Integer capacity;

    /**
     * 前置弧（JSON 格式）
     */
    private String preArcs;

    /**
     * 后置弧（JSON 格式）
     */
    private String postArcs;

    /**
     * 是否为控制库所 (0-否 1-是)
     */
    private Boolean isControlPlace;

    /**
     * 是否为时间库所 (0-否 1-是)
     */
    private Boolean isTimePlace;

    /**
     * 熟练度
     */
    private Float proficiency;

    /**
     * 阶段
     */
    private Integer stage;

    /**
     * 颜色
     */
    private String color;

    /**
     * 创建人 ID
     */
    private Long createBy;

    /**
     * 更新人 ID
     */
    private Long updateBy;

}
