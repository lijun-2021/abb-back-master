package com.youlai.boot.system.model.vo;

import lombok.Data;

/**
 * output_detail.txt解析结果VO
 */
@Data
public class OutputDetailVO {

    /**
     * 当前加工时间
     */
    private Integer processTime;

    /**
     * 工件ID
     */
    private Integer workpieceId;

    /**
     * 激发变迁
     */
    private String transition;
}
