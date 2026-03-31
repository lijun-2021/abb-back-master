package com.youlai.boot.system.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youlai.boot.common.model.Option;
import com.youlai.boot.system.model.bo.WorkerBO;
import com.youlai.boot.system.model.entity.Worker;
import com.youlai.boot.system.model.form.WorkerForm;
import com.youlai.boot.system.model.vo.WorkerPageVO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * 工人对象转换器
 */
@Mapper(componentModel = "spring")
public interface WorkerConverter {

    WorkerPageVO toPageVo(WorkerBO bo);

    Page<WorkerPageVO> toPageVo(Page<WorkerBO> page);

    @Mapping(target = "id", ignore = true)
    Worker toEntity(WorkerForm form);

    @Mappings({
            @Mapping(target = "label", source = "workerCode"),
            @Mapping(target = "value", source = "id")
    })
    Option<String> toOption(Worker entity);

    List<Option<String>> toOptions(List<Worker> list);
}