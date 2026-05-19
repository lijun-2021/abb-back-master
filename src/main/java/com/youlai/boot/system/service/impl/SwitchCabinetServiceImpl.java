package com.youlai.boot.system.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youlai.boot.system.converter.SwitchCabinetConverter;
import com.youlai.boot.system.mapper.EmployeeTaskMapper;
import com.youlai.boot.system.mapper.SwitchCabinetMapper;
import com.youlai.boot.system.model.entity.EmployeeTask;
import com.youlai.boot.system.model.entity.SwitchCabinet;
import com.youlai.boot.system.model.form.SwitchCabinetForm;
import com.youlai.boot.system.model.query.SwitchCabinetPageQuery;
import com.youlai.boot.system.model.vo.SwitchCabinetPageVO;
import com.youlai.boot.system.service.EmployeeTaskService;
import com.youlai.boot.system.service.SwitchCabinetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 开关柜业务实现类
 *
* @author lijun
* @since 2026/04/23
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class SwitchCabinetServiceImpl extends ServiceImpl<SwitchCabinetMapper, SwitchCabinet> implements SwitchCabinetService {

    private final SwitchCabinetConverter switchCabinetConverter;
    private final EmployeeTaskMapper employeeTaskMapper;
    private final EmployeeTaskService employeeTaskService;

    /**
     * 获取开关柜分页列表
     *
     * @param queryParams 查询参数
     * @return 开关柜分页列表
     */
    @Override
    public IPage<SwitchCabinetPageVO> getSwitchCabinetPage(SwitchCabinetPageQuery queryParams) {
        Page<SwitchCabinetPageVO> page = new Page<>(queryParams.getPageNum(), queryParams.getPageSize());
        IPage<SwitchCabinetPageVO> result = this.baseMapper.getSwitchCabinetPage(page, queryParams);

        // 处理功能检测状态
        for (SwitchCabinetPageVO vo : result.getRecords()) {
            vo.setFunctionStatus(calculateFunctionStatus(vo.getFunctionStarttime(), vo.getFunctionEndtime()));
        }

        return result;
    }

    /**
     * 计算功能检测状态
     * 0-未完成：function_starttime 为空
     * 1-进行中：function_starttime 不为空，function_endtime 为空
     * 2-已完成：function_starttime 不为空，function_endtime 不为空
     *
     * @param functionStarttime 功能检测开始时间
     * @param functionEndtime   功能检测结束时间
     * @return 状态码 0-未完成 1-进行中 2-已完成
     */
    private Integer calculateFunctionStatus(LocalDateTime functionStarttime, LocalDateTime functionEndtime) {
        if (functionStarttime == null) {
            return 0; // 未完成
        }
        if (functionEndtime == null) {
            return 1; // 进行中
        }
        return 2; // 已完成
    }


    /**
     * 开关柜指派员工
     *
     * @param batchForm 开关柜表单
     * @return 是否成功
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateSwitchCabinets(SwitchCabinetForm batchForm) {
        List<Long> ids = batchForm.getIds();
        List<String> empNames = batchForm.getFunctionEmpNames();
        List<String> areas = batchForm.getAreas();

        Assert.isTrue(ids.size() == empNames.size(), "开关柜ID列表与员工姓名列表长度不一致");
        Assert.isTrue(ids.size() == areas.size(), "开关柜ID列表与区域列表长度不一致");

        log.info("批量处理开关柜，总数: {}", ids.size());

        for (int i = 0; i < ids.size(); i++) {
            Long cabinetId = ids.get(i);
            String newEmpName = empNames.get(i);
            String newArea = areas.get(i);

            SwitchCabinet existCabinet = this.getById(cabinetId);
            Assert.notNull(existCabinet, "开关柜不存在，ID: " + cabinetId);

            String oldFunctionEmpName = existCabinet.getFunctionEmpName();
            String snCode = existCabinet.getSnCode();

            log.info("处理开关柜 ID: {}, SN: {}, 旧员工: {}, 新员工: {}",
                    cabinetId, snCode, oldFunctionEmpName, newEmpName);

            if (StrUtil.isNotBlank(newEmpName)) {
                if (!newEmpName.equals(oldFunctionEmpName)) {
                    if (StrUtil.isNotBlank(oldFunctionEmpName)) {
                        EmployeeTask oldEmployee = employeeTaskMapper.selectOne(
                                new LambdaQueryWrapper<EmployeeTask>()
                                        .eq(EmployeeTask::getEmpName, oldFunctionEmpName)
                                        .eq(EmployeeTask::getTaskType, 2)
                                        .eq(EmployeeTask::getIsDeleted, 0)
                                        .last("LIMIT 1")
                        );
                        if (oldEmployee != null) {
                            employeeTaskService.removeSnCodeFromEmployee(oldEmployee.getEmpId(), snCode);
                            log.info("已从旧员工 {} 移除SN号 {}", oldFunctionEmpName, snCode);
                        }
                    }

                    EmployeeTask newEmployee = employeeTaskMapper.selectOne(
                            new LambdaQueryWrapper<EmployeeTask>()
                                    .eq(EmployeeTask::getEmpName, newEmpName)
                                    .eq(EmployeeTask::getTaskType, 2)
                                    .eq(EmployeeTask::getIsDeleted, 0)
                                    .last("LIMIT 1")
                    );
                    Assert.notNull(newEmployee, "员工任务列表中不存在该员工: " + newEmpName);
                    employeeTaskService.assignSnCodeToEmployee(newEmployee.getEmpId(), newEmpName, snCode);
                    log.info("已为员工 {} 分配SN号 {}", newEmpName, snCode);

                    existCabinet.setFunctionEmpName(newEmpName);
                }
            } else {
                if (StrUtil.isNotBlank(oldFunctionEmpName)) {
                    log.info("检测到functionEmpName为null或空，准备从员工 {} 移除SN号: {}", oldFunctionEmpName, snCode);
                    EmployeeTask oldEmployee = employeeTaskMapper.selectOne(
                            new LambdaQueryWrapper<EmployeeTask>()
                                    .eq(EmployeeTask::getEmpName, oldFunctionEmpName)
                                    .eq(EmployeeTask::getTaskType, 2)
                                    .eq(EmployeeTask::getIsDeleted, 0)
                                    .last("LIMIT 1")
                    );
                    if (oldEmployee != null) {
                        employeeTaskService.removeSnCodeFromEmployee(oldEmployee.getEmpId(), snCode);
                        log.info("已从员工 {} (ID:{}) 成功移除SN号: {}", oldFunctionEmpName, oldEmployee.getEmpId(), snCode);
                    } else {
                        log.warn("未找到员工 {} 的任务记录，无法移除SN号: {}", oldFunctionEmpName, snCode);
                    }
                    existCabinet.setFunctionEmpName(null);
                    log.info("已将开关柜 {} 的 functionEmpName 设置为 null", snCode);
                }
            }

            if (newArea != null) {
                existCabinet.setArea(newArea);
            }

            boolean updated = this.update(null, new LambdaUpdateWrapper<SwitchCabinet>()
                    .eq(SwitchCabinet::getId, cabinetId)
                    .set(SwitchCabinet::getFunctionEmpName, existCabinet.getFunctionEmpName())
                    .set(SwitchCabinet::getArea, existCabinet.getArea()));

            log.info("更新开关柜成功，ID: {}, SN: {}, 员工: {}, 区域: {}",
                    cabinetId, snCode, existCabinet.getFunctionEmpName(), existCabinet.getArea());
        }

        return true;
    }
}



//
//    /**
//     * 新增开关柜
//     *
//     * @param switchCabinetForm 开关柜表单
//     * @return 是否成功
//     */
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public boolean saveSwitchCabinet(SwitchCabinetForm switchCabinetForm) {
//        String snCode = switchCabinetForm.getSnCode();
//
//        long count = this.count(new LambdaQueryWrapper<SwitchCabinet>()
//                .eq(SwitchCabinet::getSnCode, snCode)
//                .eq(SwitchCabinet::getIsDeleted, 0)
//        );
//        Assert.isTrue(count == 0, "SN号已存在");
//
//        SwitchCabinet entity = switchCabinetConverter.toEntity(switchCabinetForm);
//
//        if (StrUtil.isNotBlank(switchCabinetForm.getOfflineTime())) {
//            try {
//                entity.setOfflineTime(LocalDateTime.parse(
//                    switchCabinetForm.getOfflineTime(),
//                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
//            } catch (Exception e) {
//                log.error("下线时间格式错误: {}", switchCabinetForm.getOfflineTime(), e);
//                throw new IllegalArgumentException("下线时间格式错误，正确格式：yyyy-MM-dd HH:mm:ss");
//            }
//        }
//
//        return this.save(entity);
//    }



//**
// //     * 删除开关柜
// //     *
// //     * @param ids 主键ID，多个以英文逗号(,)分割
// //     * @return 是否成功
// //     */
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public boolean deleteSwitchCabinets(String ids) {
//        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的开关柜数据为空");
//
//        List<Long> idList = Arrays.stream(ids.split(","))
//                .map(Long::parseLong)
//                .collect(Collectors.toList());
//
//        long count = this.count(new LambdaQueryWrapper<SwitchCabinet>()
//                .in(SwitchCabinet::getId, idList)
//                .eq(SwitchCabinet::getIsDeleted, 0)
//        );
//
//        Assert.isTrue(count > 0, "开关柜不存在或已被删除");
//
//        boolean result = this.removeByIds(idList);
//
//        if (result) {
//            log.info("删除开关柜成功，IDs: {}", ids);
//        }
//
//        return result;
//    }