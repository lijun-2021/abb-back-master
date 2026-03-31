package com.youlai.boot.system.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youlai.boot.common.model.Option;
import com.youlai.boot.security.util.SecurityUtils;
import com.youlai.boot.system.converter.TransitionConverter;
import com.youlai.boot.system.mapper.TransitionMapper;
import com.youlai.boot.system.model.bo.TransitionBO;
import com.youlai.boot.system.model.entity.Transition;
import com.youlai.boot.system.model.form.TransitionForm;
import com.youlai.boot.system.model.query.TransitionPageQuery;
import com.youlai.boot.system.model.vo.TransitionPageVO;
import com.youlai.boot.system.service.TransitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 变迁业务实现类
 */
@Service
@RequiredArgsConstructor
public class TransitionServiceImpl extends ServiceImpl<TransitionMapper, Transition> implements TransitionService {

    private final TransitionConverter transitionConverter;

    /**
     * 获取变迁分页列表
     *
     * @param queryParams 查询参数
     * @return {@link IPage<TransitionPageVO>} 变迁分页列表
     */
    @Override
    public IPage<TransitionPageVO> getTransitionPage(TransitionPageQuery queryParams) {
        // 参数构建
        int pageNum = queryParams.getPageNum();
        int pageSize = queryParams.getPageSize();
        Page<TransitionBO> page = new Page<>(pageNum, pageSize);

        // 查询数据
        Page<TransitionBO> transitionPage = this.baseMapper.getTransitionPage(page, queryParams);

        // 实体转换
        return transitionConverter.toPageVo(transitionPage);
    }

    /**
     * 获取变迁表单数据
     *
     * @param transitionId 变迁ID
     * @return {@link TransitionForm} 变迁表单数据
     */
    @Override
    public TransitionForm getTransitionFormData(Long transitionId) {
        return this.baseMapper.getTransitionFormData(transitionId);
    }

    /**
     * 新增变迁
     *
     * @param transitionForm 变迁表单对象
     * @return {@link Boolean} 是否新增成功
     */
    @Override
    public boolean saveTransition(TransitionForm transitionForm) {
        // 实体转换 form->entity
        Transition entity = transitionConverter.toEntity(transitionForm);
        entity.setCreateBy(SecurityUtils.getUserId());
        entity.setUpdateBy(SecurityUtils.getUserId());

        // 新增变迁
        return this.save(entity);
    }

    /**
     * 修改变迁
     *
     * @param transitionId   变迁ID
     * @param transitionForm 变迁表单对象
     * @return {@link Boolean} 是否修改成功
     */
    @Override
    public boolean updateTransition(Long transitionId, TransitionForm transitionForm) {
        // form -> entity
        Transition entity = transitionConverter.toEntity(transitionForm);
        entity.setId(transitionId);
        entity.setUpdateBy(SecurityUtils.getUserId());

        // 修改变迁
        return this.updateById(entity);
    }

    /**
     * 批量删除变迁
     *
     * @param idsStr 变迁ID，多个以英文逗号(,)分割
     * @return {@link Boolean} 是否删除成功
     */
    @Override
    public boolean deleteTransitions(String idsStr) {
        StrUtil.isNotBlank(idsStr);
        // 逻辑删除
        List<Long> ids = Arrays.stream(idsStr.split("\\,")).map(Long::parseLong).collect(Collectors.toList());
        return this.removeByIds(ids);
    }

    /**
     * 获取变迁下拉列表
     *
     * @return {@link List<Option<String>>} 变迁选项列表
     */
    @Override
    public List<Option<String>> listTransitionOptions() {
        List<Transition> transitions = this.list(new LambdaQueryWrapper<Transition>().orderByAsc(Transition::getId));
        return transitionConverter.toOptions(transitions);
    }
}