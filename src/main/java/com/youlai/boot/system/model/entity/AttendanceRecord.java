package com.youlai.boot.system.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@TableName("fqc_attendance_record")
@Getter
@Setter
public class AttendanceRecord {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String empId;

    private String empTeam;

    private String empName;

    private Integer amState;

    private Integer pmState;

    private LocalDate recordDate;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    @TableLogic
    private Integer isDeleted;
}