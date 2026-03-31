package com.youlai.boot.system.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youlai.boot.common.model.Option;
import com.youlai.boot.security.util.SecurityUtils;
import com.youlai.boot.system.converter.WorkerConverter;
import com.youlai.boot.system.mapper.WorkerMapper;
import com.youlai.boot.system.model.bo.WorkerBO;
import com.youlai.boot.system.model.entity.Worker;
import com.youlai.boot.system.model.form.WorkerForm;
import com.youlai.boot.system.model.query.WorkerPageQuery;
import com.youlai.boot.system.model.vo.WorkerPageVO;
import com.youlai.boot.system.service.WorkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 工人业务实现类
 */
@Service
@RequiredArgsConstructor
public class WorkerServiceImpl extends ServiceImpl<WorkerMapper, Worker> implements WorkerService {

    private final WorkerConverter workerConverter;

    /**
     * 获取工人分页列表
     *
     * @param queryParams 查询参数
     * @return {@link IPage<WorkerPageVO>} 工人分页列表
     */
    @Override
    public IPage<WorkerPageVO> getWorkerPage(WorkerPageQuery queryParams) {
        // 参数构建
        int pageNum = queryParams.getPageNum();
        int pageSize = queryParams.getPageSize();
        Page<WorkerBO> page = new Page<>(pageNum, pageSize);

        // 查询数据
        Page<WorkerBO> workerPage = this.baseMapper.getWorkerPage(page, queryParams);

        // 实体转换
        return workerConverter.toPageVo(workerPage);
    }

    /**
     * 获取工人表单数据
     *
     * @param workerId 工人ID
     * @return {@link WorkerForm} 工人表单数据
     */
    @Override
    public WorkerForm getWorkerFormData(Long workerId) {
        return this.baseMapper.getWorkerFormData(workerId);
    }

    /**
     * 新增工人
     *
     * @param workerForm 工人表单对象
     * @return {@link Boolean} 是否新增成功
     */
    @Override
    public boolean saveWorker(WorkerForm workerForm) {
        // 实体转换 form->entity
        Worker entity = workerConverter.toEntity(workerForm);
        entity.setCreateBy(SecurityUtils.getUserId());
        entity.setUpdateBy(SecurityUtils.getUserId());

        // 新增工人
        return this.save(entity);
    }

    /**
     * 修改工人
     *
     * @param workerId   工人ID
     * @param workerForm 工人表单对象
     * @return {@link Boolean} 是否修改成功
     */
    @Override
    public boolean updateWorker(Long workerId, WorkerForm workerForm) {
        // form -> entity
        Worker entity = workerConverter.toEntity(workerForm);
        entity.setId(workerId);
        entity.setUpdateBy(SecurityUtils.getUserId());

        // 修改工人
        return this.updateById(entity);
    }

    /**
     * 批量删除工人
     *
     * @param idsStr 工人ID，多个以英文逗号(,)分割
     * @return {@link Boolean} 是否删除成功
     */
    @Override
    public boolean deleteWorkers(String idsStr) {
        StrUtil.isNotBlank(idsStr);
        // 逻辑删除
        List<Long> ids = Arrays.stream(idsStr.split("\\,")).map(Long::parseLong).collect(Collectors.toList());
        return this.removeByIds(ids);
    }

    /**
     * 获取工人下拉列表
     *
     * @return {@link List<Option<String>>} 工人选项列表
     */
    @Override
    public List<Option<String>> listWorkerOptions() {
        List<Worker> workers = this.list(new LambdaQueryWrapper<Worker>().orderByAsc(Worker::getId));
        return workerConverter.toOptions(workers);
    }
}