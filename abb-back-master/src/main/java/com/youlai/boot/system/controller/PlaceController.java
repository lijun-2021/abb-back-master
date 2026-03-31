package com.youlai.boot.system.controller;

import cn.idev.excel.EasyExcel;
import cn.idev.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.youlai.boot.common.annotation.Log;
import com.youlai.boot.common.annotation.RepeatSubmit;
import com.youlai.boot.common.enums.LogModuleEnum;
import com.youlai.boot.common.model.Option;
import com.youlai.boot.common.util.ExcelUtils;
import com.youlai.boot.core.web.ExcelResult;
import com.youlai.boot.core.web.PageResult;
import com.youlai.boot.core.web.Result;
import com.youlai.boot.system.listener.PlaceImportListener;
import com.youlai.boot.system.model.dto.PlaceImportDTO;
import com.youlai.boot.system.model.form.PlaceForm;
import com.youlai.boot.system.model.query.PlacePageQuery;
import com.youlai.boot.system.model.vo.PlacePageVO;
import com.youlai.boot.system.service.PlaceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * 库所控制层
 */
@Tag(name = "库所接口")
@RestController
@RequestMapping("/api/v1/place")
@RequiredArgsConstructor
public class PlaceController {

    private final PlaceService placeService;

    @Operation(summary = "库所分页列表")
    @GetMapping("/page")
    @Log(value = "库所分页列表", module = LogModuleEnum.PLACE)
    public PageResult<PlacePageVO> getPlacePage(
            @Valid PlacePageQuery queryParams
    ) {
        IPage<PlacePageVO> result = placeService.getPlacePage(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "新增库所")
    @PostMapping
    @PreAuthorize("@ss.hasPerm('abb:place:add')")
    @RepeatSubmit
    @Log(value = "新增库所", module = LogModuleEnum.PLACE)
    public Result<?> savePlace(
            @RequestBody @Valid PlaceForm placeForm
    ) {
        boolean result = placeService.savePlace(placeForm);
        return Result.judge(result);
    }

    @Operation(summary = "获取库所表单数据")
    @GetMapping("/{placeId}/form")
    @PreAuthorize("@ss.hasPerm('abb:place:edit')")
    @Log(value = "库所表单数据", module = LogModuleEnum.PLACE)
    public Result<PlaceForm> getPlaceForm(
            @Parameter(description = "库所ID") @PathVariable Long placeId
    ) {
        PlaceForm formData = placeService.getPlaceFormData(placeId);
        return Result.success(formData);
    }

    @Operation(summary = "修改库所")
    @PutMapping(value = "/{placeId}")
    @PreAuthorize("@ss.hasPerm('abb:place:edit')")
    @Log(value = "修改库所", module = LogModuleEnum.PLACE)
    public Result<Void> updatePlace(
            @Parameter(description = "库所ID") @PathVariable Long placeId,
            @RequestBody @Valid PlaceForm placeForm
    ) {
        boolean result = placeService.updatePlace(placeId, placeForm);
        return Result.judge(result);
    }

    @Operation(summary = "批量删除库所")
    @DeleteMapping("/{ids}")
    @PreAuthorize("@ss.hasPerm('abb:place:delete')")
    @Log(value = "删除库所", module = LogModuleEnum.PLACE)
    public Result<Void> deletePlaces(
            @Parameter(description = "库所ID，多个以英文逗号(,)分割") @PathVariable String ids
    ) {
        boolean result = placeService.deletePlaces(ids);
        return Result.judge(result);
    }

    @Operation(summary = "获取库所下拉列表")
    @GetMapping("/options")
    public Result<List<Option<String>>> listPlaceOptions() {
        List<Option<String>> list = placeService.listPlaceOptions();
        return Result.success(list);
    }

    @Operation(summary = "库所导入模板下载")
    @GetMapping("/template")
    @Log(value = "库所导入模板下载", module = LogModuleEnum.USER)
    public void downloadTemplate(HttpServletResponse response)  {
        String fileName = "库所导入模板.xlsx";
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, StandardCharsets.UTF_8));

        String fileClassPath = "templates" + File.separator + "excel" + File.separator + fileName;
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(fileClassPath);

        try (ServletOutputStream outputStream = response.getOutputStream();
             ExcelWriter excelWriter = EasyExcel.write(outputStream).withTemplate(inputStream).build()) {
            excelWriter.finish();
        } catch (IOException e) {
            throw new RuntimeException("库所导入模板下载失败", e);
        }
    }

    @Operation(summary = "导入库所")
    @PostMapping("/import")
    @PreAuthorize("@ss.hasPerm('abb:place:import')")
    @Log(value = "导入库所", module = LogModuleEnum.PLACE)
    public Result<ExcelResult> importPlaces(MultipartFile file) throws IOException {
        PlaceImportListener listener = new PlaceImportListener();
        ExcelUtils.importExcel(file.getInputStream(), PlaceImportDTO.class, listener);
        return Result.success(listener.getExcelResult());
    }
}