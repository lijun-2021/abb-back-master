package com.youlai.boot.system.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youlai.boot.common.model.Option;
import com.youlai.boot.system.model.bo.PlaceBO;
import com.youlai.boot.system.model.entity.Place;
import com.youlai.boot.system.model.form.PlaceForm;
import com.youlai.boot.system.model.vo.PlacePageVO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * 库所对象转换器
 */
@Mapper(componentModel = "spring")
public interface PlaceConverter {

    PlacePageVO toPageVo(PlaceBO bo);

    PlacePageVO toPageVo(Place entity);

    Page<PlacePageVO> toPageVo(Page<PlaceBO> page);

    List<PlacePageVO> toPageVoList(List<Place> list);

    @Mapping(target = "id", ignore = true)
    Place toEntity(PlaceForm form);

    @Mappings({
            @Mapping(target = "label", source = "placeName"),
            @Mapping(target = "value", source = "id")
    })
    Option<String> toOption(Place entity);

    List<Option<String>> toOptions(List<Place> list);
}