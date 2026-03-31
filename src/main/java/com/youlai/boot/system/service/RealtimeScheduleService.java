package com.youlai.boot.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.youlai.boot.common.model.Option;
import com.youlai.boot.system.model.entity.RealtimeSchedule;
import com.youlai.boot.system.model.form.RealtimeScheduleForm;
import com.youlai.boot.system.model.query.RealtimeSchedulePageQuery;
import com.youlai.boot.system.model.vo.RealtimeSchedulePageVO;

import java.util.List;

/**
 * 实时调度业务接口
 */
public interface RealtimeScheduleService extends IService<RealtimeSchedule> {

    /**
     * 获取实时调度分页列表
     *
     * @param queryParams 查询参数
     * @return {@link IPage<RealtimeSchedulePageVO>} 实时调度分页列表
     */
    IPage<RealtimeSchedulePageVO> getRealtimeSchedulePage(RealtimeSchedulePageQuery queryParams);

    /**
     * 获取实时调度表单数据
     *
     * @param realtimeScheduleId 实时调度ID
     * @return {@link RealtimeScheduleForm} 实时调度表单数据
     */
    RealtimeScheduleForm getRealtimeScheduleFormData(Long realtimeScheduleId);

    /**
     * 保存实时调度信息
     *
     * @param realtimeScheduleForm 实时调度表单对象
     * @return {@link Boolean} 是否保存成功
     */
    boolean saveRealtimeSchedule(RealtimeScheduleForm realtimeScheduleForm);

    /**
     * 更新实时调度信息
     *
     * @param realtimeScheduleId   实时调度ID
     * @param realtimeScheduleForm 实时调度表单对象
     * @return {@link Boolean} 是否更新成功
     */
    boolean updateRealtimeSchedule(Long realtimeScheduleId, RealtimeScheduleForm realtimeScheduleForm);

    /**
     * 批量删除实时调度
     *
     * @param idsStr 实时调度ID字符串，多个以英文逗号(,)分割
     * @return {@link Boolean} 是否删除成功
     */
    boolean deleteRealtimeSchedules(String idsStr);

    /**
     * 获取实时调度下拉选项列表
     *
     * @return {@link List<Option<String>>} 实时调度下拉选项列表
     */
    List<Option<String>> listRealtimeScheduleOptions();
}