package com.youlai.boot.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.youlai.boot.system.model.entity.SwitchCabinet;
import com.youlai.boot.system.model.form.SwitchCabinetForm;
import com.youlai.boot.system.model.query.SwitchCabinetPageQuery;
import com.youlai.boot.system.model.vo.SwitchCabinetPageVO;

/**
 * 开关柜业务接口
 *
* @author lijun
* @since 2026/04/23
 */
public interface SwitchCabinetService extends IService<SwitchCabinet> {

    /**
     * 获取开关柜分页列表
     *
     * @param queryParams 查询参数
     * @return 开关柜分页列表
     */
    IPage<SwitchCabinetPageVO> getSwitchCabinetPage(SwitchCabinetPageQuery queryParams);

    /**
     * 新增开关柜
     *
     * @param switchCabinetForm 开关柜表单
     * @return 是否成功
     */
    boolean saveSwitchCabinet(SwitchCabinetForm switchCabinetForm);

    /**
     * 开关柜员工指派
     *
     * @param id                  主键ID
     * @param switchCabinetForm   开关柜表单
     * @return 是否成功
     */
    boolean updateSwitchCabinet(Long id, SwitchCabinetForm switchCabinetForm);

    /**
     * 删除开关柜
     *
     * @param ids 主键ID，多个以英文逗号(,)分割
     * @return 是否成功
     */
    boolean deleteSwitchCabinets(String ids);
}
