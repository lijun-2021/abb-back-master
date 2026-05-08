package com.youlai.boot.system.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import com.youlai.boot.common.base.IBaseEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

/**
 * 任务状态枚举
 *
 * @author lijun
 * @since 2026/04/23
 */
@Schema(enumAsRef = true)
@Getter
public enum TaskStatusEnum implements IBaseEnum<Integer> {

    /**
     * 未开始
     */
    NOT_STARTED(0, "未开始"),

    /**
     * 进行中
     */
    IN_PROGRESS(1, "进行中"),

    /**
     * 已完成
     */
    COMPLETED(2, "已完成");

    @JsonValue
    private final Integer value;

    private final String label;

    TaskStatusEnum(Integer value, String label) {
        this.value = value;
        this.label = label;
    }
}
