package com.youlai.boot.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.youlai.boot.common.model.Option;
import com.youlai.boot.system.model.entity.Transition;
import com.youlai.boot.system.model.form.TransitionForm;
import com.youlai.boot.system.model.query.TransitionPageQuery;
import com.youlai.boot.system.model.vo.TransitionPageVO;

import java.util.List;

/**
 * 变迁业务接口
 */
public interface TransitionService extends IService<Transition> {

    /**
     * 获取变迁分页列表
     *
     * @param queryParams 查询参数
     * @return {@link IPage<TransitionPageVO>} 变迁分页列表
     */
    IPage<TransitionPageVO> getTransitionPage(TransitionPageQuery queryParams);

    /**
     * 获取变迁表单数据
     *
     * @param transitionId 变迁ID
     * @return {@link TransitionForm} 变迁表单数据
     */
    TransitionForm getTransitionFormData(Long transitionId);

    /**
     * 保存变迁信息
     *
     * @param transitionForm 变迁表单对象
     * @return {@link Boolean} 是否保存成功
     */
    boolean saveTransition(TransitionForm transitionForm);

    /**
     * 更新变迁信息
     *
     * @param transitionId   变迁ID
     * @param transitionForm 变迁表单对象
     * @return {@link Boolean} 是否更新成功
     */
    boolean updateTransition(Long transitionId, TransitionForm transitionForm);

    /**
     * 批量删除变迁
     *
     * @param idsStr 变迁ID字符串，多个以英文逗号(,)分割
     * @return {@link Boolean} 是否删除成功
     */
    boolean deleteTransitions(String idsStr);

    /**
     * 获取变迁下拉选项列表
     *
     * @return {@link List<Option<String>>} 变迁下拉选项列表
     */
    List<Option<String>> listTransitionOptions();
}