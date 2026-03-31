package com.youlai.boot.system.model.query;

import cn.hutool.db.sql.Direction;
import com.youlai.boot.common.annotation.ValidField;
import com.youlai.boot.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 变迁分页查询对象
 */
@Schema(description = "变迁分页查询对象")
@Data
@EqualsAndHashCode(callSuper = false)
public class TransitionPageQuery extends BasePageQuery {

    @Schema(description = "变迁名称")
    private String transitionName;

    @Schema(description = "前置库所")
    private String prePlace;

    @Schema(description = "后置库所")
    private String postPlace;

    @Schema(description = "排序的字段")
    @ValidField(allowedValues = {"create_time", "update_time"})
    private String field;

    @Schema(description = "排序方式（正序:ASC；反序:DESC）")
    private Direction direction;
}