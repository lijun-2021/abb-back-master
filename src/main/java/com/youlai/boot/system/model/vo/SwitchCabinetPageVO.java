package com.youlai.boot.system.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 开关柜分页视图对象
 *
 * @author lijun
 * @since 2026/04/23
 */
@Schema(description = "开关柜分页VO")
@Data
public class SwitchCabinetPageVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "开关柜SN号")
    private String snCode;

    @Schema(description = "产线")
    private String productionLine;

    @Schema(description = "下线时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime offlineTime;

    @JsonIgnore
    @Schema(description = "功能检测开始时间（仅用于内部计算状态，不返回前端）")
    private LocalDateTime functionStarttime;

    @JsonIgnore
    @Schema(description = "功能检测结束时间（仅用于内部计算状态，不返回前端）")
    private LocalDateTime functionEndtime;

    @Schema(description = "功能检测状态:0-未完成,1-进行中,2-已完成")
    private Integer functionStatus;

    @Schema(description = "功能员工姓名")
    private String functionEmpName;

    @Schema(description = "检测区域")
    private String area;

    @Schema(description = "高压柜SN号")
    private String snHCode;

    @JsonProperty("hNcrDescription1")
    @Schema(description = "高压柜NCR描述1")
    private String hNcrDescription1;

    @JsonProperty("hNcrDescription2")
    @Schema(description = "高压柜NCR描述2")
    private String hNcrDescription2;

    @JsonProperty("hNcrDescription3")
    @Schema(description = "高压柜NCR描述3")
    private String hNcrDescription3;

    @JsonProperty("hNcrDescription4")
    @Schema(description = "高压柜NCR描述4")
    private String hNcrDescription4;

    @JsonProperty("hNcrDescription5")
    @Schema(description = "高压柜NCR描述5")
    private String hNcrDescription5;

    @Schema(description = "低压柜SN号")
    private String snLCode;

    @JsonProperty("lNcrDescription1")
    @Schema(description = "低压柜NCR描述1")
    private String lNcrDescription1;

    @JsonProperty("lNcrDescription2")
    @Schema(description = "低压柜NCR描述2")
    private String lNcrDescription2;

    @JsonProperty("lNcrDescription3")
    @Schema(description = "低压柜NCR描述3")
    private String lNcrDescription3;

    @JsonProperty("lNcrDescription4")
    @Schema(description = "低压柜NCR描述4")
    private String lNcrDescription4;

    @JsonProperty("lNcrDescription5")
    @Schema(description = "低压柜NCR描述5")
    private String lNcrDescription5;

    @Schema(description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}
