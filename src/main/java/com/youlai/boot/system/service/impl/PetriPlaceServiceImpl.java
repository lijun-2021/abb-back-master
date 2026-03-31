package com.youlai.boot.system.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youlai.boot.common.model.Option;
import com.youlai.boot.security.util.SecurityUtils;
import com.youlai.boot.system.converter.PetriPlaceConverter;
import com.youlai.boot.system.mapper.PetriPlaceMapper;
import com.youlai.boot.system.model.bo.PetriPlaceBO;
import com.youlai.boot.system.model.entity.PetriPlace;
import com.youlai.boot.system.model.form.PetriPlaceForm;
import com.youlai.boot.system.model.query.PetriPlacePageQuery;
import com.youlai.boot.system.model.vo.PetriPlacePageVO;
import com.youlai.boot.system.service.IPetriPlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Petri 库所业务实现类
 */
@Service
@RequiredArgsConstructor
public class PetriPlaceServiceImpl extends ServiceImpl<PetriPlaceMapper, PetriPlace> implements IPetriPlaceService {

    private final PetriPlaceConverter petriPlaceConverter;

    /**
     * 获取 Petri 库所分页列表
     *
     * @param queryParams 查询参数
     * @return {@link IPage<PetriPlacePageVO>} Petri 库所分页列表
     */
    @Override
    public IPage<PetriPlacePageVO> getPetriPlacePage(PetriPlacePageQuery queryParams) {
        // 参数构建
        int pageNum = queryParams.getPageNum();
        int pageSize = queryParams.getPageSize();
        Page<PetriPlaceBO> page = new Page<>(pageNum, pageSize);

        // 查询数据
        Page<PetriPlaceBO> placePage = this.baseMapper.getPetriPlacePage(page, queryParams);

        // 实体转换
        return petriPlaceConverter.toPageVo(placePage);
    }

    /**
     * 获取 Petri 库所表单数据
     *
     * @param placeId 库所 ID
     * @return {@link PetriPlaceForm} Petri 库所表单数据
     */
    @Override
    public PetriPlaceForm getPetriPlaceFormData(Long placeId) {
        return this.baseMapper.getPetriPlaceFormData(placeId);
    }

    /**
     * 新增 Petri 库所
     *
     * @param placeForm Petri 库所表单对象
     * @return {@link Boolean} 是否新增成功
     */
    @Override
    public boolean savePetriPlace(PetriPlaceForm placeForm) {
        // 实体转换 form->entity
        PetriPlace entity = petriPlaceConverter.toEntity(placeForm);
        entity.setCreateBy(SecurityUtils.getUserId());
        entity.setUpdateBy(SecurityUtils.getUserId());

        // 新增 Petri 库所
        return this.save(entity);
    }

    /**
     * 修改 Petri 库所
     *
     * @param placeId   库所 ID
     * @param placeForm Petri 库所表单对象
     * @return {@link Boolean} 是否修改成功
     */
    @Override
    public boolean updatePetriPlace(Long placeId, PetriPlaceForm placeForm) {
        // form -> entity
        PetriPlace entity = petriPlaceConverter.toEntity(placeForm);
        entity.setId(placeId);
        entity.setUpdateBy(SecurityUtils.getUserId());

        // 修改 Petri 库所
        return this.updateById(entity);
    }

    /**
     * 批量删除 Petri 库所
     *
     * @param idsStr 库所 ID，多个以英文逗号 (,) 分割
     * @return {@link Boolean} 是否删除成功
     */
    @Override
    public boolean deletePetriPlaces(String idsStr) {
        StrUtil.isNotBlank(idsStr);
        // 逻辑删除 - 使用 Long 类型
        List<Long> ids = Arrays.stream(idsStr.split("\\,")).map(Long::parseLong).collect(Collectors.toList());
        return this.removeByIds(ids);
    }

    /**
     * 获取 Petri 库所下拉列表
     *
     * @return {@link List<Option<String>>} Petri 库所选项列表
     */
    @Override
    public List<Option<String>> listPetriPlaceOptions() {
        List<PetriPlace> places = this.list(new LambdaQueryWrapper<PetriPlace>().orderByAsc(PetriPlace::getId));
        return petriPlaceConverter.toOptions(places);
    }
}

