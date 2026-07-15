package com.youlai.boot.system.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youlai.boot.system.model.entity.AlgorithmSimul;
import com.youlai.boot.system.model.query.AlgorithmSimulPageQuery;
import com.youlai.boot.system.model.vo.AlgorithmSimulPageVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * FQC算法模拟数据Mapper接口
 * 提供对fqc_algorithm_simul表的数据访问操作
 *
 * @author lijun
 * @since 2026/07/15
 */
@Mapper
public interface AlgorithmSimulMapper extends com.baomidou.mybatisplus.core.mapper.BaseMapper<AlgorithmSimul> {

    /**
     * 获取FQC算法模拟数据分页列表
     *
     * @param page        分页参数
     * @param queryParams 查询参数（支持产线、工位行ID、电压区域等模糊查询）
     * @return 分页数据结果
     */
    IPage<AlgorithmSimulPageVO> getAlgorithmSimulPage(Page<AlgorithmSimulPageVO> page, AlgorithmSimulPageQuery queryParams);
}