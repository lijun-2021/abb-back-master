package com.youlai.boot.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youlai.boot.system.model.entity.SwitchCabinet;
import com.youlai.boot.system.model.query.SwitchCabinetPageQuery;
import com.youlai.boot.system.model.vo.SwitchCabinetPageVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 开关柜持久层接口
 *
* @author lijun
* @since 2026/04/23
 */
@Mapper
public interface SwitchCabinetMapper extends BaseMapper<SwitchCabinet> {

    /**
     * 获取开关柜分页列表
     *
     * @param page        分页参数
     * @param queryParams 查询参数
     * @return 开关柜分页列表
     */
    Page<SwitchCabinetPageVO> getSwitchCabinetPage(Page<SwitchCabinetPageVO> page,
                                                   @Param("queryParams") SwitchCabinetPageQuery queryParams);
}
