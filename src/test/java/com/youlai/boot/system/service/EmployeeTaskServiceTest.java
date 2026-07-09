package com.youlai.boot.system.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.youlai.boot.system.mapper.EmployeeTaskMapper;
import com.youlai.boot.system.model.entity.EmployeeTask;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

/**
 * FQC任务同步功能测试
 *
 * @author lijun
 * @since 2026/07/08
 */
@Slf4j
@SpringBootTest
public class EmployeeTaskServiceTest {

    @Autowired
    private EmployeeTaskService employeeTaskService;

    @Autowired
    private EmployeeTaskMapper employeeTaskMapper;

    /**
     * 测试复制昨日任务到今日功能
     */
    @Test
    public void testCopyYesterdayTasksToToday() {
        log.info("========== [单元测试] 开始测试复制昨日任务功能 ==========");

        // 1. 查看昨天的任务数量
        LocalDate yesterday = LocalDate.now().minusDays(1);
        List<EmployeeTask> yesterdayTasks = employeeTaskMapper.selectList(
                new LambdaQueryWrapper<EmployeeTask>()
                        .ge(EmployeeTask::getCreateTime, yesterday.atStartOfDay())
                        .lt(EmployeeTask::getCreateTime, LocalDate.now().atStartOfDay())
                        .eq(EmployeeTask::getIsDeleted, 0)
        );
        log.info("昨天创建的任务数量: {}", yesterdayTasks.size());

        // 2. 执行复制
        int copiedCount = employeeTaskService.copyYesterdayTasksToToday();
        log.info("复制完成，共复制 {} 条记录", copiedCount);

        // 3. 验证今天创建的任务
        LocalDate today = LocalDate.now();
        List<EmployeeTask> todayTasks = employeeTaskMapper.selectList(
                new LambdaQueryWrapper<EmployeeTask>()
                        .ge(EmployeeTask::getCreateTime, today.atStartOfDay())
                        .eq(EmployeeTask::getIsDeleted, 0)
        );
        log.info("今天创建的任务数量: {}", todayTasks.size());

        // 4. 打印每个员工的SN号情况
        for (EmployeeTask task : todayTasks) {
            log.info("员工: {} (ID:{}) - SN列表: [{}, {}, {}, {}, {}]",
                    task.getEmpName(),
                    task.getEmpId(),
                    task.getSnCode1(),
                    task.getSnCode2(),
                    task.getSnCode3(),
                    task.getSnCode4(),
                    task.getSnCode5()
            );
        }

        log.info("========== [单元测试] 测试完成 ==========");
    }
}
