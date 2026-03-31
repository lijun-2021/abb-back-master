package com.youlai.boot.system.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youlai.boot.common.model.Option;
import com.youlai.boot.system.model.bo.TransitionBO;
import com.youlai.boot.system.model.entity.Transition;
import com.youlai.boot.system.model.form.TransitionForm;
import com.youlai.boot.system.model.vo.TransitionPageVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * 变迁对象转换器
 */
@Mapper(componentModel = "spring")
public interface TransitionConverter {

    TransitionPageVO toPageVo(TransitionBO bo);

    TransitionPageVO toPageVo(Transition entity);

    Page<TransitionPageVO> toPageVo(Page<TransitionBO> page);

    List<TransitionPageVO> toPageVoList(List<Transition> list);

    @Mapping(target = "id", ignore = true)
    Transition toEntity(TransitionForm form);

    @Mappings({
            @Mapping(target = "label", source = "transitionName"),
            @Mapping(target = "value", source = "id")
    })
    Option<String> toOption(Transition entity);

    List<Option<String>> toOptions(List<Transition> list);
}