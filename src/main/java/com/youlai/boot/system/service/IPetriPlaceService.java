package com.youlai.boot.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.youlai.boot.common.model.Option;
import com.youlai.boot.system.model.entity.PetriPlace;
import com.youlai.boot.system.model.form.PetriPlaceForm;
import com.youlai.boot.system.model.query.PetriPlacePageQuery;
import com.youlai.boot.system.model.vo.PetriPlacePageVO;

import java.util.List;

/**
 * Petri 库所业务接口
 */
public interface IPetriPlaceService extends IService<PetriPlace> {

    /**
     * 获取 Petri 库所分页列表
     *
     * @param queryParams 查询参数
     * @return {@link IPage<PetriPlacePageVO>} Petri 库所分页列表
     */
    IPage<PetriPlacePageVO> getPetriPlacePage(PetriPlacePageQuery queryParams);

    /**
     * 获取 Petri 库所表单数据
     *
     * @param placeId 库所 ID
     * @return {@link PetriPlaceForm} Petri 库所表单数据
     */
    PetriPlaceForm getPetriPlaceFormData(Long placeId);

    /**
     * 保存 Petri 库所信息
     *
     * @param placeForm Petri 库所表单对象
     * @return {@link Boolean} 是否保存成功
     */
    boolean savePetriPlace(PetriPlaceForm placeForm);

    /**
     * 更新 Petri 库所信息
     *
     * @param placeId   库所 ID
     * @param placeForm Petri 库所表单对象
     * @return {@link Boolean} 是否更新成功
     */
    boolean updatePetriPlace(Long placeId, PetriPlaceForm placeForm);

    /**
     * 批量删除 Petri 库所
     *
     * @param idsStr 库所 ID 字符串，多个以英文逗号 (,) 分割
     * @return {@link Boolean} 是否删除成功
     */
    boolean deletePetriPlaces(String idsStr);

    /**
     * 获取 Petri 库所下拉选项列表
     *
     * @return {@link List<Option<String>>} Petri 库所下拉选项列表
     */
    List<Option<String>> listPetriPlaceOptions();
}

