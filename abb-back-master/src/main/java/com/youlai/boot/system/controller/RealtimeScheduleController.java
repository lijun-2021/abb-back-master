package com.youlai.boot.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.youlai.boot.common.annotation.Log;
import com.youlai.boot.common.annotation.RepeatSubmit;
import com.youlai.boot.common.enums.LogModuleEnum;
import com.youlai.boot.common.model.Option;
import com.youlai.boot.core.web.PageResult;
import com.youlai.boot.core.web.Result;
import com.youlai.boot.system.model.form.RealtimeScheduleForm;
import com.youlai.boot.system.model.query.RealtimeSchedulePageQuery;
import com.youlai.boot.system.model.vo.RealtimeSchedulePageVO;
import com.youlai.boot.system.service.RealtimeScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 实时调度控制层
 */
@Tag(name = "实时调度")
@RestController
@RequestMapping("/api/v1/realtimeschedule")
@RequiredArgsConstructor
public class RealtimeScheduleController {

    private final RealtimeScheduleService realtimeScheduleService;

    @Operation(summary = "实时调度分页列表")
    @GetMapping("/page")
    @Log(value = "实时调度分页列表", module = LogModuleEnum.REALTIME_SCHEDULE)
    public PageResult<RealtimeSchedulePageVO> getRealtimeSchedulePage(
            @Valid RealtimeSchedulePageQuery queryParams
    ) {
        IPage<RealtimeSchedulePageVO> result = realtimeScheduleService.getRealtimeSchedulePage(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "获取实时调度表单数据")
    @GetMapping("/{realtimeScheduleId}/form")
    @PreAuthorize("@ss.hasPerm('abb:realtimeschedule:edit')")
    @Log(value = "实时调度表单数据", module = LogModuleEnum.REALTIME_SCHEDULE)
    public Result<RealtimeScheduleForm> getRealtimeScheduleForm(
            @Parameter(description = "实时调度ID") @PathVariable Long realtimeScheduleId
    ) {
        RealtimeScheduleForm formData = realtimeScheduleService.getRealtimeScheduleFormData(realtimeScheduleId);
        return Result.success(formData);
    }

    @Operation(summary = "新增实时调度")
    @PostMapping
    @PreAuthorize("@ss.hasPerm('abb:realtimeschedule:add')")
    @RepeatSubmit
    @Log(value = "新增实时调度", module = LogModuleEnum.REALTIME_SCHEDULE)
    public Result<?> saveRealtimeSchedule(
            @RequestBody @Valid RealtimeScheduleForm realtimeScheduleForm
    ) {
        boolean result = realtimeScheduleService.saveRealtimeSchedule(realtimeScheduleForm);
        return Result.judge(result);
    }

    @Operation(summary = "修改实时调度")
    @PutMapping(value = "/{realtimeScheduleId}")
    @PreAuthorize("@ss.hasPerm('abb:realtimeschedule:edit')")
    @Log(value = "修改实时调度", module = LogModuleEnum.REALTIME_SCHEDULE)
    public Result<Void> updateRealtimeSchedule(
            @Parameter(description = "实时调度ID") @PathVariable Long realtimeScheduleId,
            @RequestBody @Valid RealtimeScheduleForm realtimeScheduleForm
    ) {
        boolean result = realtimeScheduleService.updateRealtimeSchedule(realtimeScheduleId, realtimeScheduleForm);
        return Result.judge(result);
    }

    @Operation(summary = "批量删除实时调度")
    @DeleteMapping("/{ids}")
    @PreAuthorize("@ss.hasPerm('abb:realtimeschedule:delete')")
    @Log(value = "删除实时调度", module = LogModuleEnum.REALTIME_SCHEDULE)
    public Result<Void> deleteRealtimeSchedules(
            @Parameter(description = "实时调度ID，多个以英文逗号(,)分割") @PathVariable String ids
    ) {
        boolean result = realtimeScheduleService.deleteRealtimeSchedules(ids);
        return Result.judge(result);
    }

    @Operation(summary = "获取实时调度下拉列表")
    @GetMapping("/options")
    public Result<List<Option<String>>> listRealtimeScheduleOptions() {
        List<Option<String>> list = realtimeScheduleService.listRealtimeScheduleOptions();
        return Result.success(list);
    }
}
