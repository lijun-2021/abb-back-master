package com.youlai.boot.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.youlai.boot.system.model.entity.EmployeeTask;
import com.youlai.boot.system.model.form.EmployeeTaskForm;
import com.youlai.boot.system.model.query.EmployeeTaskPageQuery;
import com.youlai.boot.system.model.vo.EmployeeTaskPageVO;

import java.util.List;

/**
 * 员工任务业务接口
 *
* @author lijun
* @since 2026/04/23
 */
public interface EmployeeTaskService extends IService<EmployeeTask> {

    /**
     * 获取员工任务分页列表
     *
     * @param queryParams 查询参数
     * @return 员工任务分页列表
     */
    IPage<EmployeeTaskPageVO> getEmployeeTaskPage(EmployeeTaskPageQuery queryParams);

    /**
     * 为员工分配SN号任务（按顺序填充sn_code1~sn_code10）
     *
     * @param empId    员工ID
     * @param empName  员工姓名
    //* @param taskType 任务类型 1-耐压 2-功能
     * @param snCode   SN号
     */
    void assignSnCodeToEmployee(String empId, String empName, String snCode);
//    /**
//     * 新增员工任务
//     *
//     * @param employeeTaskForm 员工任务表单
//     * @return 是否成功
//     */
//    boolean saveEmployeeTask(EmployeeTaskForm employeeTaskForm);
//
//    /**
//     * 修改员工任务
//     *
//     * @param id               主键ID
//     * @param employeeTaskForm 员工任务表单
//     * @return 是否成功
//     */
//    boolean updateEmployeeTask(Long id, EmployeeTaskForm employeeTaskForm);
//
//    /**
//     * 删除员工任务
//     *
//     * @param ids 主键ID，多个以英文逗号(,)分割
//     * @return 是否成功
//     */
//    boolean deleteEmployeeTasks(String ids);


    /**
     * 从员工任务中移除SN号
     *
     * @param empId    员工ID
     * @param snCode   要移除的SN号
     */
    void removeSnCodeFromEmployee(String empId, String snCode);


}
