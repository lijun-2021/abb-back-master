package com.youlai.boot.system.model.query;

import com.youlai.boot.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 员工考勤记录分页查询对象
 *
 * @author lijun
 * @since 2026/07/06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "员工考勤记录分页查询对象")
public class AttendanceRecordPageQuery extends BasePageQuery {

    @Schema(description = "员工ID")
    private String empId;

    @Schema(description = "员工姓名(模糊查询)")
    private String empName;

    @Schema(description = "组别(A/B/C/D)")
    private String empTeam;

    @Schema(description = "状态:1-在岗 2-FAT 3-耐压 4-请假 5-离职")
    private Integer state;
}
