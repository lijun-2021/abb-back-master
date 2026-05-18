package com.youlai.boot.system.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.youlai.boot.common.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(description = "高压柜NCR描述1")
    private String hNcrDiscription1;

    @Schema(description = "高压柜NCR描述2")
    private String hNcrDiscription2;

    @Schema(description = "高压柜NCR描述3")
    private String hNcrDiscription3;

    @Schema(description = "高压柜NCR描述4")
    private String hNcrDiscription4;

    @Schema(description = "高压柜NCR描述5")
    private String hNcrDiscription5;

    /**
     * 低压柜SN号
     */
    private String snLCode;

    @Schema(description = "低压柜NCR描述1")
    private String lNcrDiscription1;

    @Schema(description = "低压柜NCR描述2")
    private String lNcrDiscription2;

    @Schema(description = "低压柜NCR描述3")
    private String lNcrDiscription3;

    @Schema(description = "低压柜NCR描述4")
    private String lNcrDiscription4;

    @Schema(description = "低压柜NCR描述5")
    private String lNcrDiscription5;

    /**
     * 是否删除 0-未删除 1-已删除
     */
    private Integer isDeleted;
}
