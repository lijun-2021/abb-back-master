package com.youlai.boot.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youlai.boot.system.model.bo.RealtimeScheduleBO;
import com.youlai.boot.system.model.entity.RealtimeSchedule;
import com.youlai.boot.system.model.form.RealtimeScheduleForm;
import com.youlai.boot.system.model.query.RealtimeSchedulePageQuery;
import org.apache.ibatis.annotations.Mapper;

/**
 * 实时调度持久层接口
 */
@Mapper
public interface RealtimeScheduleMapper extends BaseMapper<RealtimeSchedule> {

    /**
     * 获取实时调度分页列表
     *
     * @param page        分页参数
     * @param queryParams 查询参数
     * @return 实时调度分页列表
     */
    Page<RealtimeScheduleBO> getRealtimeSchedulePage(Page<RealtimeScheduleBO> page, RealtimeSchedulePageQuery queryParams);

    /**
     * 获取实时调度表单详情
     *
     * @param realtimeScheduleId 实时调度ID
     * @return 实时调度表单详情
     */
    RealtimeScheduleForm getRealtimeScheduleFormData(Long realtimeScheduleId);
}