package com.youlai.boot.system.handler;

import com.youlai.boot.system.service.EmployeeTaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * FQC员工任务同步定时任务
 * 每天凌晨自动复制昨天的任务数据到今天，只保留未完成的开关柜任务
 *
 * @author lijun
 * @since 2026/07/06
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class FqcTaskSyncJobHandler {

    private final EmployeeTaskService employeeTaskService;

    /**
     * 应用启动时自动检测并补充缺失天数的任务数据
     * 处理因关机/停电等原因导致的定时任务未执行问题
     */
    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        log.info("========== [启动检查] 应用启动，开始检测缺失天数的任务数据 ==========");
        try {
            int filledCount = employeeTaskService.fillMissingDaysTasks();
            if (filledCount > 0) {
                log.info("========== [启动检查完成] 成功补充 {} 条缺失天数的任务记录 ==========", filledCount);
            } else {
                log.info("========== [启动检查完成] 无缺失天数的任务数据 ==========");
            }
        } catch (Exception e) {
            log.error("========== [启动检查失败] 补充缺失天数任务数据失败: {} ==========", e.getMessage(), e);
        }
    }

    /**
     * 每天凌晨1点执行，检测并复制缺失天数的任务数据
     * 如果今天已有数据则跳过，避免重复复制
     * Cron表达式: 0 0 1 * * ? (每天凌晨1点)
     */
    @Scheduled(cron = "0 0 1 * * ?")
    // @Scheduled(cron = "0 */5 * * * ?") //测试每5min
    public void execute() {
        log.info("========== [定时任务] FQC任务同步定时任务开始执行 ==========");

        try {
            long startTime = System.currentTimeMillis();

            // 使用 fillMissingDaysTasks，内部会检查今天是否已有数据
            int copiedCount = employeeTaskService.fillMissingDaysTasks();

            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;

            if (copiedCount > 0) {
                String resultMsg = String.format("成功复制 %d 条员工任务记录，耗时 %d ms", copiedCount, duration);
                log.info("========== [定时任务] FQC任务同步定时任务执行完成: {} ==========", resultMsg);
            } else {
                log.info("========== [定时任务] 今日数据已存在，无需复制，耗时 {} ms ==========", duration);
            }

        } catch (Exception e) {
            String errorMsg = "FQC任务同步定时任务执行失败: " + e.getMessage();
            log.error("========== [定时任务] {} ==========", errorMsg, e);
        }
    }
}
