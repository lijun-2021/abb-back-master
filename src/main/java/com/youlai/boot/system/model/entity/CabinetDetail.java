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
    private String hNcrDescription1;

    @Schema(description = "高压柜NCR描述2")
    private String hNcrDescription2;

    @Schema(description = "高压柜NCR描述3")
    private String hNcrDescription3;

    @Schema(description = "高压柜NCR描述4")
    private String hNcrDescription4;

    @Schema(description = "高压柜NCR描述5")
    private String hNcrDescription5;

    /**
     * 低压柜SN号
     */
    private String snLCode;

    @Schema(description = "低压柜NCR描述1")
    private String lNcrDescription1;

    @Schema(description = "低压柜NCR描述2")
    private String lNcrDescription2;

    @Schema(description = "低压柜NCR描述3")
    private String lNcrDescription3;

    @Schema(description = "低压柜NCR描述4")
    private String lNcrDescription4;

    @Schema(description = "低压柜NCR描述5")
    private String lNcrDescription5;

    /**
     * 是否删除 0-未删除 1-已删除
     */
    private Integer isDeleted;
}
