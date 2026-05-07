package com.youlai.boot.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.youlai.boot.system.model.entity.Display;
import com.youlai.boot.system.model.query.DisplayPageQuery;
import com.youlai.boot.system.model.vo.DisplayPageVO;

/**
 * 开关柜数量展示业务接口
 *
 * @author lijun
 * @since 2026/04/28
 */
public interface DisplayService extends IService<Display> {

    /**
     * 获取开关柜数量展示分页列表
     *
     * @param queryParams 查询参数
     * @return 开关柜数量展示分页列表
     */
    IPage<DisplayPageVO> getDisplayPage(DisplayPageQuery queryParams);
}
