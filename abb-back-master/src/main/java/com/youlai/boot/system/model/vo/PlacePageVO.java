package com.youlai.boot.system.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 库所分页视图对象
 */
@Schema(description = "库所分页对象")
@Data
public class PlacePageVO {

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