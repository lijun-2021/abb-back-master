package com.youlai.boot.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youlai.boot.system.model.entity.AttendanceRecord;
import com.youlai.boot.system.model.query.AttendanceRecordPageQuery;
import com.youlai.boot.system.model.vo.AttendanceRecordPageVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 员工考勤记录 Mapper
 *
 * @author lijun
 * @since 2026/07/06
 */
@Mapper
public interface AttendanceRecordMapper extends BaseMapper<AttendanceRecord> {

    /**
     * 获取员工考勤记录分页列表
     *
     * @param page        分页对象
     * @param queryParams 查询参数
     * @return 分页结果
     */
    Page<AttendanceRecordPageVO> getAttendanceRecordPage(
            Page<AttendanceRecordPageVO> page,
            @Param("queryParams") AttendanceRecordPageQuery queryParams
    );
}
