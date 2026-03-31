package com.youlai.boot.system.model.query;

import cn.hutool.db.sql.Direction;
import com.youlai.boot.common.annotation.ValidField;
import com.youlai.boot.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * C++算法处理结果分页查询对象
 */
@Schema(description = "C++算法处理结果分页查询对象")
@Data
@EqualsAndHashCode(callSuper = false)
public class ProcessingResultPageQuery extends BasePageQuery {

    @Schema(description = "记录ID")
    private String uploadDir;

    @Schema(description = "排序的字段")
    @ValidField(allowedValues = {"create_time", "update_time"})
    private String field;

    @Schema(description = "排序方式（正序:ASC；反序:DESC）")
    private Direction direction;
}
