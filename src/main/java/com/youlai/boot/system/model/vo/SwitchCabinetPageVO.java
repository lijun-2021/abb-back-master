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

    @JsonProperty("hNcrDiscription1")
    @Schema(description = "高压柜NCR描述1")
    private String hNcrDiscription1;

    @JsonProperty("hNcrDiscription2")
    @Schema(description = "高压柜NCR描述2")
    private String hNcrDiscription2;

    @JsonProperty("hNcrDiscription3")
    @Schema(description = "高压柜NCR描述3")
    private String hNcrDiscription3;

    @JsonProperty("hNcrDiscription4")
    @Schema(description = "高压柜NCR描述4")
    private String hNcrDiscription4;

    @JsonProperty("hNcrDiscription5")
    @Schema(description = "高压柜NCR描述5")
    private String hNcrDiscription5;

    @Schema(description = "低压柜SN号")
    private String snLCode;

    @JsonProperty("lNcrDiscription1")
    @Schema(description = "低压柜NCR描述1")
    private String lNcrDiscription1;

    @JsonProperty("lNcrDiscription2")
    @Schema(description = "低压柜NCR描述2")
    private String lNcrDiscription2;

    @JsonProperty("lNcrDiscription3")
    @Schema(description = "低压柜NCR描述3")
    private String lNcrDiscription3;

    @JsonProperty("lNcrDiscription4")
    @Schema(description = "低压柜NCR描述4")
    private String lNcrDiscription4;

    @JsonProperty("lNcrDiscription5")
    @Schema(description = "低压柜NCR描述5")
    private String lNcrDiscription5;

    @Schema(description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}
