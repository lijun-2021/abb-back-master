package com.youlai.boot.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youlai.boot.system.model.entity.ScheduleRecord;
import org.apache.ibatis.annotations.Mapper;

/**
 * 排程操作记录持久层接口
 *
* @author lijun
* @since 2026/04/23
 */
@Mapper
public interface ScheduleRecordMapper extends BaseMapper<ScheduleRecord> {
}
