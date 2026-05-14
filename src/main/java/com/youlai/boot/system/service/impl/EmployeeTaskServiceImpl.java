package com.youlai.boot.system.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youlai.boot.system.mapper.EmployeeTaskMapper;
import com.youlai.boot.system.model.entity.EmployeeTask;
import com.youlai.boot.system.model.query.EmployeeTaskPageQuery;
import com.youlai.boot.system.model.vo.EmployeeTaskPageVO;
import com.youlai.boot.system.service.EmployeeTaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 员工任务业务实现类
 *
* @author lijun
* @since 2026/04/23
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeTaskServiceImpl extends ServiceImpl<EmployeeTaskMapper, EmployeeTask> implements EmployeeTaskService {

    /**
     * 获取员工任务分页列表
     *
     * @param queryParams 查询参数
     * @return 员工任务分页列表
     */
    @Override
    public IPage<EmployeeTaskPageVO> getEmployeeTaskPage(EmployeeTaskPageQuery queryParams) {
        Page<EmployeeTaskPageVO> page = new Page<>(queryParams.getPageNum(), queryParams.getPageSize());
        return this.baseMapper.getEmployeeTaskPage(page, queryParams);
    }

    /**
     * 为员工分配SN号任务（按顺序填充sn_code1~sn_code10）
     *
     * @param empId   员工ID
     * @param empName 员工姓名
     * @param snCode  SN号
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void assignSnCodeToEmployee(String empId, String empName, String snCode) {
        log.info("========== [独占分配] 开始分配SN号: {} 给员工: {} (ID:{}) ==========", snCode, empName, empId);

        // 1. 查询所有员工的任务，清理该 SN 号在其他员工处的分配
        List<EmployeeTask> allEmployeeTasks = this.list(new LambdaQueryWrapper<EmployeeTask>()
                .eq(EmployeeTask::getIsDeleted, 0)
        );

        for (EmployeeTask task : allEmployeeTasks) {
            boolean found = false;
            if (snCode.equals(task.getSnCode1())) { task.setSnCode1(null); found = true; }
            else if (snCode.equals(task.getSnCode2())) { task.setSnCode2(null); found = true; }
            else if (snCode.equals(task.getSnCode3())) { task.setSnCode3(null); found = true; }
            else if (snCode.equals(task.getSnCode4())) { task.setSnCode4(null); found = true; }
            else if (snCode.equals(task.getSnCode5())) { task.setSnCode5(null); found = true; }
            else if (snCode.equals(task.getSnCode6())) { task.setSnCode6(null); found = true; }
            else if (snCode.equals(task.getSnCode7())) { task.setSnCode7(null); found = true; }
            else if (snCode.equals(task.getSnCode8())) { task.setSnCode8(null); found = true; }
            else if (snCode.equals(task.getSnCode9())) { task.setSnCode9(null); found = true; }
            else if (snCode.equals(task.getSnCode10())) { task.setSnCode10(null); found = true; }

            if (found && !task.getEmpId().equals(empId)) {
                compactSnCodes(task);
                // 【修复】使用显式 set 强制更新所有字段
                this.update(null, new LambdaUpdateWrapper<EmployeeTask>()
                        .eq(EmployeeTask::getId, task.getId())
                        .set(EmployeeTask::getSnCode1, task.getSnCode1())
                        .set(EmployeeTask::getSnCode2, task.getSnCode2())
                        .set(EmployeeTask::getSnCode3, task.getSnCode3())
                        .set(EmployeeTask::getSnCode4, task.getSnCode4())
                        .set(EmployeeTask::getSnCode5, task.getSnCode5())
                        .set(EmployeeTask::getSnCode6, task.getSnCode6())
                        .set(EmployeeTask::getSnCode7, task.getSnCode7())
                        .set(EmployeeTask::getSnCode8, task.getSnCode8())
                        .set(EmployeeTask::getSnCode9, task.getSnCode9())
                        .set(EmployeeTask::getSnCode10, task.getSnCode10()));
                log.info(">>> [独占分配] 已从员工 {} (ID:{}) 移除SN号: {} 并自动前移", task.getEmpName(), task.getEmpId(), snCode);
            }
        }

        log.info(">>> [独占分配] 已全局清理SN号: {} 从所有其他员工任务中", snCode);

        // 2. 处理目标员工（新员工）的任务分配
        EmployeeTask employeeTask = this.getOne(new LambdaQueryWrapper<EmployeeTask>()
                .eq(EmployeeTask::getEmpId, empId)
                .eq(EmployeeTask::getIsDeleted, 0)
        );

        if (employeeTask == null) {
            employeeTask = new EmployeeTask();
            employeeTask.setEmpId(empId);
            employeeTask.setEmpName(empName);
            employeeTask.setTaskType(2);
            employeeTask.setSnCode1(snCode);
            this.save(employeeTask);
            log.info(">>> [独占分配-新建] 为员工 {} (ID:{}) 创建新记录，分配SN号到 sn_code1: {}", empName, empId, snCode);
        } else {
            compactSnCodes(employeeTask);

            boolean alreadyExists = snCode.equals(employeeTask.getSnCode1()) ||
                    snCode.equals(employeeTask.getSnCode2()) ||
                    snCode.equals(employeeTask.getSnCode3()) ||
                    snCode.equals(employeeTask.getSnCode4()) ||
                    snCode.equals(employeeTask.getSnCode5()) ||
                    snCode.equals(employeeTask.getSnCode6()) ||
                    snCode.equals(employeeTask.getSnCode7()) ||
                    snCode.equals(employeeTask.getSnCode8()) ||
                    snCode.equals(employeeTask.getSnCode9()) ||
                    snCode.equals(employeeTask.getSnCode10());

            if (!alreadyExists) {
                String assignedSlot = null;
                if (StrUtil.isBlank(employeeTask.getSnCode1())) {
                    employeeTask.setSnCode1(snCode); assignedSlot = "sn_code1";
                } else if (StrUtil.isBlank(employeeTask.getSnCode2())) {
                    employeeTask.setSnCode2(snCode); assignedSlot = "sn_code2";
                } else if (StrUtil.isBlank(employeeTask.getSnCode3())) {
                    employeeTask.setSnCode3(snCode); assignedSlot = "sn_code3";
                } else if (StrUtil.isBlank(employeeTask.getSnCode4())) {
                    employeeTask.setSnCode4(snCode); assignedSlot = "sn_code4";
                } else if (StrUtil.isBlank(employeeTask.getSnCode5())) {
                    employeeTask.setSnCode5(snCode); assignedSlot = "sn_code5";
                } else if (StrUtil.isBlank(employeeTask.getSnCode6())) {
                    employeeTask.setSnCode6(snCode); assignedSlot = "sn_code6";
                } else if (StrUtil.isBlank(employeeTask.getSnCode7())) {
                    employeeTask.setSnCode7(snCode); assignedSlot = "sn_code7";
                } else if (StrUtil.isBlank(employeeTask.getSnCode8())) {
                    employeeTask.setSnCode8(snCode); assignedSlot = "sn_code8";
                } else if (StrUtil.isBlank(employeeTask.getSnCode9())) {
                    employeeTask.setSnCode9(snCode); assignedSlot = "sn_code9";
                } else if (StrUtil.isBlank(employeeTask.getSnCode10())) {
                    employeeTask.setSnCode10(snCode); assignedSlot = "sn_code10";
                } else {
                    log.error(">>> [独占分配-失败] 员工 {} (ID:{}) 任务已满，无法分配SN号: {}", empName, empId, snCode);
                    throw new RuntimeException("该员工任务已满（最多10个）");
                }
            }
            // 【修复】使用显式 set 强制更新
            this.update(null, new LambdaUpdateWrapper<EmployeeTask>()
                    .eq(EmployeeTask::getId, employeeTask.getId())
                    .set(EmployeeTask::getSnCode1, employeeTask.getSnCode1())
                    .set(EmployeeTask::getSnCode2, employeeTask.getSnCode2())
                    .set(EmployeeTask::getSnCode3, employeeTask.getSnCode3())
                    .set(EmployeeTask::getSnCode4, employeeTask.getSnCode4())
                    .set(EmployeeTask::getSnCode5, employeeTask.getSnCode5())
                    .set(EmployeeTask::getSnCode6, employeeTask.getSnCode6())
                    .set(EmployeeTask::getSnCode7, employeeTask.getSnCode7())
                    .set(EmployeeTask::getSnCode8, employeeTask.getSnCode8())
                    .set(EmployeeTask::getSnCode9, employeeTask.getSnCode9())
                    .set(EmployeeTask::getSnCode10, employeeTask.getSnCode10()));
            log.info(">>> [独占分配-成功] 将SN号 {} 分配到员工 {} (ID:{})", snCode, empName, empId);
        }

        log.info("========== [独占分配完成] SN号: {} -> 员工: {} (ID:{}) ==========", snCode, empName, empId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeSnCodeFromEmployee(String empId, String snCode) {
        EmployeeTask employeeTask = this.getOne(new LambdaQueryWrapper<EmployeeTask>()
                .eq(EmployeeTask::getEmpId, empId)
                .eq(EmployeeTask::getIsDeleted, 0)
        );

        if (employeeTask != null) {
            boolean removed = false;
            if (snCode.equals(employeeTask.getSnCode1())) { employeeTask.setSnCode1(null); removed = true; }
            else if (snCode.equals(employeeTask.getSnCode2())) { employeeTask.setSnCode2(null); removed = true; }
            else if (snCode.equals(employeeTask.getSnCode3())) { employeeTask.setSnCode3(null); removed = true; }
            else if (snCode.equals(employeeTask.getSnCode4())) { employeeTask.setSnCode4(null); removed = true; }
            else if (snCode.equals(employeeTask.getSnCode5())) { employeeTask.setSnCode5(null); removed = true; }
            else if (snCode.equals(employeeTask.getSnCode6())) { employeeTask.setSnCode6(null); removed = true; }
            else if (snCode.equals(employeeTask.getSnCode7())) { employeeTask.setSnCode7(null); removed = true; }
            else if (snCode.equals(employeeTask.getSnCode8())) { employeeTask.setSnCode8(null); removed = true; }
            else if (snCode.equals(employeeTask.getSnCode9())) { employeeTask.setSnCode9(null); removed = true; }
            else if (snCode.equals(employeeTask.getSnCode10())) { employeeTask.setSnCode10(null); removed = true; }

            if (removed) {
                compactSnCodes(employeeTask);
                // 【修复】使用显式 set 强制同步
                this.update(null, new LambdaUpdateWrapper<EmployeeTask>()
                        .eq(EmployeeTask::getId, employeeTask.getId())
                        .set(EmployeeTask::getSnCode1, employeeTask.getSnCode1())
                        .set(EmployeeTask::getSnCode2, employeeTask.getSnCode2())
                        .set(EmployeeTask::getSnCode3, employeeTask.getSnCode3())
                        .set(EmployeeTask::getSnCode4, employeeTask.getSnCode4())
                        .set(EmployeeTask::getSnCode5, employeeTask.getSnCode5())
                        .set(EmployeeTask::getSnCode6, employeeTask.getSnCode6())
                        .set(EmployeeTask::getSnCode7, employeeTask.getSnCode7())
                        .set(EmployeeTask::getSnCode8, employeeTask.getSnCode8())
                        .set(EmployeeTask::getSnCode9, employeeTask.getSnCode9())
                        .set(EmployeeTask::getSnCode10, employeeTask.getSnCode10()));
                log.info("从员工 {} (ID:{}) 任务中移除SN号: {} 并自动前移填充", employeeTask.getEmpName(), empId, snCode);
            }
        }
    }

    /**
     * 压缩SN号，将后面的SN号依次前移，保证从sn_code1开始连续填充
     *
     * @param employeeTask 员工任务对象
     */
    private void compactSnCodes(EmployeeTask employeeTask) {
        String[] originalSnCodes = new String[10];
        originalSnCodes[0] = employeeTask.getSnCode1();
        originalSnCodes[1] = employeeTask.getSnCode2();
        originalSnCodes[2] = employeeTask.getSnCode3();
        originalSnCodes[3] = employeeTask.getSnCode4();
        originalSnCodes[4] = employeeTask.getSnCode5();
        originalSnCodes[5] = employeeTask.getSnCode6();
        originalSnCodes[6] = employeeTask.getSnCode7();
        originalSnCodes[7] = employeeTask.getSnCode8();
        originalSnCodes[8] = employeeTask.getSnCode9();
        originalSnCodes[9] = employeeTask.getSnCode10();

        String[] compactedSnCodes = new String[10];
        int writeIndex = 0;

        for (int readIndex = 0; readIndex < 10; readIndex++) {
            if (StrUtil.isNotBlank(originalSnCodes[readIndex])) {
                compactedSnCodes[writeIndex] = originalSnCodes[readIndex];
                writeIndex++;
            }
        }

        employeeTask.setSnCode1(compactedSnCodes[0]);
        employeeTask.setSnCode2(compactedSnCodes[1]);
        employeeTask.setSnCode3(compactedSnCodes[2]);
        employeeTask.setSnCode4(compactedSnCodes[3]);
        employeeTask.setSnCode5(compactedSnCodes[4]);
        employeeTask.setSnCode6(compactedSnCodes[5]);
        employeeTask.setSnCode7(compactedSnCodes[6]);
        employeeTask.setSnCode8(compactedSnCodes[7]);
        employeeTask.setSnCode9(compactedSnCodes[8]);
        employeeTask.setSnCode10(compactedSnCodes[9]);
    }
}