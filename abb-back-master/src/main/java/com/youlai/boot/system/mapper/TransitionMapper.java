package com.youlai.boot.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youlai.boot.system.model.bo.TransitionBO;
import com.youlai.boot.system.model.entity.Transition;
import com.youlai.boot.system.model.form.TransitionForm;
import com.youlai.boot.system.model.query.TransitionPageQuery;
import org.apache.ibatis.annotations.Mapper;

/**
 * 变迁持久层接口
 */
@Mapper
public interface TransitionMapper extends BaseMapper<Transition> {

    /**
     * 获取变迁分页列表
     *
     * @param page        分页参数
     * @param queryParams 查询参数
     * @return 变迁分页列表
     */
    Page<TransitionBO> getTransitionPage(Page<TransitionBO> page, TransitionPageQuery queryParams);

    /**
     * 获取变迁表单详情
     *
     * @param transitionId 变迁ID
     * @return 变迁表单详情
     */
    TransitionForm getTransitionFormData(Long transitionId);
}