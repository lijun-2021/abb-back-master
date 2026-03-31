package com.youlai.boot.system.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youlai.boot.common.model.Option;
import com.youlai.boot.system.model.bo.ResourceStatusBO;
import com.youlai.boot.system.model.entity.ResourceStatus;
import com.youlai.boot.system.model.form.ResourceStatusForm;
import com.youlai.boot.system.model.vo.ResourceStatusPageVO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * 当日资源/状态对象转换器
 */
@Mapper(componentModel = "spring")
public interface ResourceStatusConverter {

    ResourceStatusPageVO toPageVo(ResourceStatusBO bo);

    ResourceStatusPageVO toPageVo(ResourceStatus entity);

    Page<ResourceStatusPageVO> toPageVo(Page<ResourceStatusBO> page);

    List<ResourceStatusPageVO> toPageVoList(List<ResourceStatus> list);

    @Mapping(target = "id", ignore = true)
    ResourceStatus toEntity(ResourceStatusForm form);

    @Mappings({
            @Mapping(target = "label", source = "processStationId"),
            @Mapping(target = "value", source = "id")
    })
    Option<String> toOption(ResourceStatus entity);

    List<Option<String>> toOptions(List<ResourceStatus> list);
}