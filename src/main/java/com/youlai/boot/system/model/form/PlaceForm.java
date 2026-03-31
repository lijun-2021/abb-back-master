package com.youlai.boot.system.model.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 库所表单对象
 */
@Schema(description = "库所表单对象")
@Data
public class PlaceForm {

    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "库所名称")
    private String placeName;

    @Schema(description = "容量")
    private Integer capacity;

    @Schema(description = "前置变迁")
    private String preTransition;

    @Schema(description = "后置变迁")
    private String postTransition;

    @Schema(description = "库所类别")
    private String placeType;

    @Schema(description = "赋时库所标志位")
    private Boolean timedPlaceFlag;

    @Schema(description = "对应加工位ID")
    private String processStationId;

    @Schema(description = "阶段标志")
    private Integer stageFlag;

    @Schema(description = "库所颜色")
    private String placeColor;
}