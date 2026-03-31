package com.youlai.boot.system.model.query;

import cn.hutool.db.sql.Direction;
import com.youlai.boot.common.annotation.ValidField;
import com.youlai.boot.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 当日资源/状态分页查询对象
 */
@Schema(description = "当日资源/状态分页查询对象")
@Data
@EqualsAndHashCode(callSuper = false)
public class ResourceStatusPageQuery extends BasePageQuery {

    @Schema(description = "加工位ID")
    private String processStationId;

    @Schema(description = "关键字")
    private String keywords;

    @Schema(description = "加工位状态")
    private String processStationStatus;

    @Schema(description = "立库ID")
    private String warehouseId;

    @Schema(description = "排序的字段")
    @ValidField(allowedValues = {"create_time", "update_time"})
    private String field;

    @Schema(description = "排序方式（正序:ASC；反序:DESC）")
    private Direction direction;
}