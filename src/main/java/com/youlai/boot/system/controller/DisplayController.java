package com.youlai.boot.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.youlai.boot.core.web.PageResult;
import com.youlai.boot.system.model.query.DisplayPageQuery;
import com.youlai.boot.system.model.vo.DisplayPageVO;
import com.youlai.boot.system.service.DisplayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 开关柜数量展示控制器
 *
 * @author lijun
 * @since 2026/04/28
 */
@Tag(name = "FQC开关柜数据展示")
@RestController
@RequestMapping("/api/v1/fqc/display")
@RequiredArgsConstructor
public class DisplayController {

    private final DisplayService displayService;

    @Operation(summary = "获取开关柜数据展示分页列表")
    @GetMapping
    public PageResult<DisplayPageVO> getDisplayPage(
            @Parameter(description = "查询参数") DisplayPageQuery queryParams) {
        IPage<DisplayPageVO> page = displayService.getDisplayPage(queryParams);
        return PageResult.success(page);
    }
}