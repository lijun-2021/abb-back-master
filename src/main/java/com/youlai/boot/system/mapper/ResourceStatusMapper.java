package com.youlai.boot.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youlai.boot.system.model.bo.ResourceStatusBO;
import com.youlai.boot.system.model.entity.ResourceStatus;
import com.youlai.boot.system.model.form.ResourceStatusForm;
import com.youlai.boot.system.model.query.ResourceStatusPageQuery;
import org.apache.ibatis.annotations.Mapper;

/**
 * 当日资源/状态持久层接口
 */
@Mapper
public interface ResourceStatusMapper extends BaseMapper<ResourceStatus> {

    /**
     * 获取当日资源/状态分页列表
     *
     * @param page        分页参数
     * @param queryParams 查询参数
     * @return 当日资源/状态分页列表
     */
    Page<ResourceStatusBO> getResourceStatusPage(Page<ResourceStatusBO> page, ResourceStatusPageQuery queryParams);

    /**
     * 获取当日资源/状态表单详情
     *
     * @param resourceStatusId 当日资源/状态ID
     * @return 当日资源/状态表单详情
     */
    ResourceStatusForm getResourceStatusFormData(Long resourceStatusId);
}