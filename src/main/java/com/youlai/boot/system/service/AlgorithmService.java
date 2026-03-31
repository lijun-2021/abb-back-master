package com.youlai.boot.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.youlai.boot.common.model.Option;
import com.youlai.boot.system.model.entity.Algorithm;
import com.youlai.boot.system.model.form.AlgorithmForm;
import com.youlai.boot.system.model.query.AlgorithmPageQuery;
import com.youlai.boot.system.model.vo.AlgorithmPageVO;

import java.util.List;

/**
 * 算法业务接口
 */
public interface AlgorithmService extends IService<Algorithm> {

    /**
     * 获取算法分页列表
     *
     * @param queryParams 查询参数
     * @return {@link IPage<AlgorithmPageVO>} 算法分页列表
     */
    IPage<AlgorithmPageVO> getAlgorithmPage(AlgorithmPageQuery queryParams);

    /**
     * 获取算法表单数据
     *
     * @param algorithmId 算法ID
     * @return {@link AlgorithmForm} 算法表单数据
     */
    AlgorithmForm getAlgorithmFormData(Long algorithmId);

    /**
     * 保存算法信息
     *
     * @param algorithmForm 算法表单对象
     * @return {@link Boolean} 是否保存成功
     */
    boolean saveAlgorithm(AlgorithmForm algorithmForm);

    /**
     * 更新算法信息
     *
     * @param algorithmId   算法ID
     * @param algorithmForm 算法表单对象
     * @return {@link Boolean} 是否更新成功
     */
    boolean updateAlgorithm(Long algorithmId, AlgorithmForm algorithmForm);

    /**
     * 批量删除算法
     *
     * @param idsStr 算法ID字符串，多个以英文逗号(,)分割
     * @return {@link Boolean} 是否删除成功
     */
    boolean deleteAlgorithms(String idsStr);

    /**
     * 获取算法下拉选项列表
     *
     * @return {@link List<Option<String>>} 算法下拉选项列表
     */
    List<Option<String>> listAlgorithmOptions();
}
