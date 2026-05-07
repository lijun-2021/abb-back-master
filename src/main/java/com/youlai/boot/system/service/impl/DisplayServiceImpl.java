package com.youlai.boot.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youlai.boot.system.mapper.DisplayMapper;
import com.youlai.boot.system.model.entity.Display;
import com.youlai.boot.system.model.query.DisplayPageQuery;
import com.youlai.boot.system.model.vo.DisplayPageVO;
import com.youlai.boot.system.service.DisplayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 开关柜数量展示业务实现类
 *
 * @author lijun
 * @since 2026/04/28
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class DisplayServiceImpl extends ServiceImpl<DisplayMapper, Display> implements DisplayService {

    /**
     * 获取开关柜数量展示分页列表
     *
     * @param queryParams 查询参数
     * @return 开关柜数量展示分页列表
     */
    @Override
    public IPage<DisplayPageVO> getDisplayPage(DisplayPageQuery queryParams) {
        Page<DisplayPageVO> page = new Page<>(queryParams.getPageNum(), queryParams.getPageSize());
        return this.baseMapper.getDisplayPage(page, queryParams);
    }
}
