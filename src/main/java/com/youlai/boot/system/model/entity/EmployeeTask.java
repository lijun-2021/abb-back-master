package com.youlai.boot.system.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.youlai.boot.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * 员工任务分配实体
 *
 * @author lijun
 * @since 2026/04/23
 */
@TableName("fqc_employee_task")
@Getter
@Setter
public class EmployeeTask extends BaseEntity {

    /**
     * 员工ID
     */
    private String empId;

    /**
     * 组别(A/B/C/D)
     */
    private String empTeam;

    /**
     * 员工姓名
     */
    private String empName;

    /**
     * 任务类型 1-耐压 2-功能
     */
    private Integer taskType;

    /**
     * 分配SN序号1-20
     */
    private String snCode1;
    private String snCode2;
    private String snCode3;
    private String snCode4;
    private String snCode5;
    private String snCode6;
    private String snCode7;
    private String snCode8;
    private String snCode9;
    private String snCode10;
    private String snCode11;
    private String snCode12;
    private String snCode13;
    private String snCode14;
    private String snCode15;
    private String snCode16;
    private String snCode17;
    private String snCode18;
    private String snCode19;
    private String snCode20;


    /**
     * 是否删除 0-未删除 1-已删除
     */
    private Integer isDeleted;
}
