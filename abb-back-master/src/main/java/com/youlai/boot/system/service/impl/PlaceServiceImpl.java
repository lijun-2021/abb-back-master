package com.youlai.boot.system.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youlai.boot.common.model.Option;
import com.youlai.boot.security.util.SecurityUtils;
import com.youlai.boot.system.converter.PlaceConverter;
import com.youlai.boot.system.mapper.PlaceMapper;
import com.youlai.boot.system.model.bo.PlaceBO;
import com.youlai.boot.system.model.entity.Place;
import com.youlai.boot.system.model.form.PlaceForm;
import com.youlai.boot.system.model.query.PlacePageQuery;
import com.youlai.boot.system.model.vo.PlacePageVO;
import com.youlai.boot.system.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 库所业务实现类
 */
@Service
@RequiredArgsConstructor
public class PlaceServiceImpl extends ServiceImpl<PlaceMapper, Place> implements PlaceService {

    private final PlaceConverter placeConverter;

    /**
     * 获取库所分页列表
     *
     * @param queryParams 查询参数
     * @return {@link IPage<PlacePageVO>} 库所分页列表
     */
    @Override
    public IPage<PlacePageVO> getPlacePage(PlacePageQuery queryParams) {
        // 参数构建
        int pageNum = queryParams.getPageNum();
        int pageSize = queryParams.getPageSize();
        Page<PlaceBO> page = new Page<>(pageNum, pageSize);

        // 查询数据
        Page<PlaceBO> placePage = this.baseMapper.getPlacePage(page, queryParams);

        // 实体转换
        return placeConverter.toPageVo(placePage);
    }

    /**
     * 获取库所表单数据
     *
     * @param placeId 库所ID
     * @return {@link PlaceForm} 库所表单数据
     */
    @Override
    public PlaceForm getPlaceFormData(Long placeId) {
        return this.baseMapper.getPlaceFormData(placeId);
    }

    /**
     * 新增库所
     *
     * @param placeForm 库所表单对象
     * @return {@link Boolean} 是否新增成功
     */
    @Override
    public boolean savePlace(PlaceForm placeForm) {
        // 实体转换 form->entity
        Place entity = placeConverter.toEntity(placeForm);
        entity.setCreateBy(SecurityUtils.getUserId());
        entity.setUpdateBy(SecurityUtils.getUserId());

        // 新增库所
        return this.save(entity);
    }

    /**
     * 修改库所
     *
     * @param placeId   库所ID
     * @param placeForm 库所表单对象
     * @return {@link Boolean} 是否修改成功
     */
    @Override
    public boolean updatePlace(Long placeId, PlaceForm placeForm) {
        // form -> entity
        Place entity = placeConverter.toEntity(placeForm);
        entity.setId(placeId);
        entity.setUpdateBy(SecurityUtils.getUserId());

        // 修改库所
        return this.updateById(entity);
    }

    /**
     * 批量删除库所
     *
     * @param idsStr 库所ID，多个以英文逗号(,)分割
     * @return {@link Boolean} 是否删除成功
     */
    @Override
    public boolean deletePlaces(String idsStr) {
        StrUtil.isNotBlank(idsStr);
        // 逻辑删除
        List<Long> ids = Arrays.stream(idsStr.split("\\,"))
                .map(Long::parseLong)
                .collect(Collectors.toList());
        return this.removeByIds(ids);
    }

    /**
     * 获取库所下拉列表
     *
     * @return {@link List<Option<String>>} 库所选项列表
     */
    @Override
    public List<Option<String>> listPlaceOptions() {
        List<Place> places = this.list(new LambdaQueryWrapper<Place>()
                .orderByAsc(Place::getId));
        return placeConverter.toOptions(places);
    }
}