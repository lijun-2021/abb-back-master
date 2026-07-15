package com.youlai.boot.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.youlai.boot.common.annotation.Log;
import com.youlai.boot.common.enums.LogModuleEnum;
import com.youlai.boot.core.web.PageResult;
import com.youlai.boot.system.model.query.AlgorithmSimulPageQuery;
import com.youlai.boot.system.model.vo.AlgorithmSimulPageVO;
import com.youlai.boot.system.service.AlgorithmSimulService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * FQC算法模拟数据控制器
 * 提供fqc_algorithm_simul表的数据查询接口
 *
 * @author lijun
 * @since 2026/07/15
 */
@Tag(name = "FQC算法模拟数据")
@RestController
@RequestMapping("/api/v1/fqc/algorithm-simul")
@RequiredArgsConstructor
public class AlgorithmSimulController {

    /**
     * FQC算法模拟数据业务服务
     */
    private final AlgorithmSimulService algorithmSimulService;

    /**
     * 获取FQC算法模拟数据分页列表
     * 支持产线、工位行ID、电压区域、作业人员、功能区域、检验员等模糊查询
     * 不传查询参数时默认返回所有数据
     *
     * @param queryParams 查询参数（支持产线、工位行ID、电压区域等模糊查询）
     * @return 分页数据结果
     */
    @Operation(summary = "FQC算法模拟数据分页列表")
    @GetMapping("/page")
    @Log(value = "FQC算法模拟数据分页列表", module = LogModuleEnum.OTHER)
    public PageResult<AlgorithmSimulPageVO> getAlgorithmSimulPage(
            AlgorithmSimulPageQuery queryParams
    ) {
        // 调用Service层获取分页数据
        IPage<AlgorithmSimulPageVO> result = algorithmSimulService.getAlgorithmSimulPage(queryParams);
        
        // 封装并返回分页结果
        return PageResult.success(result);
    }
}