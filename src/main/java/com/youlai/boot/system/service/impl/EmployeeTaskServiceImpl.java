package com.youlai.boot.system.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youlai.boot.system.converter.EmployeeTaskConverter;
import com.youlai.boot.system.mapper.EmployeeTaskMapper;
import com.youlai.boot.system.model.entity.EmployeeTask;
import com.youlai.boot.system.model.query.EmployeeTaskPageQuery;
import com.youlai.boot.system.model.vo.EmployeeTaskPageVO;
import com.youlai.boot.system.service.EmployeeTaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    private final EmployeeTaskConverter employeeTaskConverter;

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

        this.baseMapper.update(null,
                new LambdaUpdateWrapper<EmployeeTask>()
                        .set(EmployeeTask::getSnCode1, null)
                        .eq(EmployeeTask::getSnCode1, snCode)
        );
        this.baseMapper.update(null,
                new LambdaUpdateWrapper<EmployeeTask>()
                        .set(EmployeeTask::getSnCode2, null)
                        .eq(EmployeeTask::getSnCode2, snCode)
        );
        this.baseMapper.update(null,
                new LambdaUpdateWrapper<EmployeeTask>()
                        .set(EmployeeTask::getSnCode3, null)
                        .eq(EmployeeTask::getSnCode3, snCode)
        );
        this.baseMapper.update(null,
                new LambdaUpdateWrapper<EmployeeTask>()
                        .set(EmployeeTask::getSnCode4, null)
                        .eq(EmployeeTask::getSnCode4, snCode)
        );
        this.baseMapper.update(null,
                new LambdaUpdateWrapper<EmployeeTask>()
                        .set(EmployeeTask::getSnCode5, null)
                        .eq(EmployeeTask::getSnCode5, snCode)
        );
        this.baseMapper.update(null,
                new LambdaUpdateWrapper<EmployeeTask>()
                        .set(EmployeeTask::getSnCode6, null)
                        .eq(EmployeeTask::getSnCode6, snCode)
        );
        this.baseMapper.update(null,
                new LambdaUpdateWrapper<EmployeeTask>()
                        .set(EmployeeTask::getSnCode7, null)
                        .eq(EmployeeTask::getSnCode7, snCode)
        );
        this.baseMapper.update(null,
                new LambdaUpdateWrapper<EmployeeTask>()
                        .set(EmployeeTask::getSnCode8, null)
                        .eq(EmployeeTask::getSnCode8, snCode)
        );
        this.baseMapper.update(null,
                new LambdaUpdateWrapper<EmployeeTask>()
                        .set(EmployeeTask::getSnCode9, null)
                        .eq(EmployeeTask::getSnCode9, snCode)
        );
        this.baseMapper.update(null,
                new LambdaUpdateWrapper<EmployeeTask>()
                        .set(EmployeeTask::getSnCode10, null)
                        .eq(EmployeeTask::getSnCode10, snCode)
        );

        log.info(">>> [独占分配] 已全局清理SN号: {} 从所有员工任务中", snCode);

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
            String assignedSlot = null;
            if (StrUtil.isBlank(employeeTask.getSnCode1())) {
                employeeTask.setSnCode1(snCode);
                assignedSlot = "sn_code1";
            } else if (StrUtil.isBlank(employeeTask.getSnCode2())) {
                employeeTask.setSnCode2(snCode);
                assignedSlot = "sn_code2";
            } else if (StrUtil.isBlank(employeeTask.getSnCode3())) {
                employeeTask.setSnCode3(snCode);
                assignedSlot = "sn_code3";
            } else if (StrUtil.isBlank(employeeTask.getSnCode4())) {
                employeeTask.setSnCode4(snCode);
                assignedSlot = "sn_code4";
            } else if (StrUtil.isBlank(employeeTask.getSnCode5())) {
                employeeTask.setSnCode5(snCode);
                assignedSlot = "sn_code5";
            } else if (StrUtil.isBlank(employeeTask.getSnCode6())) {
                employeeTask.setSnCode6(snCode);
                assignedSlot = "sn_code6";
            } else if (StrUtil.isBlank(employeeTask.getSnCode7())) {
                employeeTask.setSnCode7(snCode);
                assignedSlot = "sn_code7";
            } else if (StrUtil.isBlank(employeeTask.getSnCode8())) {
                employeeTask.setSnCode8(snCode);
                assignedSlot = "sn_code8";
            } else if (StrUtil.isBlank(employeeTask.getSnCode9())) {
                employeeTask.setSnCode9(snCode);
                assignedSlot = "sn_code9";
            } else if (StrUtil.isBlank(employeeTask.getSnCode10())) {
                employeeTask.setSnCode10(snCode);
                assignedSlot = "sn_code10";
            } else {
                log.error(">>> [独占分配-失败] 员工 {} (ID:{}) 任务已满，无法分配SN号: {}", empName, empId, snCode);
                throw new RuntimeException("该员工任务已满（最多10个）");
            }
            this.updateById(employeeTask);
            log.info(">>> [独占分配-成功] 将SN号 {} 分配到员工 {} (ID:{}) 的 {} 槽位", snCode, empName, empId, assignedSlot);
        }

        log.info("========== [独占分配完成] SN号: {} -> 员工: {} (ID:{}) ==========", snCode, empName, empId);
    }

    /**
     * 从所有员工的任务中移除指定的SN号
     *
     * @param snCode 要移除的SN号
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeSnCodeFromEmployee(String empId,  String snCode) {
        EmployeeTask employeeTask = this.getOne(new LambdaQueryWrapper<EmployeeTask>()
                .eq(EmployeeTask::getEmpId, empId)
                .eq(EmployeeTask::getIsDeleted, 0)
        );

        if (employeeTask != null) {
            // 找到匹配的SN号并清空
            if (snCode.equals(employeeTask.getSnCode1())) {
                employeeTask.setSnCode1(null);
            } else if (snCode.equals(employeeTask.getSnCode2())) {
                employeeTask.setSnCode2(null);
            } else if (snCode.equals(employeeTask.getSnCode3())) {
                employeeTask.setSnCode3(null);
            } else if (snCode.equals(employeeTask.getSnCode4())) {
                employeeTask.setSnCode4(null);
            } else if (snCode.equals(employeeTask.getSnCode5())) {
                employeeTask.setSnCode5(null);
            } else if (snCode.equals(employeeTask.getSnCode6())) {
                employeeTask.setSnCode6(null);
            } else if (snCode.equals(employeeTask.getSnCode7())) {
                employeeTask.setSnCode7(null);
            } else if (snCode.equals(employeeTask.getSnCode8())) {
                employeeTask.setSnCode8(null);
            } else if (snCode.equals(employeeTask.getSnCode9())) {
                employeeTask.setSnCode9(null);
            } else if (snCode.equals(employeeTask.getSnCode10())) {
                employeeTask.setSnCode10(null);
            }
            this.updateById(employeeTask);
            log.info("从员工任务中移除SN号: {}", snCode);
        }
    }
}