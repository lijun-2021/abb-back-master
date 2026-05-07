package com.youlai.boot.system.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import com.youlai.boot.common.base.IBaseEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

/**
 * 柜类型枚举
 *
 * @author YourName
 * @since 2024/XX/XX
 */
@Schema(enumAsRef = true)
@Getter
public enum CabinetTypeEnum implements IBaseEnum<Integer> {

    /**
     * 高压柜
     */
    HIGH_VOLTAGE(1, "高压柜"),

    /**
     * 低压柜
     */
    LOW_VOLTAGE(2, "低压柜");

    @JsonValue
    private final Integer value;

    private final String label;

    CabinetTypeEnum(Integer value, String label) {
        this.value = value;
        this.label = label;
    }
}
