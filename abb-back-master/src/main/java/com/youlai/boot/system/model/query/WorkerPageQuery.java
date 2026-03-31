package com.youlai.boot.system.model.query;

import cn.hutool.db.sql.Direction;
import com.youlai.boot.common.annotation.ValidField;
import com.youlai.boot.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 工人分页查询对象
 */
@Schema(description = "工人分页查询对象")
@Data
@EqualsAndHashCode(callSuper = false)
public class WorkerPageQuery extends BasePageQuery {

    @Schema(description = "工人ID")
    private String workerCode;

    @Schema(description = "关键字")
    private String keywords;

    @Schema(description = "性别，1-男，2-女")
    private Integer gender;

    @Schema(description = "熟练度/级别")
    private String skillLevel;

    @Schema(description = "排序的字段")
    @ValidField(allowedValues = {"create_time", "update_time"})
    private String field;

    @Schema(description = "排序方式（正序:ASC；反序:DESC）")
    private Direction direction;
}