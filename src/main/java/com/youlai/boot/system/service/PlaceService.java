package com.youlai.boot.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.youlai.boot.common.model.Option;
import com.youlai.boot.system.model.entity.Place;
import com.youlai.boot.system.model.form.PlaceForm;
import com.youlai.boot.system.model.query.PlacePageQuery;
import com.youlai.boot.system.model.vo.PlacePageVO;

import java.util.List;

/**
 * 库所业务接口
 */
public interface PlaceService extends IService<Place> {

    /**
     * 获取库所分页列表
     *
     * @param queryParams 查询参数
     * @return {@link IPage<PlacePageVO>} 库所分页列表
     */
    IPage<PlacePageVO> getPlacePage(PlacePageQuery queryParams);

    /**
     * 获取库所表单数据
     *
     * @param placeId 库所ID
     * @return {@link PlaceForm} 库所表单数据
     */
    PlaceForm getPlaceFormData(Long placeId);

    /**
     * 保存库所信息
     *
     * @param placeForm 库所表单对象
     * @return {@link Boolean} 是否保存成功
     */
    boolean savePlace(PlaceForm placeForm);

    /**
     * 更新库所信息
     *
     * @param placeId   库所ID
     * @param placeForm 库所表单对象
     * @return {@link Boolean} 是否更新成功
     */
    boolean updatePlace(Long placeId, PlaceForm placeForm);

    /**
     * 批量删除库所
     *
     * @param idsStr 库所ID字符串，多个以英文逗号(,)分割
     * @return {@link Boolean} 是否删除成功
     */
    boolean deletePlaces(String idsStr);

    /**
     * 获取库所下拉选项列表
     *
     * @return {@link List<Option<String>>} 库所下拉选项列表
     */
    List<Option<String>> listPlaceOptions();
}