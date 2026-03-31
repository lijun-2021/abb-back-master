package com.youlai.boot.system.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * API返回的变迁视图对象
 */
@Schema(description = "API返回的变迁视图对象")
@Data
public class ApiTransitionVO {

    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "变迁名称")
    private String transitionName;

    @Schema(description = "前置库所")
    private List<String> prePlace;

    @Schema(description = "后置库所")
    private List<String> postPlace;

    @Schema(description = "place_check标志位")
    private Boolean placeCheckFlag;

    @Schema(description = "place_lock标志位")
    private Boolean placeLockFlag;
}