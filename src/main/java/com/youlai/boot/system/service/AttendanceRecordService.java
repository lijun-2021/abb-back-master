package com.youlai.boot.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.youlai.boot.system.model.entity.AttendanceRecord;
import com.youlai.boot.system.model.form.AttendanceRecordForm;
import com.youlai.boot.system.model.query.AttendanceRecordPageQuery;
import com.youlai.boot.system.model.vo.AttendanceRecordPageVO;

/**
 * 员工考勤记录业务接口
 *
 * @author lijun
 * @since 2026/07/06
 */
public interface AttendanceRecordService extends IService<AttendanceRecord> {

    /**
     * 获取员工考勤记录分页列表
     *
     * @param queryParams 查询参数
     * @return 考勤记录分页列表
     */
    IPage<AttendanceRecordPageVO> getAttendanceRecordPage(AttendanceRecordPageQuery queryParams);

    /**
     * 更新员工考勤状态（支持单条和批量更新）
     *
     * @param form 考勤表单（包含empId和state用于单条更新，或items列表用于批量更新）
     * @return 是否成功
     */
    boolean updateAttendanceState(AttendanceRecordForm form);
}
