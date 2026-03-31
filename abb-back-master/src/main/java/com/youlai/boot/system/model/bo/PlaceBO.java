package com.youlai.boot.system.model.bo;

import lombok.Data;

/**
 * 库所业务对象
 */
@Data
public class PlaceBO {

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
}