package com.youlai.boot.system.model.query;

import com.youlai.boot.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 员工任务分页查询对象
 *
* @author lijun
* @since 2026/04/23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "员工任务分页查询对象")
public class EmployeeTaskPageQuery extends BasePageQuery {

    @Schema(description = "员工ID")
    private String empId;

    @Schema(description = "员工姓名")
    private String empName;

    @Schema(description = "任务类型:1-耐压, 2-功能")
    private Integer taskType;

    @Schema(description = "分配SN号(模糊查询)")
    private String snCode;
}
