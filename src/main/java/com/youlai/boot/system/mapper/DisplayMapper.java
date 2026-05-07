package com.youlai.boot.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youlai.boot.system.model.entity.Display;
import com.youlai.boot.system.model.query.DisplayPageQuery;
import com.youlai.boot.system.model.vo.DisplayPageVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 开关柜数量展示Mapper
 *
 * @author lijun
 * @since 2026/04/28
 */
@Mapper
public interface DisplayMapper extends BaseMapper<Display> {

    /**
     * 获取开关柜数量展示分页列表
     *
     * @param page        分页对象
     * @param queryParams 查询参数
     * @return 分页列表
     */
    IPage<DisplayPageVO> getDisplayPage(Page<DisplayPageVO> page, DisplayPageQuery queryParams);
}
