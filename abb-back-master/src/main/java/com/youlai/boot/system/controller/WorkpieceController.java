package com.youlai.boot.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.youlai.boot.common.annotation.Log;
import com.youlai.boot.common.annotation.RepeatSubmit;
import com.youlai.boot.common.enums.LogModuleEnum;
import com.youlai.boot.common.model.Option;
import com.youlai.boot.core.web.PageResult;
import com.youlai.boot.core.web.Result;
import com.youlai.boot.system.model.form.WorkpieceForm;
import com.youlai.boot.system.model.query.WorkpiecePageQuery;
import com.youlai.boot.system.model.vo.WorkpiecePageVO;
import com.youlai.boot.system.service.WorkpieceService;
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
 * 工件控制器
 *
 * @author haoxr
 * @since 2024/12/31
 */
@Tag(name = "06-工件管理")
@RestController
@RequestMapping("/api/v1/workpiece")
@RequiredArgsConstructor
public class WorkpieceController {

    private final WorkpieceService workpieceService;

    @Operation(summary = "工件分页列表")
    @GetMapping("/page")
    @Log(value = "工件分页列表", module = LogModuleEnum.WORKPIECE)
    public PageResult<WorkpiecePageVO> getWorkpiecePage(
            @Valid WorkpiecePageQuery queryParams
    ) {
        IPage<WorkpiecePageVO> result = workpieceService.getWorkpiecePage(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "新增工件")
    @PostMapping
    @PreAuthorize("@ss.hasPerm('abb:workpiece:add')")
    @RepeatSubmit
    @Log(value = "新增工件", module = LogModuleEnum.WORKPIECE)
    public Result<?> saveWorkpiece(
            @RequestBody @Valid WorkpieceForm workpieceForm
    ) {
        boolean result = workpieceService.saveWorkpiece(workpieceForm);
        return Result.judge(result);
    }

    @Operation(summary = "获取工件表单数据")
    @GetMapping("/{workpieceId}/form")
    @PreAuthorize("@ss.hasPerm('abb:workpiece:edit')")
    @Log(value = "工件表单数据", module = LogModuleEnum.WORKPIECE)
    public Result<WorkpieceForm> getWorkpieceForm(
            @Parameter(description = "工件ID") @PathVariable Long workpieceId
    ) {
        WorkpieceForm formData = workpieceService.getWorkpieceFormData(workpieceId);
        return Result.success(formData);
    }

    @Operation(summary = "修改工件")
    @PutMapping(value = "/{workpieceId}")
    @PreAuthorize("@ss.hasPerm('abb:workpiece:edit')")
    @Log(value = "修改工件", module = LogModuleEnum.WORKPIECE)
    public Result<Void> updateWorkpiece(
            @Parameter(description = "工件ID") @PathVariable Long workpieceId,
            @RequestBody @Valid WorkpieceForm workpieceForm
    ) {
        boolean result = workpieceService.updateWorkpiece(workpieceId, workpieceForm);
        return Result.judge(result);
    }

    @Operation(summary = "批量删除工件")
    @DeleteMapping("/{ids}")
    @PreAuthorize("@ss.hasPerm('abb:workpiece:delete')")
    @Log(value = "删除工件", module = LogModuleEnum.WORKPIECE)
    public Result<Void> deleteWorkpieces(
            @Parameter(description = "工件ID，多个以英文逗号(,)分割") @PathVariable String ids
    ) {
        boolean result = workpieceService.deleteWorkpieces(ids);
        return Result.judge(result);
    }

    @Operation(summary = "获取工件下拉列表")
    @GetMapping("/options")
    public Result<List<Option<String>>> listWorkpieceOptions() {
        List<Option<String>> list = workpieceService.listWorkpieceOptions();
        return Result.success(list);
    }
}