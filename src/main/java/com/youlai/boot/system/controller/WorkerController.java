package com.youlai.boot.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.youlai.boot.common.annotation.Log;
import com.youlai.boot.common.annotation.RepeatSubmit;
import com.youlai.boot.common.enums.LogModuleEnum;
import com.youlai.boot.common.model.Option;
import com.youlai.boot.core.web.PageResult;
import com.youlai.boot.core.web.Result;
import com.youlai.boot.system.model.form.WorkerForm;
import com.youlai.boot.system.model.query.WorkerPageQuery;
import com.youlai.boot.system.model.vo.WorkerPageVO;
import com.youlai.boot.system.service.WorkerService;
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
 * 工人控制层
 */
@Tag(name = "人员数据")
@RestController
@RequestMapping("/api/v1/worker")
@RequiredArgsConstructor
public class WorkerController {

    private final WorkerService workerService;

    @Operation(summary = "工人分页列表")
    @GetMapping("/page")
    @Log(value = "工人分页列表", module = LogModuleEnum.WORKER)
    public PageResult<WorkerPageVO> getWorkerPage(
            @Valid WorkerPageQuery queryParams
    ) {
        IPage<WorkerPageVO> result = workerService.getWorkerPage(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "新增工人")
    @PostMapping
    @PreAuthorize("@ss.hasPerm('abb:worker:add')")
    @RepeatSubmit
    @Log(value = "新增工人", module = LogModuleEnum.WORKER)
    public Result<?> saveWorker(
            @RequestBody @Valid WorkerForm workerForm
    ) {
        boolean result = workerService.saveWorker(workerForm);
        return Result.judge(result);
    }

    @Operation(summary = "获取工人表单数据")
    @GetMapping("/{workerId}/form")
    @PreAuthorize("@ss.hasPerm('abb:worker:edit')")
    @Log(value = "工人表单数据", module = LogModuleEnum.WORKER)
    public Result<WorkerForm> getWorkerForm(
            @Parameter(description = "工人ID") @PathVariable Long workerId
    ) {
        WorkerForm formData = workerService.getWorkerFormData(workerId);
        return Result.success(formData);
    }

    @Operation(summary = "修改工人")
    @PutMapping(value = "/{workerId}")
    @PreAuthorize("@ss.hasPerm('abb:worker:edit')")
    @Log(value = "修改工人", module = LogModuleEnum.WORKER)
    public Result<Void> updateWorker(
            @Parameter(description = "工人ID") @PathVariable Long workerId,
            @RequestBody @Valid WorkerForm workerForm
    ) {
        boolean result = workerService.updateWorker(workerId, workerForm);
        return Result.judge(result);
    }

    @Operation(summary = "批量删除工人")
    @DeleteMapping("/{ids}")
    @PreAuthorize("@ss.hasPerm('abb:worker:delete')")
    @Log(value = "删除工人", module = LogModuleEnum.WORKER)
    public Result<Void> deleteWorkers(
            @Parameter(description = "工人ID，多个以英文逗号(,)分割") @PathVariable String ids
    ) {
        boolean result = workerService.deleteWorkers(ids);
        return Result.judge(result);
    }

    @Operation(summary = "获取工人下拉列表")
    @GetMapping("/options")
    public Result<List<Option<String>>> listWorkerOptions() {
        List<Option<String>> list = workerService.listWorkerOptions();
        return Result.success(list);
    }
}