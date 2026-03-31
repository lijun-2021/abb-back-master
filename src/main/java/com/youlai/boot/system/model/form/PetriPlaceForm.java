package com.youlai.boot.system.model.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Petri 库所表单对象
 */
@Schema(description = "Petri 库所表单对象")
@Data
public class PetriPlaceForm {

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

    @Schema(description = "是否为控制库所 (0-否 1-是)")
    private Boolean isControlPlace;

    @Schema(description = "是否为时间库所 (0-否 1-是)")
    private Boolean isTimePlace;

    @Schema(description = "熟练度")
    private Float proficiency;

    @Schema(description = "阶段")
    private Integer stage;

    @Schema(description = "颜色")
    private String color;
}

