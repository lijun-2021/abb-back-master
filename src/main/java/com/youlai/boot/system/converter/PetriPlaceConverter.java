package com.youlai.boot.system.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youlai.boot.common.model.Option;
import com.youlai.boot.system.model.bo.PetriPlaceBO;
import com.youlai.boot.system.model.entity.PetriPlace;
import com.youlai.boot.system.model.form.PetriPlaceForm;
import com.youlai.boot.system.model.vo.PetriPlacePageVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * Petri 库所转换器
 */
@Mapper(componentModel = "spring")
public interface PetriPlaceConverter {

    /**
     * BO 转 PageVO
     *
     * @param page BO 分页对象
     * @return VO 分页对象
     */
    @Mapping(target = "total", source = "total")
    @Mapping(target = "records", source = "records")
    @Mapping(target = "size", source = "size")
    @Mapping(target = "current", source = "current")
    @Mapping(target = "pages", source = "pages")
    Page<PetriPlacePageVO> toPageVo(Page<PetriPlaceBO> page);

    /**
     * Form 转 Entity
     *
     * @param form 表单对象
     * @return 实体对象
     */
    PetriPlace toEntity(PetriPlaceForm form);

    /**
     * Entity 转 Option
     *
     * @param entities 实体列表
     * @return 选项列表
     */
    List<Option<String>> toOptions(List<PetriPlace> entities);
}

