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

    IPage<AttendanceRecordPageVO> getAttendanceRecordPage(AttendanceRecordPageQuery queryParams);

    boolean updateAttendanceState(AttendanceRecordForm form);

    int fillMissingDaysAttendance();
}
