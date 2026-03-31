package com.youlai.boot.system.model.bo;

import lombok.Data;

/**
 * 变迁业务对象
 */
@Data
public class TransitionBO {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 变迁名称
     */
    private String transitionName;

    /**
     * 前置库所
     */
    private String prePlace;

    /**
     * 后置库所
     */
    private String postPlace;

    /**
     * place_check标志位
     */
    private Boolean placeCheckFlag;

    /**
     * place_lock标志位
     */
    private Boolean placeLockFlag;
}