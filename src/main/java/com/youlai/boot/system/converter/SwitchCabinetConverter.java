package com.youlai.boot.system.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youlai.boot.system.model.entity.SwitchCabinet;
import com.youlai.boot.system.model.form.SwitchCabinetForm;
import com.youlai.boot.system.model.vo.SwitchCabinetPageVO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 开关柜对象转换器
 *
* @author lijun
* @since 2026/04/23
 */
@Mapper(componentModel = "spring")
public interface SwitchCabinetConverter {

    DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 分页VO转换
     */
    Page<SwitchCabinetPageVO> toPageVo(Page<SwitchCabinet> entity);

    /**
     * Form转Entity
     */
    @Mapping(target = "offlineTime", expression = "java(parseDateTime(form.getOfflineTime()))")
    SwitchCabinet toEntity(SwitchCabinetForm form);

    /**
     * Entity转Form
     */
    @InheritInverseConfiguration(name = "toEntity")
    @Mapping(target = "offlineTime", expression = "java(formatDateTime(entity.getOfflineTime()))")
    SwitchCabinetForm toForm(SwitchCabinet entity);

    /**
     * 字符串转LocalDateTime
     */
    default LocalDateTime parseDateTime(String timeStr) {
        if (timeStr == null || timeStr.isEmpty()) {
            return null;
        }
        try {
            return LocalDateTime.parse(timeStr, DATE_TIME_FORMATTER);
        } catch (Exception e) {
            throw new RuntimeException("时间格式错误，请使用 yyyy-MM-dd HH:mm:ss", e);
        }
    }

    /**
     * LocalDateTime转字符串
     */
    default String formatDateTime(LocalDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        return dateTime.format(DATE_TIME_FORMATTER);
    }
}
