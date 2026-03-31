package com.youlai.boot.system.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.youlai.boot.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * 变迁数据实体
 */
@TableName("abb_transition_def")
@Getter
@Setter
public class Transition extends BaseEntity {

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