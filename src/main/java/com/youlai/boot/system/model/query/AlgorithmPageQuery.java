package com.youlai.boot.system.model.query;

import cn.hutool.db.sql.Direction;
import com.youlai.boot.common.annotation.ValidField;
import com.youlai.boot.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;

/**
 * 算法分页查询对象
 */
@Schema(description = "算法分页查询对象")
@Data
@EqualsAndHashCode(callSuper = false)
public class AlgorithmPageQuery extends BasePageQuery {

    @Schema(description = "预计完工时间")
    private String estimatedFinishTime;

    @Schema(description = "调整次数")
    private Integer adjustCount;

    @Schema(description = "立体仓预警位")
    private String warehouseWarningFlag;

    @Schema(description = "壳体机器人利用率")
    private BigDecimal shellRobotUtilization;

    @Schema(description = "排序的字段")
    @ValidField(allowedValues = {"create_time", "update_time"})
    private String field;

    @Schema(description = "排序方式（正序:ASC；反序:DESC）")
    private Direction direction;
}
