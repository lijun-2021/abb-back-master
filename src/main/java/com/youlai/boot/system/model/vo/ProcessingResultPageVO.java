package com.youlai.boot.system.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * C++算法处理结果分页视图对象
 */
@Schema(description = "C++算法处理结果分页对象")
@Data
public class ProcessingResultPageVO {

    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "存储上传文件目录")
    private String uploadDir;

    @Schema(description = "创建时间")
    private String createTime;

    @Schema(description = "更新时间")
    private String updateTime;

    @Schema(description = "是否删除（0: 未删除, 1: 已删除）")
    private Integer isDeleted;
}
