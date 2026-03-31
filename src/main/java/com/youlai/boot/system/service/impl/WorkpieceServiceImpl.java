package com.youlai.boot.system.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youlai.boot.common.model.Option;
import com.youlai.boot.security.util.SecurityUtils;
import com.youlai.boot.system.converter.WorkpieceConverter;
import com.youlai.boot.system.mapper.WorkpieceMapper;
import com.youlai.boot.system.model.bo.WorkpieceBO;
import com.youlai.boot.system.model.entity.Workpiece;
import com.youlai.boot.system.model.form.WorkpieceForm;
import com.youlai.boot.system.model.query.WorkpiecePageQuery;
import com.youlai.boot.system.model.vo.WorkpiecePageVO;
import com.youlai.boot.system.service.WorkpieceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 工件业务实现类
 */
@Service
@RequiredArgsConstructor
public class WorkpieceServiceImpl extends ServiceImpl<WorkpieceMapper, Workpiece> implements WorkpieceService {

    private final WorkpieceConverter workpieceConverter;

    /**
     * 获取工件分页列表
     *
     * @param queryParams 查询参数
     * @return {@link IPage<WorkpiecePageVO>} 工件分页列表
     */
    @Override
    public IPage<WorkpiecePageVO> getWorkpiecePage(WorkpiecePageQuery queryParams) {
        // 参数构建
        int pageNum = queryParams.getPageNum();
        int pageSize = queryParams.getPageSize();
        Page<WorkpieceBO> page = new Page<>(pageNum, pageSize);

        // 查询数据
        Page<WorkpieceBO> workpiecePage = this.baseMapper.getWorkpiecePage(page, queryParams);

        // 实体转换
        return workpieceConverter.toPageVo(workpiecePage);
    }

    /**
     * 获取工件表单数据
     *
     * @param workpieceId 工件ID
     * @return {@link WorkpieceForm} 工件表单数据
     */
    @Override
    public WorkpieceForm getWorkpieceFormData(Long workpieceId) {
        return this.baseMapper.getWorkpieceFormData(workpieceId);
    }

    /**
     * 新增工件
     *
     * @param workpieceForm 工件表单对象
     * @return {@link Boolean} 是否新增成功
     */
    @Override
    public boolean saveWorkpiece(WorkpieceForm workpieceForm) {
        // 实体转换 form->entity
        Workpiece entity = workpieceConverter.toEntity(workpieceForm);
        entity.setCreateBy(SecurityUtils.getUserId());
        entity.setUpdateBy(SecurityUtils.getUserId());

        // 新增工件
        return this.save(entity);
    }

    /**
     * 修改工件
     *
     * @param workpieceId   工件ID
     * @param workpieceForm 工件表单对象
     * @return {@link Boolean} 是否修改成功
     */
    @Override
    public boolean updateWorkpiece(Long workpieceId, WorkpieceForm workpieceForm) {
        // form -> entity
        Workpiece entity = workpieceConverter.toEntity(workpieceForm);
        entity.setId(workpieceId);
        entity.setUpdateBy(SecurityUtils.getUserId());

        // 修改工件
        return this.updateById(entity);
    }

    /**
     * 批量删除工件
     *
     * @param idsStr 工件ID，多个以英文逗号(,)分割
     * @return {@link Boolean} 是否删除成功
     */
    @Override
    public boolean deleteWorkpieces(String idsStr) {
        StrUtil.isNotBlank(idsStr);
        // 逻辑删除
        List<Long> ids = Arrays.stream(idsStr.split("\\,")).map(Long::parseLong).collect(Collectors.toList());
        return this.removeByIds(ids);
    }

    /**
     * 获取工件下拉列表
     *
     * @return {@link List<Option<String>>} 工件选项列表
     */
    @Override
    public List<Option<String>> listWorkpieceOptions() {
        List<Workpiece> workpieces = this.list(new LambdaQueryWrapper<Workpiece>().orderByAsc(Workpiece::getId));
        return workpieceConverter.toOptions(workpieces);
    }
}