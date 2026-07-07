package com.youlai.boot.system.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 员工考勤记录实体
 *
 * @author lijun
 * @since 2026/07/06
 */
@TableName("fqc_attendance_record")
@Getter
@Setter
public class AttendanceRecord {

    /**
     * 主键ID
     */
    @TableId
    private Long id;

    /**
     * 员工ID
     */
    private String empId;

    /**
     * 员工组别 A/B/C/D
     */
    private String empTeam;

    /**
     * 员工姓名
     */
    private String empName;

    /**
     * 状态: 1-在岗 2-FAT 3-耐压 4-请假 5-离职
     */
    private Integer state;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
