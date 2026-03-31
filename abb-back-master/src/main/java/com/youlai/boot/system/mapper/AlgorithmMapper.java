package com.youlai.boot.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youlai.boot.system.model.bo.AlgorithmBO;
import com.youlai.boot.system.model.entity.Algorithm;
import com.youlai.boot.system.model.form.AlgorithmForm;
import com.youlai.boot.system.model.query.AlgorithmPageQuery;
import org.apache.ibatis.annotations.Mapper;

/**
 * 算法持久层接口
 */
@Mapper
public interface AlgorithmMapper extends BaseMapper<Algorithm> {

    /**
     * 获取算法分页列表
     *
     * @param page        分页参数
     * @param queryParams 查询参数
     * @return 算法分页列表
     */
    Page<AlgorithmBO> getAlgorithmPage(Page<AlgorithmBO> page, AlgorithmPageQuery queryParams);

    /**
     * 获取算法表单详情
     *
     * @param algorithmId 算法ID
     * @return 算法表单详情
     */
    AlgorithmForm getAlgorithmFormData(Long algorithmId);
}
