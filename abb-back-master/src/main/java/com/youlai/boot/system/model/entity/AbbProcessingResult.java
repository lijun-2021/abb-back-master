package com.youlai.boot.system.model.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.youlai.boot.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * C++算法处理结果实体
 */
@TableName("abb_processing_result")
@Getter
@Setter
public class AbbProcessingResult extends BaseEntity {

    /**
     * 存储上传文件目录
     */
    private String uploadDir;

    /**
     * 是否删除(0-否 1-是)
     */
    @TableLogic
    private Boolean isDeleted;
}
