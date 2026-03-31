package com.youlai.boot.system.converter;

import com.youlai.boot.system.model.entity.Token;
import com.youlai.boot.system.model.vo.ApiTokenVO;

import java.util.List;
import java.util.stream.Collectors;

/**
 * API Token对象转换器
 */
public class ApiTokenConverter {

    /**
     * 将Token实体转换为ApiTokenVO
     * @param token Token实体
     * @return ApiTokenVO
     */
    public static ApiTokenVO toApiVO(Token token) {
        if (token == null) {
            return null;
        }
        
        ApiTokenVO apiTokenVO = new ApiTokenVO();
        apiTokenVO.setId(token.getId());
        apiTokenVO.setTokenName(token.getTokenName());
        
        // 将tokenCode从字符串转换为Integer
        if (token.getTokenCode() != null && !token.getTokenCode().trim().isEmpty()) {
            try {
                apiTokenVO.setTokenCode(Integer.parseInt(token.getTokenCode()));
            } catch (NumberFormatException e) {
                // 如果转换失败，设置为null或处理异常
                apiTokenVO.setTokenCode(null);
            }
        }
        
        apiTokenVO.setDoorPreinstallTime(token.getDoorPreinstallTime());
        apiTokenVO.setDoorTime(token.getDoorTime());
        apiTokenVO.setGridPreinstallTime(token.getGridPreinstallTime());
        apiTokenVO.setGridTime(token.getGridTime());
        apiTokenVO.setTopPlateTime(token.getTopPlateTime());
        apiTokenVO.setBottomPlateTime(token.getBottomPlateTime());
        apiTokenVO.setShellAssemblyTime(token.getShellAssemblyTime());
        apiTokenVO.setTotalAssemblyTime(token.getTotalAssemblyTime());
        
        // 将LocalDateTime转换为时间戳（毫秒）
        if (token.getCreateTime() != null) {
            apiTokenVO.setCreateTime(token.getCreateTime().toEpochSecond(java.time.ZoneOffset.of("+8")) * 1000);
        }
        
        if (token.getUpdateTime() != null) {
            apiTokenVO.setUpdateTime(token.getUpdateTime().toEpochSecond(java.time.ZoneOffset.of("+8")) * 1000);
        }
        
        return apiTokenVO;
    }

    /**
     * 将Token实体列表转换为ApiTokenVO列表
     * @param tokens Token实体列表
     * @return ApiTokenVO列表
     */
    public static List<ApiTokenVO> toApiVOList(List<Token> tokens) {
        if (tokens == null) {
            return null;
        }
        return tokens.stream()
                .map(ApiTokenConverter::toApiVO)
                .collect(Collectors.toList());
    }
}