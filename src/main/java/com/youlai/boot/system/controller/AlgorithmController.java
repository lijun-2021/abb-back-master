package com.youlai.boot.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.youlai.boot.common.annotation.Log;
import com.youlai.boot.common.annotation.RepeatSubmit;
import com.youlai.boot.common.enums.LogModuleEnum;
import com.youlai.boot.common.model.Option;
import com.youlai.boot.core.web.PageResult;
import com.youlai.boot.core.web.Result;
import com.youlai.boot.system.model.form.AlgorithmForm;
import com.youlai.boot.system.model.query.AlgorithmPageQuery;
import com.youlai.boot.system.model.vo.AlgorithmPageVO;
import com.youlai.boot.system.service.AlgorithmService;
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
 * 算法控制层
 */
@Tag(name = "算法数据")
@RestController
@RequestMapping("/api/v1/algorithm")
@RequiredArgsConstructor
public class AlgorithmController {

    private final AlgorithmService algorithmService;

    @Operation(summary = "算法分页列表")
    @GetMapping("/page")
    @Log(value = "算法分页列表", module = LogModuleEnum.ALGORITHM)
    public PageResult<AlgorithmPageVO> getAlgorithmPage(
            @Valid AlgorithmPageQuery queryParams
    ) {
        IPage<AlgorithmPageVO> result = algorithmService.getAlgorithmPage(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "新增算法")
    @PostMapping
    @PreAuthorize("@ss.hasPerm('abb:algorithm:add')")
    @RepeatSubmit
    @Log(value = "新增算法", module = LogModuleEnum.ALGORITHM)
    public Result<?> saveAlgorithm(
            @RequestBody @Valid AlgorithmForm algorithmForm
    ) {
        boolean result = algorithmService.saveAlgorithm(algorithmForm);
        return Result.judge(result);
    }

    @Operation(summary = "获取算法表单数据")
    @GetMapping("/{algorithmId}/form")
    @PreAuthorize("@ss.hasPerm('abb:algorithm:edit')")
    @Log(value = "算法表单数据", module = LogModuleEnum.ALGORITHM)
    public Result<AlgorithmForm> getAlgorithmForm(
            @Parameter(description = "算法ID") @PathVariable Long algorithmId
    ) {
        AlgorithmForm formData = algorithmService.getAlgorithmFormData(algorithmId);
        return Result.success(formData);
    }

    @Operation(summary = "修改算法")
    @PutMapping(value = "/{algorithmId}")
    @PreAuthorize("@ss.hasPerm('abb:algorithm:edit')")
    @Log(value = "修改算法", module = LogModuleEnum.ALGORITHM)
    public Result<Void> updateAlgorithm(
            @Parameter(description = "算法ID") @PathVariable Long algorithmId,
            @RequestBody @Valid AlgorithmForm algorithmForm
    ) {
        boolean result = algorithmService.updateAlgorithm(algorithmId, algorithmForm);
        return Result.judge(result);
    }

    @Operation(summary = "批量删除算法")
    @DeleteMapping("/{ids}")
    @PreAuthorize("@ss.hasPerm('abb:algorithm:delete')")
    @Log(value = "删除算法", module = LogModuleEnum.ALGORITHM)
    public Result<Void> deleteAlgorithms(
            @Parameter(description = "算法ID，多个以英文逗号(,)分割") @PathVariable String ids
    ) {
        boolean result = algorithmService.deleteAlgorithms(ids);
        return Result.judge(result);
    }

    @Operation(summary = "获取算法下拉列表")
    @GetMapping("/options")
    public Result<List<Option<String>>> listAlgorithmOptions() {
        List<Option<String>> list = algorithmService.listAlgorithmOptions();
        return Result.success(list);
    }
}