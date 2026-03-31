package com.youlai.boot.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youlai.boot.system.model.bo.PlaceBO;
import com.youlai.boot.system.model.entity.Place;
import com.youlai.boot.system.model.form.PlaceForm;
import com.youlai.boot.system.model.query.PlacePageQuery;
import org.apache.ibatis.annotations.Mapper;

/**
 * 库所持久层接口
 */
@Mapper
public interface PlaceMapper extends BaseMapper<Place> {

    /**
     * 获取库所分页列表
     *
     * @param page        分页参数
     * @param queryParams 查询参数
     * @return 库所分页列表
     */
    Page<PlaceBO> getPlacePage(Page<PlaceBO> page, PlacePageQuery queryParams);

    /**
     * 获取库所表单详情
     *
     * @param placeId 库所ID
     * @return 库所表单详情
     */
    PlaceForm getPlaceFormData(Long placeId);
}