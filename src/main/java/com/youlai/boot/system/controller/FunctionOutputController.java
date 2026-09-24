package com.youlai.boot.system.controller;

import com.youlai.boot.core.web.Result;
import com.youlai.boot.system.model.query.FunctionOutputQuery;
import com.youlai.boot.system.model.vo.FunctionOutputOverviewVO;
import com.youlai.boot.system.service.FunctionOutputService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "FQC功能产出概览")
@RestController
@RequestMapping("/api/v1/fqc/function-output")
@RequiredArgsConstructor
public class FunctionOutputController {

    private final FunctionOutputService functionOutputService;

    @Operation(summary = "获取当日功能产出概览")
    @GetMapping("/overview")
    public Result<FunctionOutputOverviewVO> getOverview(
            @Valid FunctionOutputQuery queryParams) {
        return Result.success(functionOutputService.getOverview(queryParams));
    }
}
