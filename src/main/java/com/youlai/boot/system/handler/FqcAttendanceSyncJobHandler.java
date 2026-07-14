package com.youlai.boot.system.handler;

import com.youlai.boot.system.service.AttendanceRecordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class FqcAttendanceSyncJobHandler {

    private final AttendanceRecordService attendanceRecordService;

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        log.info("========== [启动检查] 应用启动，开始检测缺失天数的考勤数据 ==========");
        try {
            int filledCount = attendanceRecordService.fillMissingDaysAttendance();
            if (filledCount > 0) {
                log.info("========== [启动检查完成] 成功补充 {} 条缺失天数的考勤记录 ==========", filledCount);
            } else {
                log.info("========== [启动检查完成] 无缺失天数的考勤数据 ==========");
            }
        } catch (Exception e) {
            log.error("========== [启动检查失败] 补充缺失天数考勤数据失败 ==========");
            log.error("异常类型: {}", e.getClass().getName());
            log.error("异常消息: {}", e.getMessage());
            log.error("异常堆栈:", e);
        }
    }

    @Scheduled(cron = "0 0 1 * * ?")
    public void execute() {
        log.info("========== [定时任务] FQC考勤同步定时任务开始执行 ==========");

        try {
            long startTime = System.currentTimeMillis();

            int copiedCount = attendanceRecordService.fillMissingDaysAttendance();

            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;

            if (copiedCount > 0) {
                String resultMsg = String.format("成功复制 %d 条员工考勤记录，耗时 %d ms", copiedCount, duration);
                log.info("========== [定时任务] FQC考勤同步定时任务执行完成: {} ==========", resultMsg);
            } else {
                log.info("========== [定时任务] 今日数据已存在，无需复制，耗时 {} ms ==========", duration);
            }

        } catch (Exception e) {
            String errorMsg = "FQC考勤同步定时任务执行失败: " + e.getMessage();
            log.error("========== [定时任务] {} ==========", errorMsg, e);
        }
    }
}