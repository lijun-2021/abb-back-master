package com.youlai.boot.system.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * FQC算法模拟数据实体类
 * 对应数据库表：fqc_algorithm_simul
 *
 * @author lijun
 * @since 2026/07/15
 */
@TableName("fqc_algorithm_simul")
@Getter
@Setter
public class AlgorithmSimul {

    /**
     * 记录ID（主键，自增）
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 产线名称
     */
    private String line;

    /**
     * 工位行ID
     */
    private String rowId;

    /**
     * 电压区域
     */
    private String voltageZone;

    /**
     * 作业人员编号
     */
    private Integer worker;

    /**
     * 功能区域
     */
    private String functionZone;

    /**
     * 检验员编号
     */
    private String inspector;

    /**
     * 工序时长（分钟）
     */
    private Integer functionMinutes;
}