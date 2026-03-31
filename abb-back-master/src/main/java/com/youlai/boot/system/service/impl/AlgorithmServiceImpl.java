package com.youlai.boot.system.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youlai.boot.common.model.Option;
import com.youlai.boot.security.util.SecurityUtils;
import com.youlai.boot.system.converter.AlgorithmConverter;
import com.youlai.boot.system.mapper.AlgorithmMapper;
import com.youlai.boot.system.model.bo.AlgorithmBO;
import com.youlai.boot.system.model.entity.Algorithm;
import com.youlai.boot.system.model.form.AlgorithmForm;
import com.youlai.boot.system.model.query.AlgorithmPageQuery;
import com.youlai.boot.system.model.vo.AlgorithmPageVO;
import com.youlai.boot.system.service.AlgorithmService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 算法业务实现类
 */
@Service
@RequiredArgsConstructor
public class AlgorithmServiceImpl extends ServiceImpl<AlgorithmMapper, Algorithm> implements AlgorithmService {

    private final AlgorithmConverter algorithmConverter;

    /**
     * 获取算法分页列表
     *
     * @param queryParams 查询参数
     * @return {@link IPage<AlgorithmPageVO>} 算法分页列表
     */
    @Override
    public IPage<AlgorithmPageVO> getAlgorithmPage(AlgorithmPageQuery queryParams) {
        // 参数构建
        int pageNum = queryParams.getPageNum();
        int pageSize = queryParams.getPageSize();
        Page<AlgorithmBO> page = new Page<>(pageNum, pageSize);

        // 查询数据
        Page<AlgorithmBO> algorithmPage = this.baseMapper.getAlgorithmPage(page, queryParams);

        // 实体转换
        return algorithmConverter.toPageVo(algorithmPage);
    }

    /**
     * 获取算法表单数据
     *
     * @param algorithmId 算法ID
     * @return {@link AlgorithmForm} 算法表单数据
     */
    @Override
    public AlgorithmForm getAlgorithmFormData(Long algorithmId) {
        return this.baseMapper.getAlgorithmFormData(algorithmId);
    }

    /**
     * 保存算法信息
     *
     * @param algorithmForm 算法表单对象
     * @return {@link Boolean} 是否保存成功
     */
    @Override
    public boolean saveAlgorithm(AlgorithmForm algorithmForm) {
        // 实体转换 form->entity
        Algorithm entity = algorithmConverter.toEntity(algorithmForm);
        entity.setCreateBy(SecurityUtils.getUserId());
        entity.setUpdateBy(SecurityUtils.getUserId());

        // 保存算法
        return this.save(entity);
    }

    /**
     * 更新算法信息
     *
     * @param algorithmId   算法ID
     * @param algorithmForm 算法表单对象
     * @return {@link Boolean} 是否更新成功
     */
    @Override
    public boolean updateAlgorithm(Long algorithmId, AlgorithmForm algorithmForm) {
        // form -> entity
        Algorithm entity = algorithmConverter.toEntity(algorithmForm);
        entity.setId(algorithmId);
        entity.setUpdateBy(SecurityUtils.getUserId());

        // 更新算法
        return this.updateById(entity);
    }

    /**
     * 批量删除算法
     *
     * @param idsStr 算法ID字符串，多个以英文逗号(,)分割
     * @return {@link Boolean} 是否删除成功
     */
    @Override
    public boolean deleteAlgorithms(String idsStr) {
        StrUtil.isNotBlank(idsStr);
        // 逻辑删除
        List<Long> ids = Arrays.stream(idsStr.split(",")).map(Long::parseLong).collect(Collectors.toList());
        return this.removeByIds(ids);
    }

    /**
     * 获取算法下拉选项列表
     *
     * @return {@link List<Option<String>>} 算法下拉选项列表
     */
    @Override
    public List<Option<String>> listAlgorithmOptions() {
        List<Algorithm> algorithms = this.list(new LambdaQueryWrapper<Algorithm>().orderByAsc(Algorithm::getId));
        return algorithmConverter.toOptions(algorithms);
    }
}
