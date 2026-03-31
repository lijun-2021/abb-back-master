package com.youlai.boot.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.youlai.boot.common.model.Option;
import com.youlai.boot.system.model.entity.Worker;
import com.youlai.boot.system.model.form.WorkerForm;
import com.youlai.boot.system.model.query.WorkerPageQuery;
import com.youlai.boot.system.model.vo.WorkerPageVO;

import java.util.List;

/**
 * 工人业务接口
 */
public interface WorkerService extends IService<Worker> {

    /**
     * 获取工人分页列表
     *
     * @param queryParams 查询参数
     * @return {@link IPage<WorkerPageVO>} 工人分页列表
     */
    IPage<WorkerPageVO> getWorkerPage(WorkerPageQuery queryParams);

    /**
     * 获取工人表单数据
     *
     * @param workerId 工人ID
     * @return {@link WorkerForm} 工人表单数据
     */
    WorkerForm getWorkerFormData(Long workerId);

    /**
     * 保存工人信息
     *
     * @param workerForm 工人表单对象
     * @return {@link Boolean} 是否保存成功
     */
    boolean saveWorker(WorkerForm workerForm);

    /**
     * 更新工人信息
     *
     * @param workerId   工人ID
     * @param workerForm 工人表单对象
     * @return {@link Boolean} 是否更新成功
     */
    boolean updateWorker(Long workerId, WorkerForm workerForm);

    /**
     * 批量删除工人
     *
     * @param idsStr 工人ID字符串，多个以英文逗号(,)分割
     * @return {@link Boolean} 是否删除成功
     */
    boolean deleteWorkers(String idsStr);

    /**
     * 获取工人下拉选项列表
     *
     * @return {@link List<Option<String>>} 工人下拉选项列表
     */
    List<Option<String>> listWorkerOptions();
}