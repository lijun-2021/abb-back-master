package com.youlai.boot.system.model.bo;

import lombok.Data;
import java.math.BigDecimal;

/**
 * 算法业务对象
 */
@Data
public class AlgorithmBO {

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
}
