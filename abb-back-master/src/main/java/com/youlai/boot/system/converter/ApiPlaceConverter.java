package com.youlai.boot.system.converter;

import com.youlai.boot.system.model.entity.Place;
import com.youlai.boot.system.model.vo.ApiPlaceVO;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * API库所对象转换器
 */
public class ApiPlaceConverter {

    /**
     * 将Place实体转换为ApiPlaceVO
     * @param place Place实体
     * @return ApiPlaceVO
     */
    public static ApiPlaceVO toApiVO(Place place) {
        if (place == null) {
            return null;
        }
        
        ApiPlaceVO apiPlaceVO = new ApiPlaceVO();
        apiPlaceVO.setId(place.getId());
        apiPlaceVO.setPlaceName(place.getPlaceName());
        apiPlaceVO.setCapacity(place.getCapacity());
        
        // 将前置变迁字符串转换为数组
        if (place.getPreTransition() != null && !place.getPreTransition().trim().isEmpty()) {
            List<String> preTransitions = Arrays.stream(place.getPreTransition().split("\\s*,\\s*"))
                    .collect(Collectors.toList());
            apiPlaceVO.setPreTransition(preTransitions);
        }
        
        // 将后置变迁字符串转换为数组
        if (place.getPostTransition() != null && !place.getPostTransition().trim().isEmpty()) {
            List<String> postTransitions = Arrays.stream(place.getPostTransition().split("\\s*,\\s*"))
                    .collect(Collectors.toList());
            apiPlaceVO.setPostTransition(postTransitions);
        }
        
        apiPlaceVO.setPlaceType(place.getPlaceType());
        apiPlaceVO.setTimedPlaceFlag(place.getTimedPlaceFlag());
        
        // 将processStationId从字符串转换为Double，确保更高精度
        if (place.getProcessStationId() != null && !place.getProcessStationId().trim().isEmpty()) {
            try {
                apiPlaceVO.setProcessStationId(Double.parseDouble(place.getProcessStationId()));
            } catch (NumberFormatException e) {
                // 如果转换失败，设置为null或处理异常
                apiPlaceVO.setProcessStationId(null);
            }
        }
        
        apiPlaceVO.setStageFlag(place.getStageFlag());
        apiPlaceVO.setPlaceColor(place.getPlaceColor());
        
        return apiPlaceVO;
    }

    /**
     * 将Place实体列表转换为ApiPlaceVO列表
     * @param places Place实体列表
     * @return ApiPlaceVO列表
     */
    public static List<ApiPlaceVO> toApiVOList(List<Place> places) {
        if (places == null) {
            return null;
        }
        return places.stream()
                .map(ApiPlaceConverter::toApiVO)
                .collect(Collectors.toList());
    }
}