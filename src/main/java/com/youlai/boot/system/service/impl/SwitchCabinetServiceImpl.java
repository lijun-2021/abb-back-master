package com.youlai.boot.system.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
     * 新增开关柜
     *
     * @param switchCabinetForm 开关柜表单
     * @return 是否成功
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveSwitchCabinet(SwitchCabinetForm switchCabinetForm) {
        String snCode = switchCabinetForm.getSnCode();

        long count = this.count(new LambdaQueryWrapper<SwitchCabinet>()
                .eq(SwitchCabinet::getSnCode, snCode)
                .eq(SwitchCabinet::getIsDeleted, 0)
        );
        Assert.isTrue(count == 0, "SN号已存在");

        SwitchCabinet entity = switchCabinetConverter.toEntity(switchCabinetForm);

        if (StrUtil.isNotBlank(switchCabinetForm.getOfflineTime())) {
            try {
                entity.setOfflineTime(LocalDateTime.parse(
                    switchCabinetForm.getOfflineTime(),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            } catch (Exception e) {
                log.error("下线时间格式错误: {}", switchCabinetForm.getOfflineTime(), e);
                throw new IllegalArgumentException("下线时间格式错误，正确格式：yyyy-MM-dd HH:mm:ss");
            }
        }

        return this.save(entity);
    }

    /**
     * 开关柜指派员工
     *
     * @param id                主键ID
     * @param switchCabinetForm 开关柜表单
     * @return 是否成功
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateSwitchCabinet(Long id, SwitchCabinetForm switchCabinetForm) {
        SwitchCabinet existCabinet = this.getById(id);
        Assert.notNull(existCabinet, "开关柜不存在");

        if (StrUtil.isNotBlank(switchCabinetForm.getSnCode())) {
            long count = this.count(new LambdaQueryWrapper<SwitchCabinet>()
                    .ne(SwitchCabinet::getId, id)
                    .eq(SwitchCabinet::getSnCode, switchCabinetForm.getSnCode())
                    .eq(SwitchCabinet::getIsDeleted, 0));
            Assert.isTrue(count == 0, "SN号已存在");
            existCabinet.setSnCode(switchCabinetForm.getSnCode());
        }

        if (StrUtil.isNotBlank(switchCabinetForm.getProductionLine())) {
            existCabinet.setProductionLine(switchCabinetForm.getProductionLine());
        }

        if (StrUtil.isNotBlank(switchCabinetForm.getOfflineTime())) {
            try {
                existCabinet.setOfflineTime(LocalDateTime.parse(
                        switchCabinetForm.getOfflineTime(),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            } catch (Exception e) {
                log.error("下线时间格式错误: {}", switchCabinetForm.getOfflineTime(), e);
                throw new IllegalArgumentException("下线时间格式错误，正确格式：yyyy-MM-dd HH:mm:ss");
            }
        }

        if (StrUtil.isNotBlank(switchCabinetForm.getFunctionStarttime())) {
            try {
                existCabinet.setFunctionStarttime(LocalDateTime.parse(
                        switchCabinetForm.getFunctionStarttime(),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            } catch (Exception e) {
                log.error("功能开始时间格式错误: {}", switchCabinetForm.getFunctionStarttime(), e);
                throw new IllegalArgumentException("功能开始时间格式错误，正确格式：yyyy-MM-dd HH:mm:ss");
            }
        }

        if (StrUtil.isNotBlank(switchCabinetForm.getFunctionEndtime())) {
            try {
                existCabinet.setFunctionEndtime(LocalDateTime.parse(
                        switchCabinetForm.getFunctionEndtime(),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            } catch (Exception e) {
                log.error("功能结束时间格式错误: {}", switchCabinetForm.getFunctionEndtime(), e);
                throw new IllegalArgumentException("功能结束时间格式错误，正确格式：yyyy-MM-dd HH:mm:ss");
            }
        }

        String oldFunctionEmpName = existCabinet.getFunctionEmpName();
        String newFunctionEmpName = switchCabinetForm.getFunctionEmpName();
        String newSnCode = existCabinet.getSnCode();

        if (newFunctionEmpName != null) {
            if (!newFunctionEmpName.equals(oldFunctionEmpName)) {
                if (StrUtil.isNotBlank(newFunctionEmpName)) {
                    EmployeeTask newEmployee = employeeTaskMapper.selectOne(
                            new LambdaQueryWrapper<EmployeeTask>()
                                    .eq(EmployeeTask::getEmpName, newFunctionEmpName)
                                    .eq(EmployeeTask::getTaskType, 2)
                                    .eq(EmployeeTask::getIsDeleted, 0)
                                    .last("LIMIT 1")
                    );
                    if (newEmployee != null) {
                        employeeTaskService.assignSnCodeToEmployee(
                                newEmployee.getEmpId(), newFunctionEmpName, newSnCode);
                    }
                }
            }
            else if (StrUtil.isBlank(oldFunctionEmpName) && StrUtil.isNotBlank(newFunctionEmpName)) {
                EmployeeTask employee = employeeTaskMapper.selectOne(
                        new LambdaQueryWrapper<EmployeeTask>()
                                .eq(EmployeeTask::getEmpName, newFunctionEmpName)
                                .eq(EmployeeTask::getTaskType, 2)
                                .eq(EmployeeTask::getIsDeleted, 0)
                                .last("LIMIT 1")
                );
                if (employee != null) {
                    employeeTaskService.assignSnCodeToEmployee(
                            employee.getEmpId(), newFunctionEmpName, newSnCode);
                }
            }

            existCabinet.setFunctionEmpName(newFunctionEmpName);
        }
        else if (StrUtil.isNotBlank(oldFunctionEmpName) && newFunctionEmpName == null) {
            EmployeeTask oldEmployee = employeeTaskMapper.selectOne(
                    new LambdaQueryWrapper<EmployeeTask>()
                            .eq(EmployeeTask::getEmpName, oldFunctionEmpName)
                            .eq(EmployeeTask::getTaskType, 2)
                            .eq(EmployeeTask::getIsDeleted, 0)
                            .last("LIMIT 1")
            );
            if (oldEmployee != null) {
                employeeTaskService.removeSnCodeFromEmployee(
                        oldEmployee.getEmpId(), newSnCode);
            }
            existCabinet.setFunctionEmpName(null);
        }

        if (StrUtil.isNotBlank(switchCabinetForm.getArea())) {
            existCabinet.setArea(switchCabinetForm.getArea());
        }

        return this.updateById(existCabinet);
    }

    /**
     * 删除开关柜
     *
     * @param ids 主键ID，多个以英文逗号(,)分割
     * @return 是否成功
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteSwitchCabinets(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的开关柜数据为空");

        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .collect(Collectors.toList());

        long count = this.count(new LambdaQueryWrapper<SwitchCabinet>()
                .in(SwitchCabinet::getId, idList)
                .eq(SwitchCabinet::getIsDeleted, 0)
        );

        Assert.isTrue(count > 0, "开关柜不存在或已被删除");

        boolean result = this.removeByIds(idList);

        if (result) {
            log.info("删除开关柜成功，IDs: {}", ids);
        }

        return result;
    }
}
