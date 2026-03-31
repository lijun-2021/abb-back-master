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
import com.youlai.boot.system.listener.ResourceStatusImportListener;
import com.youlai.boot.system.model.dto.ResourceStatusImportDTO;
import com.youlai.boot.system.model.form.ResourceStatusForm;
import com.youlai.boot.system.model.query.ResourceStatusPageQuery;
import com.youlai.boot.system.model.vo.ResourceStatusPageVO;
import com.youlai.boot.system.service.ResourceStatusService;
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
 * 当日资源/状态控制层
 */
@Tag(name = "当日资源/状态数据")
@RestController
@RequestMapping("/api/v1/resourcestatus")
@RequiredArgsConstructor
public class ResourceStatusController {

    private final ResourceStatusService resourceStatusService;

    @Operation(summary = "当日资源/状态分页列表")
    @GetMapping("/page")
    @Log(value = "当日资源/状态分页列表", module = LogModuleEnum.RESOURCE_STATUS)
    public PageResult<ResourceStatusPageVO> getResourceStatusPage(
            @Valid ResourceStatusPageQuery queryParams
    ) {
        IPage<ResourceStatusPageVO> result = resourceStatusService.getResourceStatusPage(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "新增当日资源/状态")
    @PostMapping
    @PreAuthorize("@ss.hasPerm('abb:resourcestatus:add')")
    @RepeatSubmit
    @Log(value = "新增当日资源/状态", module = LogModuleEnum.RESOURCE_STATUS)
    public Result<?> saveResourceStatus(
            @RequestBody @Valid ResourceStatusForm resourceStatusForm
    ) {
        boolean result = resourceStatusService.saveResourceStatus(resourceStatusForm);
        return Result.judge(result);
    }

    @Operation(summary = "获取当日资源/状态表单数据")
    @GetMapping("/{resourceStatusId}/form")
    @PreAuthorize("@ss.hasPerm('abb:resourcestatus:edit')")
    @Log(value = "当日资源/状态表单数据", module = LogModuleEnum.RESOURCE_STATUS)
    public Result<ResourceStatusForm> getResourceStatusForm(
            @Parameter(description = "当日资源/状态ID") @PathVariable Long resourceStatusId
    ) {
        ResourceStatusForm formData = resourceStatusService.getResourceStatusFormData(resourceStatusId);
        return Result.success(formData);
    }

    @Operation(summary = "修改当日资源/状态")
    @PutMapping(value = "/{resourceStatusId}")
    @PreAuthorize("@ss.hasPerm('abb:resourcestatus:edit')")
    @Log(value = "修改当日资源/状态", module = LogModuleEnum.RESOURCE_STATUS)
    public Result<Void> updateResourceStatus(
            @Parameter(description = "当日资源/状态ID") @PathVariable Long resourceStatusId,
            @RequestBody @Valid ResourceStatusForm resourceStatusForm
    ) {
        boolean result = resourceStatusService.updateResourceStatus(resourceStatusId, resourceStatusForm);
        return Result.judge(result);
    }

    @Operation(summary = "批量删除当日资源/状态")
    @DeleteMapping("/{ids}")
    @PreAuthorize("@ss.hasPerm('abb:resourcestatus:delete')")
    @Log(value = "删除当日资源/状态", module = LogModuleEnum.RESOURCE_STATUS)
    public Result<Void> deleteResourceStatuses(
            @Parameter(description = "当日资源/状态ID，多个以英文逗号(,)分割") @PathVariable String ids
    ) {
        boolean result = resourceStatusService.deleteResourceStatuses(ids);
        return Result.judge(result);
    }

    @Operation(summary = "获取当日资源/状态下拉列表")
    @GetMapping("/options")
    public Result<List<Option<String>>> listResourceStatusOptions() {
        List<Option<String>> list = resourceStatusService.listResourceStatusOptions();
        return Result.success(list);
    }

    @Operation(summary = "当日资源状态导入模板下载")
    @GetMapping("/template")
    @Log(value = "当日资源状态导入模板下载", module = LogModuleEnum.RESOURCE_STATUS)
    public void downloadTemplate(HttpServletResponse response)  {
        String fileName = "当日资源状态导入模板.xlsx";
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, StandardCharsets.UTF_8));

        String fileClassPath = "templates" + File.separator + "excel" + File.separator + fileName;
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(fileClassPath);

        try (ServletOutputStream outputStream = response.getOutputStream();
             ExcelWriter excelWriter = EasyExcel.write(outputStream).withTemplate(inputStream).build()) {
            excelWriter.finish();
        } catch (IOException e) {
            throw new RuntimeException("当日资源状态导入模板下载失败", e);
        }
    }

    @Operation(summary = "导入当日资源状态")
    @PostMapping("/import")
    @PreAuthorize("@ss.hasPerm('abb:resourcestatus:import')")
    @Log(value = "导入当日资源状态", module = LogModuleEnum.RESOURCE_STATUS)
    public Result<ExcelResult> importResourceStatuses(MultipartFile file) throws IOException {
        ResourceStatusImportListener listener = new ResourceStatusImportListener();
        ExcelUtils.importExcel(file.getInputStream(), ResourceStatusImportDTO.class, listener);
        return Result.success(listener.getExcelResult());
    }
}