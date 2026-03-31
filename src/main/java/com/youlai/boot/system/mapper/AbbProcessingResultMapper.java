package com.youlai.boot.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youlai.boot.system.model.bo.ProcessingResultBO;
import com.youlai.boot.system.model.entity.AbbProcessingResult;
import com.youlai.boot.system.model.query.ProcessingResultPageQuery;
import org.apache.ibatis.annotations.Mapper;

/**
 * C++算法处理结果持久层接口
 */
@Mapper
public interface AbbProcessingResultMapper extends BaseMapper<AbbProcessingResult> {

    /**
     * 获取C++算法处理结果分页列表
     *
     * @param page        分页参数
     * @param queryParams 查询参数
     * @return C++算法处理结果分页列表
     */
    Page<ProcessingResultBO> getProcessingResultPage(Page<ProcessingResultBO> page, ProcessingResultPageQuery queryParams);
}
