package com.youlai.boot.system.model.query;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.youlai.boot.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 开关柜分页查询对象
 *
* @author lijun
* @since 2026/04/23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "开关柜分页查询对象")
public class SwitchCabinetPageQuery extends BasePageQuery {

    @Schema(description = "关键字(SN号/产线/区域)")
    private String keywords;

    @Schema(description = "开关柜SN号")
    private String snCode;

    @Schema(description = "产线")
    private String productionLine;

    @Schema(description = "检测区域")
    private String area;

    @Schema(description = "下线时间范围")
    private List<String> offlineTime;

//    /**
//     * 是否超级管理员（用于数据权限控制）
//     */
//    @JsonIgnore
//    @Schema(hidden = true)
//    private Boolean isRoot;
}
