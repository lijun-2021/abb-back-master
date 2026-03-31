package com.youlai.boot.system.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youlai.boot.common.model.Option;
import com.youlai.boot.security.util.SecurityUtils;
import com.youlai.boot.system.converter.RealtimeScheduleConverter;
import com.youlai.boot.system.mapper.RealtimeScheduleMapper;
import com.youlai.boot.system.model.bo.RealtimeScheduleBO;
import com.youlai.boot.system.model.entity.RealtimeSchedule;
import com.youlai.boot.system.model.form.RealtimeScheduleForm;
import com.youlai.boot.system.model.query.RealtimeSchedulePageQuery;
import com.youlai.boot.system.model.vo.RealtimeSchedulePageVO;
import com.youlai.boot.system.service.RealtimeScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 实时调度业务实现类
 */
@Service
@RequiredArgsConstructor
public class RealtimeScheduleServiceImpl extends ServiceImpl<RealtimeScheduleMapper, RealtimeSchedule> implements RealtimeScheduleService {

    private final RealtimeScheduleConverter realtimeScheduleConverter;

    /**
     * 获取实时调度分页列表
     *
     * @param queryParams 查询参数
     * @return {@link IPage<RealtimeSchedulePageVO>} 实时调度分页列表
     */
    @Override
    public IPage<RealtimeSchedulePageVO> getRealtimeSchedulePage(RealtimeSchedulePageQuery queryParams) {
        // 参数构建
        int pageNum = queryParams.getPageNum();
        int pageSize = queryParams.getPageSize();
        Page<RealtimeScheduleBO> page = new Page<>(pageNum, pageSize);

        // 查询数据
        Page<RealtimeScheduleBO> realtimeSchedulePage = this.baseMapper.getRealtimeSchedulePage(page, queryParams);

        // 实体转换
        return realtimeScheduleConverter.toPageVo(realtimeSchedulePage);
    }

    /**
     * 获取实时调度表单数据
     *
     * @param realtimeScheduleId 实时调度ID
     * @return {@link RealtimeScheduleForm} 实时调度表单数据
     */
    @Override
    public RealtimeScheduleForm getRealtimeScheduleFormData(Long realtimeScheduleId) {
        return this.baseMapper.getRealtimeScheduleFormData(realtimeScheduleId);
    }

    /**
     * 新增实时调度
     *
     * @param realtimeScheduleForm 实时调度表单对象
     * @return {@link Boolean} 是否新增成功
     */
    @Override
    public boolean saveRealtimeSchedule(RealtimeScheduleForm realtimeScheduleForm) {
        // 实体转换 form->entity
        RealtimeSchedule entity = realtimeScheduleConverter.toEntity(realtimeScheduleForm);
        entity.setCreateBy(SecurityUtils.getUserId());
        entity.setUpdateBy(SecurityUtils.getUserId());

        // 新增实时调度
        return this.save(entity);
    }

    /**
     * 修改实时调度
     *
     * @param realtimeScheduleId   实时调度ID
     * @param realtimeScheduleForm 实时调度表单对象
     * @return {@link Boolean} 是否修改成功
     */
    @Override
    public boolean updateRealtimeSchedule(Long realtimeScheduleId, RealtimeScheduleForm realtimeScheduleForm) {
        // form -> entity
        RealtimeSchedule entity = realtimeScheduleConverter.toEntity(realtimeScheduleForm);
        entity.setId(realtimeScheduleId);
        entity.setUpdateBy(SecurityUtils.getUserId());

        // 修改实时调度
        return this.updateById(entity);
    }

    /**
     * 批量删除实时调度
     *
     * @param idsStr 实时调度ID，多个以英文逗号(,)分割
     * @return {@link Boolean} 是否删除成功
     */
    @Override
    public boolean deleteRealtimeSchedules(String idsStr) {
        StrUtil.isNotBlank(idsStr);
        // 逻辑删除
        List<Long> ids = Arrays.stream(idsStr.split(",")).map(Long::parseLong).collect(Collectors.toList());
        return this.removeByIds(ids);
    }

    /**
     * 获取实时调度下拉列表
     *
     * @return {@link List<Option<String>>} 实时调度选项列表
     */
    @Override
    public List<Option<String>> listRealtimeScheduleOptions() {
        List<RealtimeSchedule> realtimeSchedules = this.list(new LambdaQueryWrapper<RealtimeSchedule>().orderByAsc(RealtimeSchedule::getId));
        return realtimeScheduleConverter.toOptions(realtimeSchedules);
    }
}