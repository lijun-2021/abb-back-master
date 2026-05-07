package com.youlai.boot.system.model.query;

import com.youlai.boot.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 开关柜数量展示分页查询对象
 *
 * @author lijun
 * @since 2026/04/28
 */
@EqualsAndHashCode(callSuper = true)
@Schema(description = "开关柜数量展示分页查询对象")
@Data
public class DisplayPageQuery extends BasePageQuery {

    @Schema(description = "关键字（可搜索所有数量字段）")
    private String keywords;

    @Schema(description = "创建时间-开始（格式：yyyy-MM-dd）")
    private String createTimeStart;

    @Schema(description = "创建时间-结束（格式：yyyy-MM-dd）")
    private String createTimeEnd;

    @Schema(description = "更新时间-开始（格式：yyyy-MM-dd）")
    private String updateTimeStart;

    @Schema(description = "更新时间-结束（格式：yyyy-MM-dd）")
    private String updateTimeEnd;
}
