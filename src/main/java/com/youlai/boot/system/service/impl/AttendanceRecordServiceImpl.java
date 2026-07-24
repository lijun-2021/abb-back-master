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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
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
@Slf4j
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
        if (form.getItems() != null && !form.getItems().isEmpty()) {
            return batchUpdateAttendanceState(form.getItems());
        } else {
            return singleUpdateAttendanceState(form.getEmpId(), form.getAmState(), form.getPmState(), form.getRecordDate());
        }
    }

    private boolean singleUpdateAttendanceState(String empId, Integer amState, Integer pmState, LocalDate recordDate) {
        if (empId == null || empId.trim().isEmpty()) {
            throw new IllegalArgumentException("员工ID不能为空");
        }

        if (recordDate == null) {
            recordDate = LocalDate.now();
        }

        if (amState != null) {
            validateState(amState);
        }
        if (pmState != null) {
            validateState(pmState);
        }

        AttendanceRecord existingRecord = this.lambdaQuery()
                .eq(AttendanceRecord::getEmpId, empId)
                .eq(AttendanceRecord::getRecordDate, recordDate)
                .one();

        if (existingRecord == null) {
            existingRecord = new AttendanceRecord();
            existingRecord.setEmpId(empId);
            existingRecord.setRecordDate(recordDate);
        }

        if (amState != null) {
            existingRecord.setAmState(amState);
        }
        if (pmState != null) {
            existingRecord.setPmState(pmState);
        }

        return existingRecord.getId() == null ? this.save(existingRecord) : this.updateById(existingRecord);
    }

    private boolean batchUpdateAttendanceState(List<AttendanceRecordForm.AttendanceStateItem> items) {
        for (AttendanceRecordForm.AttendanceStateItem item : items) {
            if (item.getAmState() != null) {
                validateState(item.getAmState());
            }
            if (item.getPmState() != null) {
                validateState(item.getPmState());
            }
        }

        List<String> empIds = items.stream()
                .map(AttendanceRecordForm.AttendanceStateItem::getEmpId)
                .collect(Collectors.toList());

        LocalDate today = LocalDate.now();

        List<AttendanceRecord> existingRecords = this.lambdaQuery()
                .in(AttendanceRecord::getEmpId, empIds)
                .list();

        Map<String, AttendanceRecord> recordMap = existingRecords.stream()
                .collect(Collectors.toMap(
                        record -> record.getEmpId() + "_" + record.getRecordDate(),
                        record -> record,
                        (existing, replacement) -> existing
                ));

        List<AttendanceRecord> recordsToUpdate = items.stream()
                .map(item -> {
                    LocalDate recordDate = item.getRecordDate() != null ? item.getRecordDate() : today;
                    String key = item.getEmpId() + "_" + recordDate;
                    AttendanceRecord record = recordMap.get(key);

                    if (record == null) {
                        record = new AttendanceRecord();
                        record.setEmpId(item.getEmpId());
                        record.setRecordDate(recordDate);
                    }

                    if (item.getAmState() != null) {
                        record.setAmState(item.getAmState());
                    }
                    if (item.getPmState() != null) {
                        record.setPmState(item.getPmState());
                    }

                    return record;
                })
                .collect(Collectors.toList());

        List<AttendanceRecord> updateRecords = recordsToUpdate.stream()
                .filter(record -> record.getId() != null)
                .collect(Collectors.toList());

        List<AttendanceRecord> insertRecords = recordsToUpdate.stream()
                .filter(record -> record.getId() == null)
                .collect(Collectors.toList());

        boolean updateSuccess = updateRecords.isEmpty() || this.updateBatchById(updateRecords);
        boolean insertSuccess = insertRecords.isEmpty() || this.saveBatch(insertRecords);

        return updateSuccess && insertSuccess;
    }

    private void validateState(Integer state) {
        if (state == null || state < 1 || state > 5) {
            throw new IllegalArgumentException("无效的状态值，应为1-5之间");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int fillMissingDaysAttendance() {
        log.info("========== [启动检查] 开始检测缺失天数的考勤数据 ==========");
        LocalDate today = LocalDate.now();
        log.info(">>> [启动检查] 当前日期: {}", today);

        List<AttendanceRecord> latestRecords = this.list(new com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper<AttendanceRecord>()
                .orderByDesc(AttendanceRecord::getRecordDate)
                .last("LIMIT 1")
        );
        log.info(">>> [启动检查] 查询到最新记录数量: {}", latestRecords.size());
        if (latestRecords.isEmpty()) {
            log.info(">>> [启动检查] 数据库中无任何考勤数据，跳过");
            return 0;
        }
        LocalDate latestDate = latestRecords.get(0).getRecordDate();
        log.info(">>> [启动检查] 数据库中最新考勤数据日期: {}", latestDate);

        if (latestDate.equals(today)) {
            log.info(">>> [启动检查] 今日考勤数据已存在，无需补充");
            return 0;
        }

        LocalDate yesterday = today.minusDays(1);
        if (latestDate.equals(yesterday)) {
            log.info(">>> [启动检查] 最新数据是昨天，执行正常的今日复制");
            return copyAttendanceFromDateToDate(yesterday, today);
        }

        LocalDate startDate = latestDate.plusDays(1);
        int totalCopied = 0;
        log.info(">>> [启动检查] 发现缺失天数: {} 天 (从 {} 到 {}), 开始逐天补充",
                java.time.temporal.ChronoUnit.DAYS.between(startDate, today) + 1, startDate, today);
        LocalDate currentDate = startDate;
        while (!currentDate.isAfter(today)) {
            LocalDate sourceDate = currentDate.minusDays(1);
            int copied = copyAttendanceFromDateToDate(sourceDate, currentDate);
            totalCopied += copied;
            log.info(">>> [启动检查] 补充 {} 的考勤: 复制了 {} 条记录", currentDate, copied);
            currentDate = currentDate.plusDays(1);
        }
        log.info("========== [启动检查完成] 共补充 {} 条考勤记录 ==========", totalCopied);
        return totalCopied;
    }

    private int copyAttendanceFromDateToDate(LocalDate sourceDate, LocalDate targetDate) {
        log.info(">>> [复制考勤] 从 {} 复制到 {}", sourceDate, targetDate);

        try {
            List<AttendanceRecord> sourceRecords = this.list(new com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper<AttendanceRecord>()
                    .eq(AttendanceRecord::getRecordDate, sourceDate)
            );
            log.info(">>> [复制考勤] 查询到源数据数量: {}", sourceRecords.size());
            
            if (sourceRecords.isEmpty()) {
                log.info(">>> [复制考勤] {} 无考勤数据，跳过", sourceDate);
                return 0;
            }

            List<AttendanceRecord> newRecords = new ArrayList<>();
            for (AttendanceRecord oldRecord : sourceRecords) {
                AttendanceRecord newRecord = new AttendanceRecord();
                newRecord.setEmpId(oldRecord.getEmpId());
                newRecord.setEmpName(oldRecord.getEmpName());
                newRecord.setEmpTeam(oldRecord.getEmpTeam());
                newRecord.setRecordDate(targetDate);
                // 默认全天在岗：上午状态和下午状态都设为1
                newRecord.setAmState(1);
                newRecord.setPmState(1);
                newRecords.add(newRecord);
            }

            log.info(">>> [复制考勤] 准备插入 {} 条新记录", newRecords.size());
            if (!newRecords.isEmpty()) {
                boolean success = this.saveBatch(newRecords);
                if (success) {
                    log.info(">>> [复制考勤] 成功复制 {} 条记录到 {}", newRecords.size(), targetDate);
                    return newRecords.size();
                } else {
                    throw new RuntimeException("saveBatch返回false，复制考勤数据到 " + targetDate + " 失败");
                }
            }
        } catch (Exception e) {
            log.error(">>> [复制考勤] 复制失败 - 异常类型: {}", e.getClass().getName());
            log.error(">>> [复制考勤] 复制失败 - 异常消息: {}", e.getMessage());
            log.error(">>> [复制考勤] 复制失败 - 异常堆栈:", e);
            throw e;
        }
        return 0;
    }
}
