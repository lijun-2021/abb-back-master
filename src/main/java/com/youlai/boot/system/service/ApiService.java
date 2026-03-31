package com.youlai.boot.system.service;

import com.youlai.boot.system.model.vo.ApiPlaceVO;
import com.youlai.boot.system.model.vo.ApiTokenVO;
import com.youlai.boot.system.model.vo.ApiTransitionVO;
import com.youlai.boot.system.model.vo.ResourceStatusPageVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 对外API业务接口
 */
public interface ApiService {

    /**
     * 获取所有库所数据
     *
     * @return {@link List<ApiPlaceVO>} 库所列表
     */
    List<ApiPlaceVO> getAllPlaces();

    /**
     * 获取所有Token数据
     *
     * @return {@link List<ApiTokenVO>} Token列表
     */
    List<ApiTokenVO> getAllTokens();

    /**
     * 获取所有变迁数据
     *
     * @return {@link List<ApiTransitionVO>} 变迁列表
     */
    List<ApiTransitionVO> getAllTransitions();

    /**
     * 获取当日资源数据
     *
     * @return {@link List<ResourceStatusPageVO>} 资源状态列表
     */
    List<ResourceStatusPageVO> getDailyResources();

    /**
     * 处理文件上传
     *
     * @param result 文件1
     * @param resultDetail 文件2
     * @return {@link String} 上传结果
     */
    String handleUpload(MultipartFile result, MultipartFile resultDetail);
}