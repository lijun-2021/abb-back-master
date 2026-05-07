package com.youlai.boot.system.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youlai.boot.system.model.entity.EmployeeTask;
import com.youlai.boot.system.model.form.EmployeeTaskForm;
import com.youlai.boot.system.model.vo.EmployeeTaskPageVO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

/**
 * 员工任务对象转换器
 *
* @author lijun
* @since 2026/04/23
 */
@Mapper(componentModel = "spring")
public interface EmployeeTaskConverter {

    /**
     * 分页VO转换
     */
    Page<EmployeeTaskPageVO> toPageVo(Page<EmployeeTask> entity);

    /**
     * Form转Entity
     */
    EmployeeTask toEntity(EmployeeTaskForm form);

    /**
     * Entity转Form
     */
    @InheritInverseConfiguration(name = "toEntity")
    EmployeeTaskForm toForm(EmployeeTask entity);
}
