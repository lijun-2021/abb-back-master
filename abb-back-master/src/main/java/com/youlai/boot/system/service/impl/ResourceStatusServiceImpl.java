package com.youlai.boot.system.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youlai.boot.common.model.Option;
import com.youlai.boot.security.util.SecurityUtils;
import com.youlai.boot.system.converter.ResourceStatusConverter;
import com.youlai.boot.system.mapper.ResourceStatusMapper;
import com.youlai.boot.system.model.bo.ResourceStatusBO;
import com.youlai.boot.system.model.entity.ResourceStatus;
import com.youlai.boot.system.model.form.ResourceStatusForm;
import com.youlai.boot.system.model.query.ResourceStatusPageQuery;
import com.youlai.boot.system.model.vo.ResourceStatusPageVO;
import com.youlai.boot.system.service.ResourceStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 当日资源/状态业务实现类
 */
@Service
@RequiredArgsConstructor
public class ResourceStatusServiceImpl extends ServiceImpl<ResourceStatusMapper, ResourceStatus> implements ResourceStatusService {

    private final ResourceStatusConverter resourceStatusConverter;

    /**
     * 获取当日资源/状态分页列表
     *
     * @param queryParams 查询参数
     * @return {@link IPage<ResourceStatusPageVO>} 当日资源/状态分页列表
     */
    @Override
    public IPage<ResourceStatusPageVO> getResourceStatusPage(ResourceStatusPageQuery queryParams) {
        // 参数构建
        int pageNum = queryParams.getPageNum();
        int pageSize = queryParams.getPageSize();
        Page<ResourceStatusBO> page = new Page<>(pageNum, pageSize);

        // 查询数据
        Page<ResourceStatusBO> resourceStatusPage = this.baseMapper.getResourceStatusPage(page, queryParams);

        // 实体转换
        return resourceStatusConverter.toPageVo(resourceStatusPage);
    }

    /**
     * 获取当日资源/状态表单数据
     *
     * @param resourceStatusId 当日资源/状态ID
     * @return {@link ResourceStatusForm} 当日资源/状态表单数据
     */
    @Override
    public ResourceStatusForm getResourceStatusFormData(Long resourceStatusId) {
        return this.baseMapper.getResourceStatusFormData(resourceStatusId);
    }

    /**
     * 新增当日资源/状态
     *
     * @param resourceStatusForm 当日资源/状态表单对象
     * @return {@link Boolean} 是否新增成功
     */
    @Override
    public boolean saveResourceStatus(ResourceStatusForm resourceStatusForm) {
        // 实体转换 form->entity
        ResourceStatus entity = resourceStatusConverter.toEntity(resourceStatusForm);
        entity.setCreateBy(SecurityUtils.getUserId());
        entity.setUpdateBy(SecurityUtils.getUserId());

        // 新增当日资源/状态
        return this.save(entity);
    }

    /**
     * 修改当日资源/状态
     *
     * @param resourceStatusId   当日资源/状态ID
     * @param resourceStatusForm 当日资源/状态表单对象
     * @return {@link Boolean} 是否修改成功
     */
    @Override
    public boolean updateResourceStatus(Long resourceStatusId, ResourceStatusForm resourceStatusForm) {
        // form -> entity
        ResourceStatus entity = resourceStatusConverter.toEntity(resourceStatusForm);
        entity.setId(resourceStatusId);
        entity.setUpdateBy(SecurityUtils.getUserId());

        // 修改当日资源/状态
        return this.updateById(entity);
    }

    /**
     * 批量删除当日资源/状态
     *
     * @param idsStr 当日资源/状态ID，多个以英文逗号(,)分割
     * @return {@link Boolean} 是否删除成功
     */
    @Override
    public boolean deleteResourceStatuses(String idsStr) {
        StrUtil.isNotBlank(idsStr);
        // 逻辑删除
        List<Long> ids = Arrays.stream(idsStr.split("\\,\\s*"))
                .map(Long::parseLong)
                .collect(Collectors.toList());
        return this.removeByIds(ids);
    }

    /**
     * 获取当日资源/状态下拉列表
     *
     * @return {@link List<Option<String>>} 当日资源/状态选项列表
     */
    @Override
    public List<Option<String>> listResourceStatusOptions() {
        List<ResourceStatus> resourceStatuses = this.list(new LambdaQueryWrapper<ResourceStatus>()
                .eq(ResourceStatus::getIsDeleted, 0)
                .orderByAsc(ResourceStatus::getId));
        return resourceStatusConverter.toOptions(resourceStatuses);
    }
}