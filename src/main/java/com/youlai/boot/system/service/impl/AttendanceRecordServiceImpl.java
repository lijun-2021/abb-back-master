package com.youlai.boot.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youlai.boot.system.mapper.AttendanceRecordMapper;
import com.youlai.boot.system.model.entity.AttendanceRecord;
import com.youlai.boot.system.model.form.AttendanceRecordForm;
import com.youlai.boot.system.model.query.AttendanceRecordPageQuery;
import com.youlai.boot.system.model.vo.AttendanceRecordPageVO;
import com.youlai.boot.system.service.AttendanceRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 员工考勤记录业务实现类
 *
 * @author lijun
 * @since 2026/07/06
 */
@Service
@RequiredArgsConstructor
public class AttendanceRecordServiceImpl extends ServiceImpl<AttendanceRecordMapper, AttendanceRecord> implements AttendanceRecordService {

    /**
     * 获取员工考勤记录分页列表
     *
     * @param queryParams 查询参数
     * @return 考勤记录分页列表
     */
    @Override
    public IPage<AttendanceRecordPageVO> getAttendanceRecordPage(AttendanceRecordPageQuery queryParams) {
        Page<AttendanceRecordPageVO> page = new Page<>(queryParams.getPageNum(), queryParams.getPageSize());
        return this.baseMapper.getAttendanceRecordPage(page, queryParams);
    }

    /**
     * 更新员工考勤状态（仅更新state字段）
     *
     * @param form 考勤表单（包含empId和state）
     * @return 是否成功
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateAttendanceState(AttendanceRecordForm form) {
        String empId = form.getEmpId();
        Integer state = form.getState();

        // 验证状态值合法性
        if (state < 1 || state > 5) {
            throw new IllegalArgumentException("无效的状态值，应为1-5之间");
        }

        // 根据empId查询现有记录
        AttendanceRecord existingRecord = this.lambdaQuery()
                .eq(AttendanceRecord::getEmpId, empId)
                .one();

        if (existingRecord == null) {
            throw new IllegalArgumentException("未找到员工ID为 " + empId + " 的考勤记录");
        }

        // 仅更新state字段
        existingRecord.setState(state);
        return this.updateById(existingRecord);
    }
}
