package com.youlai.boot.system.converter;

import com.youlai.boot.system.model.entity.Transition;
import com.youlai.boot.system.model.vo.ApiTransitionVO;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * API变迁对象转换器
 */
public class ApiTransitionConverter {

    /**
     * 将Transition实体转换为ApiTransitionVO
     * @param transition Transition实体
     * @return ApiTransitionVO
     */
    public static ApiTransitionVO toApiVO(Transition transition) {
        if (transition == null) {
            return null;
        }
        
        ApiTransitionVO apiTransitionVO = new ApiTransitionVO();
        apiTransitionVO.setId(transition.getId());
        apiTransitionVO.setTransitionName(transition.getTransitionName());
        
        // 将前置库所字符串转换为数组
        if (transition.getPrePlace() != null && !transition.getPrePlace().trim().isEmpty()) {
            List<String> prePlaces = Arrays.stream(transition.getPrePlace().split("\\s*,\\s*"))
                    .collect(Collectors.toList());
            apiTransitionVO.setPrePlace(prePlaces);
        }
        
        // 将后置库所字符串转换为数组
        if (transition.getPostPlace() != null && !transition.getPostPlace().trim().isEmpty()) {
            List<String> postPlaces = Arrays.stream(transition.getPostPlace().split("\\s*,\\s*"))
                    .collect(Collectors.toList());
            apiTransitionVO.setPostPlace(postPlaces);
        }
        
        apiTransitionVO.setPlaceCheckFlag(transition.getPlaceCheckFlag());
        apiTransitionVO.setPlaceLockFlag(transition.getPlaceLockFlag());
        
        return apiTransitionVO;
    }

    /**
     * 将Transition实体列表转换为ApiTransitionVO列表
     * @param transitions Transition实体列表
     * @return ApiTransitionVO列表
     */
    public static List<ApiTransitionVO> toApiVOList(List<Transition> transitions) {
        if (transitions == null) {
            return null;
        }
        return transitions.stream()
                .map(ApiTransitionConverter::toApiVO)
                .collect(Collectors.toList());
    }
}