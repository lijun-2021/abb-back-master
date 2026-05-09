package com.youlai.boot.system.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import com.youlai.boot.common.base.IBaseEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

/**
 * 任务类型枚举
 *
 * @author lijun
 * @since 2026/04/23
 */
@Schema(enumAsRef = true)
@Getter
public enum TaskTypeEnum implements IBaseEnum<Integer> {

    /**
     * 耐压任务
     */
    WITHSTAND(1, "耐压"),

    /**
     * 功能任务
     */
    FUNCTION(2, "功能");

    @JsonValue
    private final Integer value;

    private final String label;

    TaskTypeEnum(Integer value, String label) {
        this.value = value;
        this.label = label;
    }
}
