package com.youlai.boot.system.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youlai.boot.common.model.Option;
import com.youlai.boot.system.model.bo.AlgorithmBO;
import com.youlai.boot.system.model.entity.Algorithm;
import com.youlai.boot.system.model.form.AlgorithmForm;
import com.youlai.boot.system.model.vo.AlgorithmPageVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * 算法对象转换器
 */
@Mapper(componentModel = "spring")
public interface AlgorithmConverter {

    AlgorithmPageVO toPageVo(AlgorithmBO bo);

    Page<AlgorithmPageVO> toPageVo(Page<AlgorithmBO> page);

    @Mapping(target = "id", ignore = true)
    Algorithm toEntity(AlgorithmForm form);

    @Mappings({
            @Mapping(target = "label", source = "estimatedFinishTime"),
            @Mapping(target = "value", source = "id")
    })
    Option<String> toOption(Algorithm entity);

    List<Option<String>> toOptions(List<Algorithm> list);
}
