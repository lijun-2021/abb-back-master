package com.youlai.boot.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.youlai.boot.common.annotation.Log;
import com.youlai.boot.common.enums.LogModuleEnum;
import com.youlai.boot.core.web.PageResult;
import com.youlai.boot.core.web.Result;
import com.youlai.boot.system.model.query.ProcessingResultPageQuery;
import com.youlai.boot.system.model.vo.OutputDetailVO;
import com.youlai.boot.system.model.vo.ProcessingResultPageVO;
import com.youlai.boot.system.service.AbbProcessingResultService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * C++算法处理结果控制器
 */
@Tag(name = "C++算法处理结果")
@RestController
@RequestMapping("/api/v1/processing-result")
@RequiredArgsConstructor
@Slf4j
public class ProcessingResultController {

    private final AbbProcessingResultService processingResultService;

    @Operation(summary = "C++算法处理结果分页列表")
    @GetMapping("/page")
    @Log(value = "C++算法处理结果分页列表", module = LogModuleEnum.OTHER)
    public PageResult<ProcessingResultPageVO> getProcessingResultPage(
            @Valid ProcessingResultPageQuery queryParams
    ) {
        IPage<ProcessingResultPageVO> result = processingResultService.getProcessingResultPage(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "批量删除C++算法处理结果")
    @DeleteMapping("/{ids}")
    @PreAuthorize("@ss.hasPerm('abb:processingresult:delete')")
    @Log(value = "删除C++算法处理结果", module = LogModuleEnum.OTHER)
    public Result<Void> deleteProcessingResults(
            @Parameter(description = "C++算法处理结果ID字符串，多个以英文逗号(,)分割") @PathVariable String ids
    ) {
        boolean result = processingResultService.deleteProcessingResults(ids);
        return Result.judge(result);
    }

    @Operation(summary = "下载处理结果")
    @GetMapping("/downloadProcessResult")
    public void downloadProcessResult(
            @RequestParam String uploadDir,
            HttpServletResponse response) {

        File zipFile = processingResultService.downloadFiles(uploadDir);

        response.setContentType("application/octet-stream");
        response.setHeader(
                "Content-Disposition",
                "attachment; filename=" + URLEncoder.encode(zipFile.getName(), StandardCharsets.UTF_8)
        );
        log.info("zip size = {}", zipFile.length());
        response.setContentLengthLong(zipFile.length());

        try (InputStream in = new FileInputStream(zipFile);
            ServletOutputStream out = response.getOutputStream()) {

            byte[] buffer = new byte[8192];
            int len;
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            out.flush();

        } catch (IOException e) {
            throw new RuntimeException("下载处理结果失败", e);
        } finally {
            // 下载完成后删除临时zip文件
            if (zipFile.exists()) {
                boolean deleted = zipFile.delete();
                if (deleted) {
                    log.debug("临时文件 {} 已删除", zipFile.getAbsolutePath());
                } else {
                    log.warn("无法删除临时文件: {}", zipFile.getAbsolutePath());
                }
            }
        }
    }

    @Operation(summary = "解析output_detail.txt文件")
    @GetMapping("/gantt-data")
    public Result<List<OutputDetailVO>> ganttData(
            @RequestParam String uploadDir) {

        List<OutputDetailVO> result = processingResultService.ganttData(uploadDir);
        return Result.success(result);
    }
}
