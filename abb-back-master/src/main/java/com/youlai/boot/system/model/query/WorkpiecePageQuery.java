package com.youlai.boot.system.model.query;

import cn.hutool.db.sql.Direction;
import com.youlai.boot.common.base.BasePageQuery;
import com.youlai.boot.common.annotation.ValidField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 工件分页查询对象
 */
@Schema(description = "工件分页查询对象")
@Data
@EqualsAndHashCode(callSuper = false)
public class WorkpiecePageQuery extends BasePageQuery {

    @Schema(description = "SN")
    private String sn;

    @Schema(description = "关键字")
    private String keywords;

    @Schema(description = "编号")
    private String code;

    @Schema(description = "型号")
    private String model;

    @Schema(description = "当前状态")
    private String currentStatus;

    @Schema(description = "排序的字段")
    @ValidField(allowedValues = {"create_time", "update_time"})
    private String field;

    @Schema(description = "排序方式（正序:ASC；反序:DESC）")
    private Direction direction;
}