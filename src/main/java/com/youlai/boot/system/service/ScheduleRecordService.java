package com.youlai.boot.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.youlai.boot.system.model.entity.ScheduleRecord;

/**
 * 排程操作记录业务接口
 *
* @author lijun
* @since 2026/04/23
 */
public interface ScheduleRecordService extends IService<ScheduleRecord> {

    /**
     * 保存操作记录
     *
     * @param snCode       SN号
     * @param operateType  操作类型 1-保存耐压员工 2-保存功能员工 3-状态更新
     * @param beforeValue  修改前值
     * @param afterValue   修改后值
     */
    void saveScheduleRecord(String snCode, Integer operateType, String beforeValue, String afterValue);
}
