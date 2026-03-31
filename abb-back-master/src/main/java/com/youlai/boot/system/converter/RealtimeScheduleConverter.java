package com.youlai.boot.system.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youlai.boot.common.model.Option;
import com.youlai.boot.system.model.bo.RealtimeScheduleBO;
import com.youlai.boot.system.model.entity.RealtimeSchedule;
import com.youlai.boot.system.model.form.RealtimeScheduleForm;
import com.youlai.boot.system.model.vo.RealtimeSchedulePageVO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * 实时调度对象转换器
 */
@Mapper(componentModel = "spring")
public interface RealtimeScheduleConverter {

    RealtimeSchedulePageVO toPageVo(RealtimeScheduleBO bo);

    Page<RealtimeSchedulePageVO> toPageVo(Page<RealtimeScheduleBO> page);

    @Mapping(target = "id", ignore = true)
    RealtimeSchedule toEntity(RealtimeScheduleForm form);

    @Mappings({
            @Mapping(target = "label", source = "faultCode"),
            @Mapping(target = "value", source = "id")
    })
    Option<String> toOption(RealtimeSchedule entity);

    List<Option<String>> toOptions(List<RealtimeSchedule> list);
}