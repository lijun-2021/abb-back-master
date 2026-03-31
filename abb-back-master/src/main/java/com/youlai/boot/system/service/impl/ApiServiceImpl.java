package com.youlai.boot.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.youlai.boot.core.web.Result;
import com.youlai.boot.system.converter.PlaceConverter;
import com.youlai.boot.system.converter.TokenConverter;
import com.youlai.boot.system.converter.TransitionConverter;
import com.youlai.boot.system.converter.ResourceStatusConverter;
import com.youlai.boot.system.converter.ApiPlaceConverter;
import com.youlai.boot.system.converter.ApiTokenConverter;
import com.youlai.boot.system.converter.ApiTransitionConverter;
import com.youlai.boot.system.model.entity.Place;
import com.youlai.boot.system.model.entity.Token;
import com.youlai.boot.system.model.entity.Transition;
import com.youlai.boot.system.model.entity.ResourceStatus;
import com.youlai.boot.system.model.vo.ApiPlaceVO;
import com.youlai.boot.system.model.vo.ApiTokenVO;
import com.youlai.boot.system.model.vo.ApiTransitionVO;
import com.youlai.boot.system.model.vo.ResourceStatusPageVO;
import com.youlai.boot.system.service.ApiService;
import com.youlai.boot.system.service.PlaceService;
import com.youlai.boot.system.service.TokenService;
import com.youlai.boot.system.service.TransitionService;
import com.youlai.boot.system.service.ResourceStatusService;
import com.youlai.boot.system.service.AbbProcessingResultService;
import com.youlai.boot.system.model.entity.AbbProcessingResult;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.util.UUID;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 对外API业务实现类
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ApiServiceImpl implements ApiService {

    private final PlaceService placeService;
    private final TokenService tokenService;
    private final TransitionService transitionService;
    private final ResourceStatusService resourceStatusService;
    private final AbbProcessingResultService abbProcessingResultService;
    
    private final PlaceConverter placeConverter;
    private final TokenConverter tokenConverter;
    private final TransitionConverter transitionConverter;
    private final ResourceStatusConverter resourceStatusConverter;

    /**
     * 获取所有库所数据
     *
     * @return {@link List<ApiPlaceVO>} 库所列表
     */
    @Override
    public List<ApiPlaceVO> getAllPlaces() {
        List<Place> places = placeService.lambdaQuery()
                .eq(Place::getIsDeleted, false)
                .list();
        return ApiPlaceConverter.toApiVOList(places);
    }

    /**
     * 获取所有Token数据
     *
     * @return {@link List<ApiTokenVO>} Token列表
     */
    @Override
    public List<ApiTokenVO> getAllTokens() {
        List<Token> tokens = tokenService.lambdaQuery()
                .eq(Token::getIsDeleted, false)
                .list();
        return ApiTokenConverter.toApiVOList(tokens);
    }

    /**
     * 获取所有变迁数据
     *
     * @return {@link List<ApiTransitionVO>} 变迁列表
     */
    @Override
    public List<ApiTransitionVO> getAllTransitions() {
        List<Transition> transitions = transitionService.lambdaQuery()
                .eq(Transition::getIsDeleted, false)
                .list();
        return ApiTransitionConverter.toApiVOList(transitions);
    }

    /**
     * 获取当日资源数据
     *
     * @return {@link List<ResourceStatusPageVO>} 资源状态列表
     */
    @Override
    public List<ResourceStatusPageVO> getDailyResources() {
        List<ResourceStatus> resources = resourceStatusService.lambdaQuery()
                .eq(ResourceStatus::getIsDeleted, false)
                .list();
        return resourceStatusConverter.toPageVoList(resources);
    }

    /**
     * 处理文件上传
     *
     * @param result 文件1
     * @param resultDetail 文件2
     * @return {@link String} 上传结果
     */
    @Override
    public String handleUpload(MultipartFile result, MultipartFile resultDetail) {
        try {
            // 生成UUID作为上传目录名
            String uuidDir = UUID.randomUUID().toString();
            
            // 创建上传目录（在tmp目录下）
            String uploadPath = System.getProperty("user.dir") + "/tmp/" + uuidDir;
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            // 保存文件1
            if (result != null && !result.isEmpty()) {
                String fileName1 = result.getOriginalFilename();
                File destFile1 = new File(uploadDir, fileName1);
                result.transferTo(destFile1);
                log.info("file1 saved to: {}", destFile1.getAbsolutePath());
            }

            // 保存文件2
            if (resultDetail != null && !resultDetail.isEmpty()) {
                String fileName2 = resultDetail.getOriginalFilename();
                File destFile2 = new File(uploadDir, fileName2);
                resultDetail.transferTo(destFile2);
                log.info("file2 saved to: {}", destFile2.getAbsolutePath());
            }

            // 处理普通字段
//            params.forEach((k, v) -> log.info("param: {} = {}", k, v));

            // 保存上传记录到数据库
            AbbProcessingResult processingResult = new AbbProcessingResult();
            processingResult.setUploadDir(uuidDir);
            processingResult.setCreateTime(LocalDateTime.now());
            processingResult.setIsDeleted(false);
            abbProcessingResultService.save(processingResult);

            return "上传成功，目录: " + uuidDir;
        } catch (Exception e) {
            log.error("文件上传失败", e);
            return "上传失败: " + e.getMessage();
        }
    }
}