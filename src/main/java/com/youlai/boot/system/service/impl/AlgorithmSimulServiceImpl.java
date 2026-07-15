package com.youlai.boot.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youlai.boot.system.mapper.AlgorithmSimulMapper;
import com.youlai.boot.system.model.entity.AlgorithmSimul;
import com.youlai.boot.system.model.query.AlgorithmSimulPageQuery;
import com.youlai.boot.system.model.vo.AlgorithmSimulPageVO;
import com.youlai.boot.system.service.AlgorithmSimulService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * FQC算法模拟数据业务实现类
 *
 * @author lijun
 * @since 2026/07/15
 */
@Service
@RequiredArgsConstructor
public class AlgorithmSimulServiceImpl extends ServiceImpl<AlgorithmSimulMapper, AlgorithmSimul> implements AlgorithmSimulService {

    /**
     * 获取FQC算法模拟数据分页列表
     *
     * @param queryParams 查询参数（支持产线、工位行ID、电压区域等模糊查询）
     * @return 分页数据结果
     */
    @Override
    public IPage<AlgorithmSimulPageVO> getAlgorithmSimulPage(AlgorithmSimulPageQuery queryParams) {
        // 获取分页参数
        int pageNum = queryParams.getPageNum();
        int pageSize = queryParams.getPageSize();
        
        // 创建分页对象
        Page<AlgorithmSimulPageVO> page = new Page<>(pageNum, pageSize);
        
        // 调用Mapper查询分页数据
        return this.baseMapper.getAlgorithmSimulPage(page, queryParams);
    }
}