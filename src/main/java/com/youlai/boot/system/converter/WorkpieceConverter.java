package com.youlai.boot.system.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youlai.boot.common.model.Option;
import com.youlai.boot.system.model.bo.WorkpieceBO;
import com.youlai.boot.system.model.entity.Workpiece;
import com.youlai.boot.system.model.form.WorkpieceForm;
import com.youlai.boot.system.model.vo.WorkpiecePageVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * 工件转换器
 *
 * @author haoxr
 * @since 2024/12/31
 */
@Mapper(componentModel = "spring")
public interface WorkpieceConverter {

    WorkpiecePageVO toPageVo(WorkpieceBO bo);

    Page<WorkpiecePageVO> toPageVo(Page<WorkpieceBO> page);

    @Mapping(target = "id", ignore = true)
    Workpiece toEntity(WorkpieceForm form);

    WorkpiecePageVO entity2PageVO(Workpiece entity);

    @Mappings({
            @Mapping(target = "label", source = "code"),
            @Mapping(target = "value", source = "id")
    })
    Option<String> toOption(Workpiece entity);

    List<Option<String>> toOptions(List<Workpiece> list);
}