package com.youlai.boot.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.youlai.boot.common.model.Option;
import com.youlai.boot.system.model.entity.ResourceStatus;
import com.youlai.boot.system.model.form.ResourceStatusForm;
import com.youlai.boot.system.model.query.ResourceStatusPageQuery;
import com.youlai.boot.system.model.vo.ResourceStatusPageVO;

import java.util.List;

/**
 * 当日资源/状态业务接口
 */
public interface ResourceStatusService extends IService<ResourceStatus> {

    /**
     * 获取当日资源/状态分页列表
     *
     * @param queryParams 查询参数
     * @return {@link IPage<ResourceStatusPageVO>} 当日资源/状态分页列表
     */
    IPage<ResourceStatusPageVO> getResourceStatusPage(ResourceStatusPageQuery queryParams);

    /**
     * 获取当日资源/状态表单数据
     *
     * @param resourceStatusId 当日资源/状态ID
     * @return {@link ResourceStatusForm} 当日资源/状态表单数据
     */
    ResourceStatusForm getResourceStatusFormData(Long resourceStatusId);

    /**
     * 保存当日资源/状态信息
     *
     * @param resourceStatusForm 当日资源/状态表单对象
     * @return {@link Boolean} 是否保存成功
     */
    boolean saveResourceStatus(ResourceStatusForm resourceStatusForm);

    /**
     * 更新当日资源/状态信息
     *
     * @param resourceStatusId   当日资源/状态ID
     * @param resourceStatusForm 当日资源/状态表单对象
     * @return {@link Boolean} 是否更新成功
     */
    boolean updateResourceStatus(Long resourceStatusId, ResourceStatusForm resourceStatusForm);

    /**
     * 批量删除当日资源/状态
     *
     * @param idsStr 当日资源/状态ID字符串，多个以英文逗号(,)分割
     * @return {@link Boolean} 是否删除成功
     */
    boolean deleteResourceStatuses(String idsStr);

    /**
     * 获取当日资源/状态下拉选项列表
     *
     * @return {@link List<Option<String>>} 当日资源/状态下拉选项列表
     */
    List<Option<String>> listResourceStatusOptions();
}