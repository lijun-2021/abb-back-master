package com.youlai.boot.system.model.query;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Schema(description = "功能试验产出查询对象")
public class FunctionOutputQuery {

    @Schema(description = "统计日期，不传默认当天", example = "2026-08-14")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate statDate;

    @Schema(description = "员工姓名（模糊查询）")
    private String empName;

    @Schema(description = "组别（A/B/C/D）")
    @Pattern(regexp = "^[ABCD]$", message = "组别只能是A、B、C、D")
    private String empTeam;
}
