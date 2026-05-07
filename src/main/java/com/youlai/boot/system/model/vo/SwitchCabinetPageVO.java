package com.youlai.boot.system.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Schema(description = "低压柜SN号")
    private String snLCode;

    @Schema(description = "高压柜NCR描述")
    private String hNcrDiscription;

    @Schema(description = "低压柜NCR描述")
    private String lNcrDiscription;

    @Schema(description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}
