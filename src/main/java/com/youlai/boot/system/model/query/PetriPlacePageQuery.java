package com.youlai.boot.system.model.query;

import cn.hutool.db.sql.Direction;
import com.youlai.boot.common.annotation.ValidField;
import com.youlai.boot.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Petri 库所分页查询对象
 */
@Schema(description = "Petri 库所分页查询对象")
@Data
@EqualsAndHashCode(callSuper = false)
public class PetriPlacePageQuery extends BasePageQuery {

    @Schema(description = "库所名称")
    private String placeName;

    @Schema(description = "关键字")
    private String keywords;

    @Schema(description = "是否为控制库所")
    private Boolean isControlPlace;

    @Schema(description = "是否为时间库所")
    private Boolean isTimePlace;

    @Schema(description = "阶段")
    private Integer stage;

    @Schema(description = "颜色")
    private String color;

    @Schema(description = "排序的字段")
    @ValidField(allowedValues = {"id", "place_name", "stage", "create_time", "update_time"})
    private String field;

    @Schema(description = "排序方式（正序:ASC；反序:DESC）")
    private Direction direction;
}
