package com.youlai.boot.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youlai.boot.system.model.bo.PetriPlaceBO;
import com.youlai.boot.system.model.entity.PetriPlace;
import com.youlai.boot.system.model.form.PetriPlaceForm;
import com.youlai.boot.system.model.query.PetriPlacePageQuery;
import org.apache.ibatis.annotations.Mapper;

/**
 * Petri 库所持久层接口
 */
@Mapper
public interface PetriPlaceMapper extends BaseMapper<PetriPlace> {

    /**
     * 获取 Petri 库所分页列表
     *
     * @param page        分页参数
     * @param queryParams 查询参数
     * @return Petri 库所分页列表
     */
    Page<PetriPlaceBO> getPetriPlacePage(Page<PetriPlaceBO> page, PetriPlacePageQuery queryParams);

    /**
     * 获取 Petri 库所表单详情
     *
     * @param placeId 库所 ID
     * @return Petri 库所表单详情
     */
    PetriPlaceForm getPetriPlaceFormData(Long placeId);
}

