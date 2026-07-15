package com.youlai.boot.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.youlai.boot.system.model.entity.AlgorithmSimul;
import com.youlai.boot.system.model.query.AlgorithmSimulPageQuery;
import com.youlai.boot.system.model.vo.AlgorithmSimulPageVO;

/**
 * FQC算法模拟数据业务接口
 *
 * @author lijun
 * @since 2026/07/15
 */
public interface AlgorithmSimulService extends IService<AlgorithmSimul> {

    /**
     * 获取FQC算法模拟数据分页列表
     *
     * @param queryParams 查询参数（支持产线、工位行ID、电压区域等模糊查询）
     * @return 分页数据结果
     */
    IPage<AlgorithmSimulPageVO> getAlgorithmSimulPage(AlgorithmSimulPageQuery queryParams);
}