package com.youlai.boot.system.model.bo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 开关柜业务对象
 *
 * @author lijun
 * @since 2026/04/23
 */
@Data
@Schema(description = "开关柜业务对象")
public class SwitchCabinetBO {

    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "开关柜SN号")
    private String snCode;

    @Schema(description = "产线")
    private String productionLine;

    @Schema(description = "下线时间")
    private LocalDateTime offlineTime;

    @Schema(description = "耐压状态")
    private Integer withstandStatus;

    @Schema(description = "耐压员工姓名")
    private String withstandEmpName;

    @Schema(description = "功能状态")
    private Integer functionStatus;

    @Schema(description = "功能员工姓名")
    private String functionEmpName;

    @Schema(description = "检测区域")
    private String area;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;
}
