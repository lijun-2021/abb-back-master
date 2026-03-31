package com.youlai.boot.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.youlai.boot.system.model.entity.AbbProcessingResult;
import com.youlai.boot.system.model.query.ProcessingResultPageQuery;
import com.youlai.boot.system.model.vo.ProcessingResultPageVO;

import java.io.File;

/**
 * C++算法处理结果业务接口
 */
public interface AbbProcessingResultService extends IService<AbbProcessingResult> {

    /**
     * 获取C++算法处理结果分页列表
     *
     * @param queryParams 查询参数
     * @return 分页结果
     */
    IPage<ProcessingResultPageVO> getProcessingResultPage(ProcessingResultPageQuery queryParams);

    /**
     * 批量删除C++算法处理结果
     *
     * @param ids 主键ID字符串，多个以英文逗号(,)分割
     * @return 是否删除成功
     */
    boolean deleteProcessingResults(String ids);

    /**
     * 下载上传的文件目录
     *
     * @param uploadDir 上传目录的UUID
     * @return {@link java.io.File} 打包后的zip文件
     */
    java.io.File downloadFiles(String uploadDir);

    /**
     * 解析output_detail.txt文件
     *
     * @param uploadDir 上传目录的UUID
     * @return 解析结果列表
     */
    java.util.List<com.youlai.boot.system.model.vo.OutputDetailVO> ganttData(String uploadDir);
}
