package com.youlai.boot.system.model.query;

import cn.hutool.db.sql.Direction;
import com.youlai.boot.common.annotation.ValidField;
import com.youlai.boot.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 实时调度分页查询对象
 */
@Schema(description = "实时调度分页查询对象")
@Data
@EqualsAndHashCode(callSuper = false)
public class RealtimeSchedulePageQuery extends BasePageQuery {

    @Schema(description = "故障编号")
    private String faultCode;

    @Schema(description = "故障类型")
    private String faultType;

    @Schema(description = "故障状态")
    private String faultStatus;

    @Schema(description = "超时工件ID")
    private String timeoutWorkpieceId;

    @Schema(description = "超时工人ID")
    private String timeoutWorkerId;

    @Schema(description = "排序的字段")
    @ValidField(allowedValues = {"create_time", "update_time"})
    private String field;

    @Schema(description = "排序方式（正序:ASC；反序:DESC）")
    private Direction direction;
}