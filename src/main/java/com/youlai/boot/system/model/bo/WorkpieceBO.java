package com.youlai.boot.system.model.bo;

import lombok.Data;

/**
 * 工件业务对象
 */
@Data
public class WorkpieceBO {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * SN
     */
    private String sn;

    /**
     * 编号
     */
    private String code;

    /**
     * 型号
     */
    private String model;

    /**
     * 线束根数
     */
    private Integer harnessCount;

    /**
     * 线束类型
     */
    private String harnessType;

    /**
     * 端子类型
     */
    private String terminalType;

    /**
     * 长度
     */
    private String length;

    /**
     * 宽度
     */
    private String width;

    /**
     * 高度
     */
    private String height;

    /**
     * 加工流程
     */
    private String processFlow;

    /**
     * 当前状态
     */
    private String currentStatus;
}