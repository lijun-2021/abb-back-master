package com.youlai.boot.system.model.bo;

import lombok.Data;

/**
 * C++算法处理结果业务对象
 */
@Data
public class ProcessingResultBO {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 存储上传文件目录
     */
    private String uploadDir;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;

    /**
     * 是否删除（0: 未删除, 1: 已删除）
     */
    private Integer isDeleted;
}
