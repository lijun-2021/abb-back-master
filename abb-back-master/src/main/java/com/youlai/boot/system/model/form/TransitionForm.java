package com.youlai.boot.system.model.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 变迁表单对象
 */
@Schema(description = "变迁表单对象")
@Data
public class TransitionForm {

    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "变迁名称")
    private String transitionName;

    @Schema(description = "前置库所")
    private String prePlace;

    @Schema(description = "后置库所")
    private String postPlace;

    @Schema(description = "place_check标志位")
    private Boolean placeCheckFlag;

    @Schema(description = "place_lock标志位")
    private Boolean placeLockFlag;
}