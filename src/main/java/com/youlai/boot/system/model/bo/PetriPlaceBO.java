package com.youlai.boot.system.model.bo;

import lombok.Data;

/**
 * Petri 库所业务对象
 */
@Data
public class PetriPlaceBO {

    /**
     * 主键 ID
     */
    private Integer id;

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
     * 是否为控制库所
     */
    private Boolean isControlPlace;

    /**
     * 是否为时间库所
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
}

