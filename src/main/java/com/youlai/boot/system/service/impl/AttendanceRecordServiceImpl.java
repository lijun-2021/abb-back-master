package com.youlai.boot.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youlai.boot.system.mapper.AttendanceRecordMapper;
import com.youlai.boot.system.model.entity.AttendanceRecord;
import com.youlai.boot.system.model.form.AttendanceRecordForm;
import com.youlai.boot.system.model.query.AttendanceRecordPageQuery;
import com.youlai.boot.system.model.vo.AttendanceRecordPageVO;
import com.youlai.boot.system.service.AttendanceRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 员工考勤记录业务实现类
 *
 * @author lijun
 * @since 2026/07/06
 */
@Service
@RequiredArgsConstructor
public class AttendanceRecordServiceImpl extends ServiceImpl<AttendanceRecordMapper, AttendanceRecord> implements AttendanceRecordService {

    /**
     * 获取员工考勤记录分页列表
     *
     * @param queryParams 查询参数
     * @return 考勤记录分页列表
     */
    @Override
    public IPage<AttendanceRecordPageVO> getAttendanceRecordPage(AttendanceRecordPageQuery queryParams) {
        Page<AttendanceRecordPageVO> page = new Page<>(queryParams.getPageNum(), queryParams.getPageSize());
        return this.baseMapper.getAttendanceRecordPage(page, queryParams);
    }

    /**
     * 更新员工考勤状态（支持单条和批量更新）
     *
     * @param form 考勤表单（包含empId和state用于单条更新，或items列表用于批量更新）
     * @return 是否成功
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateAttendanceState(AttendanceRecordForm form) {
        // 判断是单条更新还是批量更新
        if (form.getItems() != null && !form.getItems().isEmpty()) {
            // 批量更新
            return batchUpdateAttendanceState(form.getItems());
        } else {
            // 单条更新
            return singleUpdateAttendanceState(form.getEmpId(), form.getState());
        }
    }

    /**
     * 单条更新员工考勤状态
     *
     * @param empId 员工ID
     * @param state 状态值
     * @return 是否成功
     */
    private boolean singleUpdateAttendanceState(String empId, Integer state) {
        // 验证参数
        if (empId == null || empId.trim().isEmpty()) {
            throw new IllegalArgumentException("员工ID不能为空");
        }
        validateState(state);

        // 根据empId查询现有记录
        AttendanceRecord existingRecord = this.lambdaQuery()
                .eq(AttendanceRecord::getEmpId, empId)
                .one();

        if (existingRecord == null) {
            throw new IllegalArgumentException("未找到员工ID为 " + empId + " 的考勤记录");
        }

        // 仅更新state字段
        existingRecord.setState(state);
        return this.updateById(existingRecord);
    }

    /**
     * 批量更新员工考勤状态
     *
     * @param items 员工状态列表
     * @return 是否成功
     */
    private boolean batchUpdateAttendanceState(List<AttendanceRecordForm.AttendanceStateItem> items) {
        // 验证所有项的状态值合法性
        for (AttendanceRecordForm.AttendanceStateItem item : items) {
            validateState(item.getState());
        }

        // 提取所有员工ID
        List<String> empIds = items.stream()
                .map(AttendanceRecordForm.AttendanceStateItem::getEmpId)
                .collect(Collectors.toList());

        // 批量查询现有记录
        List<AttendanceRecord> existingRecords = this.lambdaQuery()
                .in(AttendanceRecord::getEmpId, empIds)
                .list();

        // 构建 empId -> AttendanceRecord 的映射
        Map<String, AttendanceRecord> recordMap = existingRecords.stream()
                .collect(Collectors.toMap(AttendanceRecord::getEmpId, record -> record));

        // 检查是否有不存在的员工
        for (String empId : empIds) {
            if (!recordMap.containsKey(empId)) {
                throw new IllegalArgumentException("未找到员工ID为 " + empId + " 的考勤记录");
            }
        }

        // 批量更新状态
        List<AttendanceRecord> recordsToUpdate = items.stream()
                .map(item -> {
                    AttendanceRecord record = recordMap.get(item.getEmpId());
                    record.setState(item.getState());
                    return record;
                })
                .collect(Collectors.toList());

        return this.updateBatchById(recordsToUpdate);
    }

    /**
     * 验证状态值合法性
     *
     * @param state 状态值
     */
    private void validateState(Integer state) {
        if (state == null || state < 1 || state > 5) {
            throw new IllegalArgumentException("无效的状态值，应为1-5之间");
        }
    }
}
