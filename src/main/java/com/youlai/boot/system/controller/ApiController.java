package com.youlai.boot.system.controller;

import com.youlai.boot.core.web.Result;
import com.youlai.boot.system.model.vo.ApiPlaceVO;
import com.youlai.boot.system.model.vo.ApiTokenVO;
import com.youlai.boot.system.model.vo.ApiTransitionVO;
import com.youlai.boot.system.model.vo.ResourceStatusPageVO;
import com.youlai.boot.system.service.ApiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.MalformedURLException;
import java.util.List;

/**
 * 对外API接口控制器
 */
@Tag(name = "对外API接口")
@RestController
@RequestMapping("/api/v1/public")
@RequiredArgsConstructor
public class ApiController {

    private final ApiService apiService;

    @Operation(summary = "获取所有库所数据")
    @GetMapping("/places")
    public Result<List<ApiPlaceVO>> getAllPlaces() {
        List<ApiPlaceVO> apiPlaceVOs = apiService.getAllPlaces();
        return Result.success(apiPlaceVOs);
    }

    @Operation(summary = "获取所有Token数据")
    @GetMapping("/tokens")
    public Result<List<ApiTokenVO>> getAllTokens() {
        List<ApiTokenVO> apiTokenVOs = apiService.getAllTokens();
        return Result.success(apiTokenVOs);
    }

    @Operation(summary = "获取所有变迁数据")
    @GetMapping("/transitions")
    public Result<List<ApiTransitionVO>> getAllTransitions() {
        List<ApiTransitionVO> apiTransitionVOs = apiService.getAllTransitions();
        return Result.success(apiTransitionVOs);
    }

    @Operation(summary = "获取当日资源数据")
    @GetMapping("/daily-resources")
    public Result<List<ResourceStatusPageVO>> getDailyResources() {
        List<ResourceStatusPageVO> resourceStatusPageVOs = apiService.getDailyResources();
        return Result.success(resourceStatusPageVOs);
    }

    @Operation(summary = "上传处理结果")
    @PostMapping("/uploadProcessResult")
    public Result<String> handleUpload(
            @RequestPart(value = "result", required = false) MultipartFile result,
            @RequestPart(value = "result_detail", required = false) MultipartFile resultDetail
    ) {
        String responseResult = apiService.handleUpload(result, resultDetail);
        return Result.success(responseResult);
    }
}