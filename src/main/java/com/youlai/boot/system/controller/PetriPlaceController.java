package com.youlai.boot.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.youlai.boot.common.annotation.Log;
import com.youlai.boot.common.annotation.RepeatSubmit;
import com.youlai.boot.common.enums.LogModuleEnum;
import com.youlai.boot.common.model.Option;
import com.youlai.boot.core.web.PageResult;
import com.youlai.boot.core.web.Result;
import com.youlai.boot.system.model.form.PetriPlaceForm;
import com.youlai.boot.system.model.query.PetriPlacePageQuery;
import com.youlai.boot.system.model.vo.PetriPlacePageVO;
import com.youlai.boot.system.service.IPetriPlaceService;
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
 * Petri 库所控制层
 */
@Tag(name = "Petri 库所接口")
@RestController
@RequestMapping("/api/v1/petri-place")
@RequiredArgsConstructor
public class PetriPlaceController {

    private final IPetriPlaceService petriPlaceService;

    @Operation(summary = "Petri 库所分页列表")
    @GetMapping("/page")
    @Log(value = "Petri 库所分页列表", module = LogModuleEnum.PLACE)
    public PageResult<PetriPlacePageVO> getPetriPlacePage(
            @Valid PetriPlacePageQuery queryParams
    ) {
        IPage<PetriPlacePageVO> result = petriPlaceService.getPetriPlacePage(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "新增 Petri 库所")
    @PostMapping
    @PreAuthorize("@ss.hasPerm('abb:petri:place:add')")
    @RepeatSubmit
    @Log(value = "新增 Petri 库所", module = LogModuleEnum.PLACE)
    public Result<?> savePetriPlace(
            @RequestBody @Valid PetriPlaceForm placeForm
    ) {
        boolean result = petriPlaceService.savePetriPlace(placeForm);
        return Result.judge(result);
    }

    @Operation(summary = "获取 Petri 库所表单数据")
    @GetMapping("/{placeId}/form")
    @PreAuthorize("@ss.hasPerm('abb:petri:place:edit')")
    @Log(value = "Petri 库所表单数据", module = LogModuleEnum.PLACE)
    public Result<PetriPlaceForm> getPetriPlaceForm(
            @Parameter(description = "库所 ID") @PathVariable Long placeId
    ) {
        PetriPlaceForm formData = petriPlaceService.getPetriPlaceFormData(placeId);
        return Result.success(formData);
    }

    @Operation(summary = "修改 Petri 库所")
    @PutMapping(value = "/{placeId}")
    @PreAuthorize("@ss.hasPerm('abb:petri:place:edit')")
    @Log(value = "修改 Petri 库所", module = LogModuleEnum.PLACE)
    public Result<Void> updatePetriPlace(
            @Parameter(description = "库所 ID") @PathVariable Long placeId,
            @RequestBody @Valid PetriPlaceForm placeForm
    ) {
        boolean result = petriPlaceService.updatePetriPlace(placeId, placeForm);
        return Result.judge(result);
    }

    @Operation(summary = "批量删除 Petri 库所")
    @DeleteMapping("/{ids}")
    @PreAuthorize("@ss.hasPerm('abb:petri:place:delete')")
    @Log(value = "删除 Petri 库所", module = LogModuleEnum.PLACE)
    public Result<Void> deletePetriPlaces(
            @Parameter(description = "库所 ID，多个以英文逗号 (,) 分割") @PathVariable String ids
    ) {
        boolean result = petriPlaceService.deletePetriPlaces(ids);
        return Result.judge(result);
    }

    @Operation(summary = "获取 Petri 库所下拉列表")
    @GetMapping("/options")
    public Result<List<Option<String>>> listPetriPlaceOptions() {
        List<Option<String>> list = petriPlaceService.listPetriPlaceOptions();
        return Result.success(list);
    }
}

