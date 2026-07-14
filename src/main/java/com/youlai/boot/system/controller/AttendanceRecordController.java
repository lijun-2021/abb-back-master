package com.youlai.boot.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.youlai.boot.common.annotation.Log;
import com.youlai.boot.common.enums.LogModuleEnum;
import com.youlai.boot.core.web.PageResult;
import com.youlai.boot.core.web.Result;
import com.youlai.boot.system.model.form.AttendanceRecordForm;
import com.youlai.boot.system.model.query.AttendanceRecordPageQuery;
import com.youlai.boot.system.model.vo.AttendanceRecordPageVO;
import com.youlai.boot.system.service.AttendanceRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 员工考勤记录控制层
 *
 * @author lijun
 * @since 2026/07/06
 */
@Tag(name = "FQC员工考勤管理")
@RestController
@RequestMapping("/api/v1/fqc/attendance-records")
@RequiredArgsConstructor
public class AttendanceRecordController {

    private final AttendanceRecordService attendanceRecordService;

    @Operation(summary = "员工考勤记录分页列表")
    @GetMapping("/page")
    @Log(value = "员工考勤记录分页列表", module = LogModuleEnum.OTHER)
    public PageResult<AttendanceRecordPageVO> getAttendanceRecordPage(
            @Valid AttendanceRecordPageQuery queryParams
    ) {
        IPage<AttendanceRecordPageVO> result = attendanceRecordService.getAttendanceRecordPage(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "员工考勤记录分页列表(兼容前端根路径请求)")
    @GetMapping
    @Log(value = "员工考勤记录分页列表", module = LogModuleEnum.OTHER)
    public PageResult<AttendanceRecordPageVO> getAttendanceRecordPageRoot(
            @Valid AttendanceRecordPageQuery queryParams
    ) {
        IPage<AttendanceRecordPageVO> result = attendanceRecordService.getAttendanceRecordPage(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "更新员工考勤状态（支持单条和批量）")
    @PatchMapping("/state")
    @Log(value = "更新员工考勤状态", module = LogModuleEnum.OTHER)
    public Result<Void> updateAttendanceState(
            @RequestBody @Valid AttendanceRecordForm form
    ) {
        boolean result = attendanceRecordService.updateAttendanceState(form);
        return Result.judge(result);
    }
}
