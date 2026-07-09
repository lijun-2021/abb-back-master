package com.youlai.boot.system.controller;

import com.youlai.boot.common.model.Result;
import com.youlai.boot.system.service.EmployeeTaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * FQC任务同步测试控制器
 * 用于手动测试定时任务功能
 *
 * @author lijun
 * @since 2026/07/08
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/fqc/test")
@Tag(name = "FQC任务同步测试", description = "用于测试定时任务功能")
public class FqcTestController {

    private final EmployeeTaskService employeeTaskService;

    /**
     * 手动触发复制昨日任务到今日
     * 
     * @return 复制的记录数
     */
    @PostMapping("/copy-yesterday-tasks")
    @Operation(summary = "手动复制昨日任务到今日", description = "用于测试定时任务功能，生产环境应使用XXL-JOB定时任务")
    public Result<Integer> copyYesterdayTasksToToday() {
        log.info("========== [手动触发] 开始复制昨日任务数据 ==========");
        
        try {
            int count = employeeTaskService.copyYesterdayTasksToToday();
            
            log.info("========== [手动触发完成] 成功复制 {} 条记录 ==========", count);
            
            return Result.success(count, "成功复制 " + count + " 条员工任务记录");
        } catch (Exception e) {
            log.error("========== [手动触发失败] 复制任务数据失败: {} ==========", e.getMessage(), e);
            return Result.failed("复制失败: " + e.getMessage());
        }
    }
}
