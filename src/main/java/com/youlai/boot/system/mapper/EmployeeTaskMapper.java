package com.youlai.boot.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youlai.boot.system.model.entity.EmployeeTask;
import com.youlai.boot.system.model.query.EmployeeTaskPageQuery;
import com.youlai.boot.system.model.vo.EmployeeTaskPageVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 员工任务持久层接口
 *
* @author lijun
* @since 2026/04/23
 */
@Mapper
public interface EmployeeTaskMapper extends BaseMapper<EmployeeTask> {

    /**
     * 获取员工任务分页列表
     *
     * @param page        分页参数
     * @param queryParams 查询参数
     * @return 员工任务分页列表
     */
    Page<EmployeeTaskPageVO> getEmployeeTaskPage(Page<EmployeeTaskPageVO> page,
                                                 @Param("queryParams") EmployeeTaskPageQuery queryParams);
}
