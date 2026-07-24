package com.youlai.boot.system.model.query;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.youlai.boot.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

//import java.time.LocalDate;
import java.util.List;

/**
 * 开关柜分页查询对象
 *
* @author lijun
* @since 2026/04/23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "开关柜分页查询对象")
public class SwitchCabinetPageQuery extends BasePageQuery {

    @Schema(description = "关键字(SN号/产线/区域)")
    private String keywords;

    @Schema(description = "开关柜SN号")
    private String snCode;

    @Schema(description = "SN合同号")
    private String snContract;

    @Schema(description = "产线")
    private String productionLine;

    @Schema(description = "检测区域")
    private String area;

    @Schema(description = "下线时间范围")
    private List<String> offlineTime;

    @Schema(description = "功能员工姓名(模糊查询)")
    private String functionEmpName;

    @Schema(description = "是否需要FAT(0-否,1-是)")
    private Integer fat;

    @Schema(description = "功能检测状态(0-未完成,1-进行中,2-已完成)")
    private Integer functionStatus;

    @Schema(description = "功能检查开始时间")
    private String functionStarttime;

    @Schema(description = "功能检查结束时间")
    private String functionEndtime;

//    @Schema(description = "任务日期(按当天分配的SN查询，不传默认当天)")
//    private LocalDate taskDate;

//    /**
//     * 是否超级管理员（用于数据权限控制）
//     */
//    @JsonIgnore
//    @Schema(hidden = true)
//    private Boolean isRoot;
}
