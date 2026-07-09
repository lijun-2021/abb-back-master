package com.youlai.boot.system.handler;

import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.youlai.boot.system.service.EmployeeTaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
     * 每天凌晨1点执行，复制昨日任务数据到今日
     * Cron表达式: 0 0 1 * * ? (每天凌晨1点)
     */
    @XxlJob("fqcTaskSyncJobHandler")
    public void execute() {
        log.info("========== [XXL-JOB] FQC任务同步定时任务开始执行 ==========");
        
        try {
            // 记录任务开始时间
            long startTime = System.currentTimeMillis();
            
            // 执行任务复制逻辑
            int copiedCount = employeeTaskService.copyYesterdayTasksToToday();
            
            // 记录任务结束时间和耗时
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            
            String resultMsg = String.format("成功复制 %d 条员工任务记录，耗时 %d ms", copiedCount, duration);
            log.info("========== [XXL-JOB] FQC任务同步定时任务执行完成: {} ==========", resultMsg);
            
            // 设置任务执行结果（用于在XXL-JOB管理后台查看）
            XxlJobHelper.handleSuccess(resultMsg);
            
        } catch (Exception e) {
            String errorMsg = "FQC任务同步定时任务执行失败: " + e.getMessage();
            log.error("========== [XXL-JOB] {} ==========", errorMsg, e);
            XxlJobHelper.handleFail(errorMsg);
        }
    }
}
