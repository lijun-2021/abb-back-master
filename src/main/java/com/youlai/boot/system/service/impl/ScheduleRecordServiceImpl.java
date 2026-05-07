package com.youlai.boot.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youlai.boot.security.util.SecurityUtils;
import com.youlai.boot.system.mapper.ScheduleRecordMapper;
import com.youlai.boot.system.model.entity.ScheduleRecord;
import com.youlai.boot.system.service.ScheduleRecordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 排程操作记录业务实现类
 *
* @author lijun
* @since 2026/04/23
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ScheduleRecordServiceImpl extends ServiceImpl<ScheduleRecordMapper, ScheduleRecord> implements ScheduleRecordService {

    /**
     * 保存操作记录
     *
     * @param snCode       SN号
     * @param operateType  操作类型
     * @param beforeValue  修改前值
     * @param afterValue   修改后值
     */
    @Override
    public void saveScheduleRecord(String snCode, Integer operateType, String beforeValue, String afterValue) {
        ScheduleRecord record = new ScheduleRecord();
        record.setSnCode(snCode);
        record.setOperateType(operateType);
        record.setBeforeValue(beforeValue);
        record.setAfterValue(afterValue);
        record.setOperateUser(SecurityUtils.getUserId());

        try {
            record.setOperateUserName(SecurityUtils.getUsername());
        } catch (Exception e) {
            log.warn("获取当前用户姓名失败", e);
            record.setOperateUserName("系统");
        }

        this.save(record);
    }
}
