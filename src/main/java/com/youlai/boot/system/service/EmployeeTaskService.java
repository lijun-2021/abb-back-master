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
     * 为员工分配SN号任务（按顺序填充sn_code1~sn_code20）
     *
     * @param empId   员工ID
     * @param empName 员工姓名
    //* @param taskType 任务类型 1-耐压 2-功能
     * @param snCode  SN号
     */
    void assignSnCodeToEmployee(String empId, String empName, String snCode);

    /**
     * 从员工任务中移除SN号
     *
     * @param empId    员工ID
     * @param snCode   要移除的SN号
     */
    void removeSnCodeFromEmployee(String empId, String snCode);

    /**
     * 复制昨日任务数据到今日（只保留未完成的任务）
     * 每天凌晨自动执行，将昨天的任务数据复制到今天，但只保留进行中和未完成的开关柜任务
     *
     * @return 复制的记录数
     */
    int copyYesterdayTasksToToday();

    /**
     * 检测并补充缺失天数的任务数据
     * 应用启动时自动调用，处理因关机/停电等原因导致的定时任务未执行问题
     * 会从最近有数据的日期开始，逐天复制到今天
     *
     * @return 补充的总记录数
     */
    int fillMissingDaysTasks();

}
