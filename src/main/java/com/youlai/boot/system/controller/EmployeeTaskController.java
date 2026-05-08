package com.youlai.boot.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.youlai.boot.common.annotation.Log;
import com.youlai.boot.common.enums.LogModuleEnum;
import com.youlai.boot.core.web.PageResult;
import com.youlai.boot.system.model.query.EmployeeTaskPageQuery;
import com.youlai.boot.system.model.vo.EmployeeTaskPageVO;
import com.youlai.boot.system.service.EmployeeTaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 员工任务控制层
 *
* @author lijun
* @since 2026/04/23
 */
@Tag(name = "FQC员工任务列表")
@RestController
@RequestMapping("/api/v1/fqc/employee-tasks")
@RequiredArgsConstructor
public class EmployeeTaskController {

    private final EmployeeTaskService employeeTaskService;

    @Operation(summary = "员工任务分页列表")
    @GetMapping("/page")
    @Log(value = "员工任务分页列表", module = LogModuleEnum.OTHER)
    public PageResult<EmployeeTaskPageVO> getEmployeeTaskPage(
            @Valid EmployeeTaskPageQuery queryParams
    ) {
        IPage<EmployeeTaskPageVO> result = employeeTaskService.getEmployeeTaskPage(queryParams);
        return PageResult.success(result);
    }

}
