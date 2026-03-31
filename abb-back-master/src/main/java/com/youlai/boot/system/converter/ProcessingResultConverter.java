package com.youlai.boot.system.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youlai.boot.system.model.bo.ProcessingResultBO;
import com.youlai.boot.system.model.vo.ProcessingResultPageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * C++算法处理结果对象转换类
 */
@Mapper
public interface ProcessingResultConverter {

    ProcessingResultConverter INSTANCE = Mappers.getMapper(ProcessingResultConverter.class);

    /**
     * 将ProcessingResultBO转换为ProcessingResultPageVO
     *
     * @param bo C++算法处理结果业务对象
     * @return C++算法处理结果分页视图对象
     */
    ProcessingResultPageVO toPageVo(ProcessingResultBO bo);

    /**
     * 将ProcessingResultBO列表转换为ProcessingResultPageVO列表
     *
     * @param boList C++算法处理结果业务对象列表
     * @return C++算法处理结果分页视图对象列表
     */
    List<ProcessingResultPageVO> toPageVoList(List<ProcessingResultBO> boList);

    /**
     * 将ProcessingResultBO分页对象转换为ProcessingResultPageVO分页对象
     *
     * @param boPage C++算法处理结果业务对象分页
     * @return C++算法处理结果分页视图对象分页
     */
    default Page<ProcessingResultPageVO> toPageVo(Page<ProcessingResultBO> boPage) {
        Page<ProcessingResultPageVO> voPage = new Page<>(boPage.getCurrent(), boPage.getSize());
        voPage.setTotal(boPage.getTotal());
        voPage.setRecords(toPageVoList(boPage.getRecords()));
        return voPage;
    }
}