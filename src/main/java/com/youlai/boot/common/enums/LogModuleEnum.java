package com.youlai.boot.common.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

/**
 * 日志模块枚举
 *
 * @author Ray
 * @since 2.10.0
 */
@Schema(enumAsRef = true)
@Getter
public enum LogModuleEnum {

    EXCEPTION("异常"),
    LOGIN("登录"),
    USER("用户"),
    DEPT("部门"),
    ROLE("角色"),
    MENU("菜单"),
    DICT("字典"),
    SETTING("系统配置"),
    WORKER("工人"),
    PLACE("库所"),
    RESOURCE_STATUS("当日资源/状态"),
    TOKEN("Token"),
    WORKPIECE("工件"),
    ALGORITHM("算法"),
    REALTIME_SCHEDULE("实时调度"),
    TRANSITION("变迁"),
    FQC_SWITCH_CABINET("FQC开关柜"),
    FQC_EMPLOYEE_TASK("FQC员工任务"),
    OTHER("其他");

    @JsonValue
    private final String moduleName;

    LogModuleEnum(String moduleName) {
        this.moduleName = moduleName;
    }
}