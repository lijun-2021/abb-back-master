package com.youlai.boot.system.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youlai.boot.system.mapper.EmployeeTaskMapper;
import com.youlai.boot.system.mapper.SwitchCabinetMapper;
import com.youlai.boot.system.model.entity.EmployeeTask;
import com.youlai.boot.system.model.entity.SwitchCabinet;
import com.youlai.boot.system.model.query.EmployeeTaskPageQuery;
import com.youlai.boot.system.model.vo.EmployeeTaskPageVO;
import com.youlai.boot.system.service.EmployeeTaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    private static final int MAX_SN_SLOTS = 20;
    private final SwitchCabinetMapper switchCabinetMapper;

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
     * 为员工分配SN号任务（按顺序填充sn_code1~sn_code20）
     *
     * @param empId   员工ID
     * @param empName 员工姓名
     * @param snCode  SN号
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void assignSnCodeToEmployee(String empId, String empName, String snCode) {
        log.info("========== [独占分配] 开始分配SN号: {} 给员工: {} (ID:{}) ==========", snCode, empName, empId);

        LocalDate today = LocalDate.now();

        // 1. 查询当天所有员工的任务，清理该 SN 号在其他员工处的分配
        List<EmployeeTask> allEmployeeTasks = this.list(new LambdaQueryWrapper<EmployeeTask>()
                .eq(EmployeeTask::getTaskDate, today)
                .eq(EmployeeTask::getIsDeleted, 0)
        );

        for (EmployeeTask task : allEmployeeTasks) {
            boolean found = removeSnCodeFromTask(task, snCode);

            if (found && !task.getEmpId().equals(empId)) {
                compactSnCodes(task);
                this.update(null, buildUpdateWrapper(task));
                log.info(">>> [独占分配] 已从员工 {} (ID:{}) 移除SN号: {} 并自动前移", task.getEmpName(), task.getEmpId(), snCode);
            }
        }

        log.info(">>> [独占分配] 已全局清理SN号: {} 从所有其他员工任务中", snCode);

        // 2. 处理目标员工（新员工）的任务分配
        EmployeeTask employeeTask = this.getOne(new LambdaQueryWrapper<EmployeeTask>()
                .eq(EmployeeTask::getEmpId, empId)
                .eq(EmployeeTask::getTaskDate, today)
                .eq(EmployeeTask::getIsDeleted, 0)
        );

        if (employeeTask == null) {
            employeeTask = new EmployeeTask();
            employeeTask.setEmpId(empId);
            employeeTask.setEmpName(empName);
            employeeTask.setTaskType(2);
            employeeTask.setTaskDate(today);
            employeeTask.setSnCode1(snCode);
            this.save(employeeTask);
            log.info(">>> [独占分配-新建] 为员工 {} (ID:{}) 创建新记录，分配SN号到 sn_code1: {}", empName, empId, snCode);
        } else {
            compactSnCodes(employeeTask);

            if (!containsSnCode(employeeTask, snCode)) {
                String assignedSlot = assignToFirstEmptySlot(employeeTask, snCode);
                if (assignedSlot == null) {
                    log.error(">>> [独占分配-失败] 员工 {} (ID:{}) 任务已满，无法分配SN号: {}", empName, empId, snCode);
                    throw new RuntimeException("该员工任务已满（最多" + MAX_SN_SLOTS + "个）");
                }
            }
            this.update(null, buildUpdateWrapper(employeeTask));
            log.info(">>> [独占分配-成功] 将SN号 {} 分配到员工 {} (ID:{})", snCode, empName, empId);
        }

        log.info("========== [独占分配完成] SN号: {} -> 员工: {} (ID:{}) ==========", snCode, empName, empId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeSnCodeFromEmployee(String empId, String snCode) {
        LocalDate today = LocalDate.now();
        EmployeeTask employeeTask = this.getOne(new LambdaQueryWrapper<EmployeeTask>()
                .eq(EmployeeTask::getEmpId, empId)
                .eq(EmployeeTask::getTaskDate, today)
                .eq(EmployeeTask::getIsDeleted, 0)
        );

        if (employeeTask != null) {
            boolean removed = removeSnCodeFromTask(employeeTask, snCode);

            if (removed) {
                compactSnCodes(employeeTask);
                this.update(null, buildUpdateWrapper(employeeTask));
                log.info("从员工 {} (ID:{}) 任务中移除SN号: {} 并自动前移填充", employeeTask.getEmpName(), empId, snCode);
            }
        }
    }

    /**
     * 复制昨日任务数据到今日
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int copyYesterdayTasksToToday() {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        LocalDate today = LocalDate.now();
        return copyTasksFromDateToDate(yesterday, today);
    }

    /**
     * 检测并补充缺失天数的任务数据
     * 应用启动时自动调用，处理因关机/停电等原因导致的定时任务未执行问题
     * 会从最近有数据的日期开始，逐天复制到今天，保留每一天的任务记录
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int fillMissingDaysTasks() {
        log.info("========== [启动检查] 开始检测缺失天数的任务数据 ==========");
        LocalDate today = LocalDate.now();

        // 1. 查询数据库中最近有任务数据的日期（按 task_date）
        List<EmployeeTask> latestTasks = this.list(new LambdaQueryWrapper<EmployeeTask>()
                .eq(EmployeeTask::getIsDeleted, 0)
                .orderByDesc(EmployeeTask::getTaskDate)
                .last("LIMIT 1")
        );
        if (latestTasks.isEmpty()) {
            log.info(">>> [启动检查] 数据库中无任何任务数据，跳过");
            return 0;
        }
        LocalDate latestDate = latestTasks.get(0).getTaskDate();
        log.info(">>> [启动检查] 数据库中最新任务数据日期: {}", latestDate);

        // 2. 今天数据已存在，无需补充
        if (latestDate.equals(today)) {
            log.info(">>> [启动检查] 今日任务数据已存在，无需补充");
            return 0;
        }

        // 3. 最新数据是昨天，执行正常复制
        LocalDate yesterday = today.minusDays(1);
        if (latestDate.equals(yesterday)) {
            log.info(">>> [启动检查] 最新数据是昨天，执行正常的今日复制");
            return copyTasksFromDateToDate(yesterday, today);
        }

        // 4. 存在多天缺失，逐天补充（保留每一天的数据）
        LocalDate startDate = latestDate.plusDays(1);
        int totalCopied = 0;
        log.info(">>> [启动检查] 发现缺失天数: {} 天 (从 {} 到 {}), 开始逐天补充",
                java.time.temporal.ChronoUnit.DAYS.between(startDate, today) + 1, startDate, today);
        LocalDate currentDate = startDate;
        while (!currentDate.isAfter(today)) {
            LocalDate sourceDate = currentDate.minusDays(1);
            int copied = copyTasksFromDateToDate(sourceDate, currentDate);
            totalCopied += copied;
            log.info(">>> [启动检查] 补充 {} 的任务: 复制了 {} 条记录", currentDate, copied);
            currentDate = currentDate.plusDays(1);
        }
        log.info("========== [启动检查完成] 共补充 {} 条任务记录 ==========", totalCopied);
        return totalCopied;
    }

    /**
     * 核心复制逻辑：从源日期的任务数据复制到目标日期，过滤已完成的SN号
     * 按 task_date 字段查询源数据，新记录的 task_date 设为目标日期
     */
    private int copyTasksFromDateToDate(LocalDate sourceDate, LocalDate targetDate) {
        log.info(">>> [复制任务] 从 {} 复制到 {}", sourceDate, targetDate);

        // 按 task_date 查询源日期的任务数据
        List<EmployeeTask> sourceTasks = this.list(new LambdaQueryWrapper<EmployeeTask>()
                .eq(EmployeeTask::getTaskDate, sourceDate)
                .eq(EmployeeTask::getIsDeleted, 0)
        );
        if (sourceTasks.isEmpty()) {
            log.info(">>> [复制任务] {} 无任务数据，跳过", sourceDate);
            return 0;
        }

        // 获取开关柜状态
        List<SwitchCabinet> allSwitchCabinets = switchCabinetMapper.selectList(new LambdaQueryWrapper<>());
        Map<String, Integer> snCodeStatusMap = allSwitchCabinets.stream()
                .filter(sc -> sc.getSnCode() != null)
                .collect(Collectors.toMap(
                        SwitchCabinet::getSnCode,
                        sc -> {
                            if (sc.getFunctionStarttime() == null) return 0;      // 未完成
                            else if (sc.getFunctionEndtime() == null) return 1;   // 进行中
                            else return 2;                                         // 已完成
                        },
                        (existing, replacement) -> existing
                ));

        // 创建目标日期的新任务，过滤已完成SN号
        LocalDateTime now = LocalDateTime.now();
        List<EmployeeTask> newTasks = new ArrayList<>();
        for (EmployeeTask oldTask : sourceTasks) {
            EmployeeTask newTask = new EmployeeTask();
            newTask.setEmpId(oldTask.getEmpId());
            newTask.setEmpName(oldTask.getEmpName());
            newTask.setEmpTeam(oldTask.getEmpTeam());
            newTask.setTaskType(oldTask.getTaskType());

            String[] oldSnCodes = getSnCodes(oldTask);
            String[] newSnCodes = new String[MAX_SN_SLOTS];
            int index = 0;
            for (String snCode : oldSnCodes) {
                if (StrUtil.isNotBlank(snCode)) {
                    Integer status = snCodeStatusMap.get(snCode);
                    if (status == null || status == 0 || status == 1) {
                        newSnCodes[index++] = snCode;
                    }
                }
            }

            setSnCodes(newTask, newSnCodes);
            newTask.setTaskDate(targetDate);
            newTask.setCreateTime(now);
            newTask.setUpdateTime(now);
            newTask.setIsDeleted(0);
            newTasks.add(newTask);
        }

        if (!newTasks.isEmpty()) {
            boolean success = this.saveBatch(newTasks);
            if (success) {
                log.info(">>> [复制任务] 成功复制 {} 条记录到 {}", newTasks.size(), targetDate);
                return newTasks.size();
            } else {
                throw new RuntimeException("复制任务数据到 " + targetDate + " 失败");
            }
        }
        return 0;
    }

    // ==================== 私有工具方法 ====================

    private String[] getSnCodes(EmployeeTask task) {
        return new String[]{
                task.getSnCode1(), task.getSnCode2(), task.getSnCode3(), task.getSnCode4(), task.getSnCode5(),
                task.getSnCode6(), task.getSnCode7(), task.getSnCode8(), task.getSnCode9(), task.getSnCode10(),
                task.getSnCode11(), task.getSnCode12(), task.getSnCode13(), task.getSnCode14(), task.getSnCode15(),
                task.getSnCode16(), task.getSnCode17(), task.getSnCode18(), task.getSnCode19(), task.getSnCode20()
        };
    }

    private void setSnCodes(EmployeeTask task, String[] snCodes) {
        task.setSnCode1(snCodes[0]);
        task.setSnCode2(snCodes[1]);
        task.setSnCode3(snCodes[2]);
        task.setSnCode4(snCodes[3]);
        task.setSnCode5(snCodes[4]);
        task.setSnCode6(snCodes[5]);
        task.setSnCode7(snCodes[6]);
        task.setSnCode8(snCodes[7]);
        task.setSnCode9(snCodes[8]);
        task.setSnCode10(snCodes[9]);
        task.setSnCode11(snCodes[10]);
        task.setSnCode12(snCodes[11]);
        task.setSnCode13(snCodes[12]);
        task.setSnCode14(snCodes[13]);
        task.setSnCode15(snCodes[14]);
        task.setSnCode16(snCodes[15]);
        task.setSnCode17(snCodes[16]);
        task.setSnCode18(snCodes[17]);
        task.setSnCode19(snCodes[18]);
        task.setSnCode20(snCodes[19]);
    }

    private boolean removeSnCodeFromTask(EmployeeTask task, String snCode) {
        String[] codes = getSnCodes(task);
        boolean found = false;
        for (int i = 0; i < codes.length; i++) {
            if (snCode.equals(codes[i])) {
                codes[i] = null;
                found = true;
                break;
            }
        }
        if (found) {
            setSnCodes(task, codes);
        }
        return found;
    }

    private boolean containsSnCode(EmployeeTask task, String snCode) {
        String[] codes = getSnCodes(task);
        for (String code : codes) {
            if (snCode.equals(code)) {
                return true;
            }
        }
        return false;
    }

    private String assignToFirstEmptySlot(EmployeeTask task, String snCode) {
        String[] codes = getSnCodes(task);
        for (int i = 0; i < codes.length; i++) {
            if (StrUtil.isBlank(codes[i])) {
                codes[i] = snCode;
                setSnCodes(task, codes);
                return "sn_code" + (i + 1);
            }
        }
        return null;
    }

    private LambdaUpdateWrapper<EmployeeTask> buildUpdateWrapper(EmployeeTask task) {
        return new LambdaUpdateWrapper<EmployeeTask>()
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
                .set(EmployeeTask::getSnCode10, task.getSnCode10())
                .set(EmployeeTask::getSnCode11, task.getSnCode11())
                .set(EmployeeTask::getSnCode12, task.getSnCode12())
                .set(EmployeeTask::getSnCode13, task.getSnCode13())
                .set(EmployeeTask::getSnCode14, task.getSnCode14())
                .set(EmployeeTask::getSnCode15, task.getSnCode15())
                .set(EmployeeTask::getSnCode16, task.getSnCode16())
                .set(EmployeeTask::getSnCode17, task.getSnCode17())
                .set(EmployeeTask::getSnCode18, task.getSnCode18())
                .set(EmployeeTask::getSnCode19, task.getSnCode19())
                .set(EmployeeTask::getSnCode20, task.getSnCode20());
    }

    /**
     * 压缩SN号，将后面的SN号依次前移，保证从sn_code1开始连续填充
     *
     * @param employeeTask 员工任务对象
     */
    private void compactSnCodes(EmployeeTask employeeTask) {
        String[] originalSnCodes = getSnCodes(employeeTask);
        String[] compactedSnCodes = new String[MAX_SN_SLOTS];
        int writeIndex = 0;

        for (int readIndex = 0; readIndex < MAX_SN_SLOTS; readIndex++) {
            if (StrUtil.isNotBlank(originalSnCodes[readIndex])) {
                compactedSnCodes[writeIndex] = originalSnCodes[readIndex];
                writeIndex++;
            }
        }

        setSnCodes(employeeTask, compactedSnCodes);
    }
}



