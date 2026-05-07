package com.youlai.boot.system.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.youlai.boot.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * 高低压柜明细实体
 *
 * @author lijun
 * @since 2026/04/23
 */
@TableName("fqc_cabinet_detail")
@Getter
@Setter
public class CabinetDetail extends BaseEntity {

    /**
     * 开关柜SN号
     */
    private String snCode;

    /**
     * 高压柜SN号
     */
    private String snHCode;

    /**
     * 高压柜NCR描述
     */
    private String hNcrDiscription;

    /**
     * 低压柜SN号
     */
    private String snLCode;

    /**
     * 低压柜NCR描述
     */
    private String lNcrDiscription;

    /**
     * 是否删除 0-未删除 1-已删除
     */
    private Integer isDeleted;
}
