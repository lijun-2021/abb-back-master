package com.youlai.boot.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youlai.boot.system.model.entity.CabinetDetail;
import org.apache.ibatis.annotations.Mapper;

/**
 * 高低压柜明细持久层接口
 *
* @author lijun
* @since 2026/04/23
 */
@Mapper
public interface CabinetDetailMapper extends BaseMapper<CabinetDetail> {
}
