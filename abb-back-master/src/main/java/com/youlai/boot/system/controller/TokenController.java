package com.youlai.boot.system.controller;

import cn.idev.excel.EasyExcel;
import cn.idev.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.youlai.boot.common.annotation.Log;
import com.youlai.boot.common.annotation.RepeatSubmit;
import com.youlai.boot.common.enums.LogModuleEnum;
import com.youlai.boot.common.model.Option;
import com.youlai.boot.core.web.PageResult;
import com.youlai.boot.core.web.Result;
import com.youlai.boot.common.util.ExcelUtils;
import com.youlai.boot.core.web.ExcelResult;
import com.youlai.boot.system.listener.TokenImportListener;
import com.youlai.boot.system.model.dto.TokenImportDTO;
import com.youlai.boot.system.model.form.TokenForm;
import com.youlai.boot.system.model.query.TokenPageQuery;
import com.youlai.boot.system.model.vo.TokenPageVO;
import com.youlai.boot.system.service.TokenService;
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
 * Token控制层
 */
@Tag(name = "Token管理")
@RestController
@RequestMapping("/api/v1/token")
@RequiredArgsConstructor
public class TokenController {

    private final TokenService tokenService;

    @Operation(summary = "Token分页列表")
    @GetMapping("/page")
    @Log(value = "Token分页列表", module = LogModuleEnum.TOKEN)
    public PageResult<TokenPageVO> getTokenPage(
            @Valid TokenPageQuery queryParams
    ) {
        IPage<TokenPageVO> result = tokenService.getTokenPage(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "新增Token")
    @PostMapping
    @PreAuthorize("@ss.hasPerm('abb:token:add')")
    @RepeatSubmit
    @Log(value = "新增Token", module = LogModuleEnum.TOKEN)
    public Result<?> saveToken(
            @RequestBody @Valid TokenForm tokenForm
    ) {
        boolean result = tokenService.saveToken(tokenForm);
        return Result.judge(result);
    }

    @Operation(summary = "获取Token表单数据")
    @GetMapping("/{tokenId}/form")
    @PreAuthorize("@ss.hasPerm('abb:token:edit')")
    @Log(value = "Token表单数据", module = LogModuleEnum.TOKEN)
    public Result<TokenForm> getTokenForm(
            @Parameter(description = "TokenID") @PathVariable Long tokenId
    ) {
        TokenForm formData = tokenService.getTokenFormData(tokenId);
        return Result.success(formData);
    }

    @Operation(summary = "修改Token")
    @PutMapping(value = "/{tokenId}")
    @PreAuthorize("@ss.hasPerm('abb:token:edit')")
    @Log(value = "修改Token", module = LogModuleEnum.TOKEN)
    public Result<Void> updateToken(
            @Parameter(description = "TokenID") @PathVariable Long tokenId,
            @RequestBody @Valid TokenForm tokenForm
    ) {
        boolean result = tokenService.updateToken(tokenId, tokenForm);
        return Result.judge(result);
    }

    @Operation(summary = "批量删除Token")
    @DeleteMapping("/{ids}")
    @PreAuthorize("@ss.hasPerm('abb:token:delete')")
    @Log(value = "删除Token", module = LogModuleEnum.TOKEN)
    public Result<Void> deleteTokens(
            @Parameter(description = "TokenID，多个以英文逗号(,)分割") @PathVariable String ids
    ) {
        boolean result = tokenService.deleteTokens(ids);
        return Result.judge(result);
    }

    @Operation(summary = "获取Token下拉列表")
    @GetMapping("/options")
    public Result<List<Option<String>>> listTokenOptions() {
        List<Option<String>> list = tokenService.listTokenOptions();
        return Result.success(list);
    }

    @Operation(summary = "导入模板下载")
    @GetMapping("/template")
    @Log(value = "导入模板下载", module = LogModuleEnum.TOKEN)
    public void downloadTemplate(HttpServletResponse response)  {
        String fileName = "Token导入模板.xlsx";
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, StandardCharsets.UTF_8));

        String fileClassPath = "templates" + File.separator + "excel" + File.separator + fileName;
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(fileClassPath);

        try (ServletOutputStream outputStream = response.getOutputStream();
             ExcelWriter excelWriter = EasyExcel.write(outputStream).withTemplate(inputStream).build()) {
            excelWriter.finish();
        } catch (IOException e) {
            throw new RuntimeException("Token导入模板下载失败", e);
        }
    }

    @Operation(summary = "导入Token")
    @PostMapping("/import")
    @PreAuthorize("@ss.hasPerm('abb:token:import')")
    @Log(value = "导入Token", module = LogModuleEnum.TOKEN)
    public Result<ExcelResult> importTokens(MultipartFile file) throws IOException {
        TokenImportListener listener = new TokenImportListener();
        ExcelUtils.importExcel(file.getInputStream(), TokenImportDTO.class, listener);
        return Result.success(listener.getExcelResult());
    }
}