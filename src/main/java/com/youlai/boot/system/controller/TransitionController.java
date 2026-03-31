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
import com.youlai.boot.system.listener.TransitionImportListener;
import com.youlai.boot.system.model.dto.TransitionImportDTO;
import com.youlai.boot.system.model.form.TransitionForm;
import com.youlai.boot.system.model.query.TransitionPageQuery;
import com.youlai.boot.system.model.vo.TransitionPageVO;
import com.youlai.boot.system.service.TransitionService;
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
 * 变迁控制层
 */
@Tag(name = "变迁数据")
@RestController
@RequestMapping("/api/v1/transition")
@RequiredArgsConstructor
public class TransitionController {

    private final TransitionService transitionService;

    @Operation(summary = "变迁分页列表")
    @GetMapping("/page")
    @Log(value = "变迁分页列表", module = LogModuleEnum.TRANSITION)
    public PageResult<TransitionPageVO> getTransitionPage(
            @Valid TransitionPageQuery queryParams
    ) {
        IPage<TransitionPageVO> result = transitionService.getTransitionPage(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "新增变迁")
    @PostMapping
    @PreAuthorize("@ss.hasPerm('abb:transition:add')")
    @RepeatSubmit
    @Log(value = "新增变迁", module = LogModuleEnum.TRANSITION)
    public Result<?> saveTransition(
            @RequestBody @Valid TransitionForm transitionForm
    ) {
        boolean result = transitionService.saveTransition(transitionForm);
        return Result.judge(result);
    }

    @Operation(summary = "获取变迁表单数据")
    @GetMapping("/{transitionId}/form")
    @PreAuthorize("@ss.hasPerm('abb:transition:edit')")
    @Log(value = "变迁表单数据", module = LogModuleEnum.TRANSITION)
    public Result<TransitionForm> getTransitionForm(
            @Parameter(description = "变迁ID") @PathVariable Long transitionId
    ) {
        TransitionForm formData = transitionService.getTransitionFormData(transitionId);
        return Result.success(formData);
    }

    @Operation(summary = "修改变迁")
    @PutMapping(value = "/{transitionId}")
    @PreAuthorize("@ss.hasPerm('abb:transition:edit')")
    @Log(value = "修改变迁", module = LogModuleEnum.TRANSITION)
    public Result<Void> updateTransition(
            @Parameter(description = "变迁ID") @PathVariable Long transitionId,
            @RequestBody @Valid TransitionForm transitionForm
    ) {
        boolean result = transitionService.updateTransition(transitionId, transitionForm);
        return Result.judge(result);
    }

    @Operation(summary = "批量删除变迁")
    @DeleteMapping("/{ids}")
    @PreAuthorize("@ss.hasPerm('abb:transition:delete')")
    @Log(value = "删除变迁", module = LogModuleEnum.TRANSITION)
    public Result<Void> deleteTransitions(
            @Parameter(description = "变迁ID，多个以英文逗号(,)分割") @PathVariable String ids
    ) {
        boolean result = transitionService.deleteTransitions(ids);
        return Result.judge(result);
    }

    @Operation(summary = "获取变迁下拉列表")
    @GetMapping("/options")
    public Result<List<Option<String>>> listTransitionOptions() {
        List<Option<String>> list = transitionService.listTransitionOptions();
        return Result.success(list);
    }

    @Operation(summary = "变迁数据导入模板下载")
    @GetMapping("/template")
    @Log(value = "变迁数据导入模板下载", module = LogModuleEnum.TRANSITION)
    public void downloadTemplate(HttpServletResponse response)  {
        String fileName = "变迁数据导入模板.xlsx";
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, StandardCharsets.UTF_8));

        String fileClassPath = "templates" + File.separator + "excel" + File.separator + fileName;
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(fileClassPath);

        try (ServletOutputStream outputStream = response.getOutputStream();
             ExcelWriter excelWriter = EasyExcel.write(outputStream).withTemplate(inputStream).build()) {
            excelWriter.finish();
        } catch (IOException e) {
            throw new RuntimeException("变迁数据导入模板下载失败", e);
        }
    }

    @Operation(summary = "导入变迁数据")
    @PostMapping("/import")
    @PreAuthorize("@ss.hasPerm('abb:transition:import')")
    @Log(value = "导入变迁数据", module = LogModuleEnum.TRANSITION)
    public Result<ExcelResult> importTransitions(MultipartFile file) throws IOException {
        TransitionImportListener listener = new TransitionImportListener();
        ExcelUtils.importExcel(file.getInputStream(), TransitionImportDTO.class, listener);
        return Result.success(listener.getExcelResult());
    }
}