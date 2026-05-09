package com.youlai.boot.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.youlai.boot.common.annotation.Log;
import com.youlai.boot.common.annotation.RepeatSubmit;
import com.youlai.boot.common.enums.LogModuleEnum;
import com.youlai.boot.core.web.PageResult;
import com.youlai.boot.core.web.Result;
import com.youlai.boot.system.model.form.SwitchCabinetForm;
import com.youlai.boot.system.model.query.SwitchCabinetPageQuery;
import com.youlai.boot.system.model.vo.SwitchCabinetPageVO;
import com.youlai.boot.system.service.SwitchCabinetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 开关柜控制层
 *
* @author lijun
* @since 2026/04/23
 */
@Tag(name = "FQC开关柜排程管理")
@RestController
@RequestMapping("/api/v1/fqc/switch-cabinets")
@RequiredArgsConstructor
public class SwitchCabinetController {

    private final SwitchCabinetService switchCabinetService;

    @Operation(summary = "开关柜分页列表")
    @GetMapping("/page")
    @Log(value = "开关柜分页列表", module = LogModuleEnum.OTHER)
    public PageResult<SwitchCabinetPageVO> getSwitchCabinetPage(
            @Valid SwitchCabinetPageQuery queryParams
    ) {
        IPage<SwitchCabinetPageVO> result = switchCabinetService.getSwitchCabinetPage(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "新增开关柜")
    @PostMapping
    @PreAuthorize("@ss.hasPerm('fqc:switch-cabinet:add')")
    @RepeatSubmit
    @Log(value = "新增开关柜", module = LogModuleEnum.OTHER)
    public Result<?> saveSwitchCabinet(
            @RequestBody @Valid SwitchCabinetForm switchCabinetForm
    ) {
        boolean result = switchCabinetService.saveSwitchCabinet(switchCabinetForm);
        return Result.judge(result);
    }

    @Operation(summary = "开关柜指派员工")
    @PatchMapping("/{id}")
    @PreAuthorize("@ss.hasPerm('fqc:switch-cabinet:edit')")
    @Log(value = "开关柜指派员工", module = LogModuleEnum.OTHER)
    public Result<Void> updateSwitchCabinet(
            @Parameter(description = "主键ID") @PathVariable Long id,
            @RequestBody @Valid SwitchCabinetForm switchCabinetForm
    ) {
        boolean result = switchCabinetService.updateSwitchCabinet(id, switchCabinetForm);
        return Result.judge(result);
    }

    @Operation(summary = "删除开关柜")
    @DeleteMapping("/{ids}")
    @PreAuthorize("@ss.hasPerm('fqc:switch-cabinet:delete')")
    @Log(value = "删除开关柜", module = LogModuleEnum.OTHER)
    public Result<Void> deleteSwitchCabinets(
            @Parameter(description = "主键ID，多个以英文逗号(,)分割") @PathVariable String ids
    ) {
        boolean result = switchCabinetService.deleteSwitchCabinets(ids);
        return Result.judge(result);
    }

}
