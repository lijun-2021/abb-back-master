package com.youlai.boot.system.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import com.youlai.boot.common.base.IBaseEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

/**
 * 操作类型枚举
 *
 * @author lijun
 * @since 2026/04/23
 */
@Schema(enumAsRef = true)
@Getter
public enum OperateTypeEnum implements IBaseEnum<Integer> {

    /**
     * 保存耐压员工
     */
    SAVE_WITHSTAND_EMP(1, "保存耐压员工"),

    /**
     * 保存功能员工
     */
    SAVE_FUNCTION_EMP(2, "保存功能员工"),

    /**
     * 状态更新
     */
    UPDATE_STATUS(3, "状态更新");

    @JsonValue
    private final Integer value;

    private final String label;

    OperateTypeEnum(Integer value, String label) {
        this.value = value;
        this.label = label;
    }
}
