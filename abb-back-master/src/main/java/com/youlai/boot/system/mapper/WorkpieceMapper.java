package com.youlai.boot.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youlai.boot.system.model.bo.WorkpieceBO;
import com.youlai.boot.system.model.entity.Workpiece;
import com.youlai.boot.system.model.form.WorkpieceForm;
import com.youlai.boot.system.model.query.WorkpiecePageQuery;
import org.apache.ibatis.annotations.Mapper;

/**
 * 工件持久层接口
 */
@Mapper
public interface WorkpieceMapper extends BaseMapper<Workpiece> {

    /**
     * 获取工件分页列表
     *
     * @param page        分页参数
     * @param queryParams 查询参数
     * @return 工件分页列表
     */
    Page<WorkpieceBO> getWorkpiecePage(Page<WorkpieceBO> page, WorkpiecePageQuery queryParams);

    /**
     * 获取工件表单详情
     *
     * @param workpieceId 工件ID
     * @return 工件表单详情
     */
    WorkpieceForm getWorkpieceFormData(Long workpieceId);
}