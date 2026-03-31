package com.youlai.boot.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.youlai.boot.common.model.Option;
import com.youlai.boot.system.model.entity.Workpiece;
import com.youlai.boot.system.model.form.WorkpieceForm;
import com.youlai.boot.system.model.query.WorkpiecePageQuery;
import com.youlai.boot.system.model.vo.WorkpiecePageVO;

import java.util.List;

/**
 * 工件服务接口
 */
public interface WorkpieceService extends IService<Workpiece> {

    /**
     * 获取工件分页列表
     *
     * @param queryParams 查询参数
     * @return {@link IPage<WorkpiecePageVO>} 工件分页列表
     */
    IPage<WorkpiecePageVO> getWorkpiecePage(WorkpiecePageQuery queryParams);

    /**
     * 获取工件表单数据
     *
     * @param workpieceId 工件ID
     * @return {@link WorkpieceForm} 工件表单数据
     */
    WorkpieceForm getWorkpieceFormData(Long workpieceId);

    /**
     * 新增工件
     *
     * @param workpieceForm 工件表单对象
     * @return {@link Boolean} 是否新增成功
     */
    boolean saveWorkpiece(WorkpieceForm workpieceForm);

    /**
     * 修改工件
     *
     * @param workpieceId   工件ID
     * @param workpieceForm 工件表单对象
     * @return {@link Boolean} 是否修改成功
     */
    boolean updateWorkpiece(Long workpieceId, WorkpieceForm workpieceForm);

    /**
     * 批量删除工件
     *
     * @param idsStr 工件ID，多个以英文逗号(,)分割
     * @return {@link Boolean} 是否删除成功
     */
    boolean deleteWorkpieces(String idsStr);

    /**
     * 获取工件下拉列表
     *
     * @return {@link List<Option<String>>} 工件选项列表
     */
    List<Option<String>> listWorkpieceOptions();
}