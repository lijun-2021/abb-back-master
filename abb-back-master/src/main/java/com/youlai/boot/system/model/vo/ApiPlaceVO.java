package com.youlai.boot.system.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * API返回的库所视图对象
 */
@Schema(description = "API返回的库所视图对象")
@Data
public class ApiPlaceVO {

    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "库所名称")
    private String placeName;

    @Schema(description = "容量")
    private Integer capacity;

    @Schema(description = "前置变迁")
    private List<String> preTransition;

    @Schema(description = "后置变迁")
    private List<String> postTransition;

    @Schema(description = "库所类别")
    private String placeType;

    @Schema(description = "赋时库所标志位")
    private Boolean timedPlaceFlag;

    @Schema(description = "对应加工位ID")
    private Double processStationId;

    @Schema(description = "阶段标志")
    private Integer stageFlag;

    @Schema(description = "库所颜色")
    private String placeColor;

    /**
     * 设置前置变迁字符串，转换为数组
     * @param preTransitionStr 逗号分隔的前置变迁字符串
     */
    public void setPreTransitionStr(String preTransitionStr) {
        if (preTransitionStr != null && !preTransitionStr.trim().isEmpty()) {
            this.preTransition = Arrays.stream(preTransitionStr.split("\\s*,\\s*"))
                    .collect(Collectors.toList());
        }
    }

    /**
     * 设置后置变迁字符串，转换为数组
     * @param postTransitionStr 逗号分隔的后置变迁字符串
     */
    public void setPostTransitionStr(String postTransitionStr) {
        if (postTransitionStr != null && !postTransitionStr.trim().isEmpty()) {
            this.postTransition = Arrays.stream(postTransitionStr.split("\\s*,\\s*"))
                    .collect(Collectors.toList());
        }
    }
}