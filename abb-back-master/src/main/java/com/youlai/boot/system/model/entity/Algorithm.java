package com.youlai.boot.system.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.youlai.boot.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

/**
 * 算法数据实体
 */
@TableName("abb_algorithm_result")
@Getter
@Setter
public class Algorithm extends BaseEntity {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 预计完工时间
     */
    private String estimatedFinishTime;

    /**
     * 调整次数
     */
    private Integer adjustCount;

    /**
     * 立体仓预警位
     */
    private String warehouseWarningFlag;

    /**
     * 壳体机器人利用率
     */
    private BigDecimal shellRobotUtilization;

    /**
     * 创建人 ID
     */
    private Long createBy;

    /**
     * 更新人 ID
     */
    private Long updateBy;

    /**
     * 是否删除(0-否 1-是)
     */
    private Integer isDeleted;
}
