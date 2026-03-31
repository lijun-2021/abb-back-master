package com.youlai.boot.system.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Petri 库所分页视图对象
 */
@Schema(description = "Petri 库所分页对象")
@Data
public class PetriPlacePageVO {

    @Schema(description = "主键 ID")
    private Integer id;

    @Schema(description = "库所名称")
    private String placeName;

    @Schema(description = "容量")
    private Integer capacity;

    @Schema(description = "前置弧（JSON 格式）")
    private String preArcs;

    @Schema(description = "后置弧（JSON 格式）")
    private String postArcs;

    @Schema(description = "是否为控制库所")
    private Boolean isControlPlace;

    @Schema(description = "是否为时间库所")
    private Boolean isTimePlace;

    @Schema(description = "熟练度")
    private Float proficiency;

    @Schema(description = "阶段")
    private Integer stage;

    @Schema(description = "颜色")
    private String color;
}

