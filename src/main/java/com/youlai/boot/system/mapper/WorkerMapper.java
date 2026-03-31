package com.youlai.boot.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youlai.boot.system.model.bo.WorkerBO;
import com.youlai.boot.system.model.entity.Worker;
import com.youlai.boot.system.model.form.WorkerForm;
import com.youlai.boot.system.model.query.WorkerPageQuery;
import org.apache.ibatis.annotations.Mapper;

/**
 * 工人持久层接口
 */
@Mapper
public interface WorkerMapper extends BaseMapper<Worker> {

    /**
     * 获取工人分页列表
     *
     * @param page        分页参数
     * @param queryParams 查询参数
     * @return 工人分页列表
     */
    Page<WorkerBO> getWorkerPage(Page<WorkerBO> page, WorkerPageQuery queryParams);

    /**
     * 获取工人表单详情
     *
     * @param workerId 工人ID
     * @return 工人表单详情
     */
    WorkerForm getWorkerFormData(Long workerId);
}