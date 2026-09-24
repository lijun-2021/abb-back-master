package com.youlai.boot.system.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.youlai.boot.system.mapper.FunctionOutputMapper;
import com.youlai.boot.system.model.query.DisplayPageQuery;
import com.youlai.boot.system.model.query.FunctionOutputQuery;
import com.youlai.boot.system.model.vo.DisplayPageVO;
import com.youlai.boot.system.model.vo.FunctionOutputEmployeeVO;
import com.youlai.boot.system.model.vo.FunctionOutputOverviewVO;
import com.youlai.boot.system.service.DisplayService;
import com.youlai.boot.system.service.FunctionOutputService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FunctionOutputServiceImpl implements FunctionOutputService {

    private static final List<String> TEAMS = List.of("A", "B", "C", "D");
    private static final ZoneId BUSINESS_ZONE = ZoneId.of("Asia/Shanghai");

    private final FunctionOutputMapper functionOutputMapper;
    private final DisplayService displayService;

    @Override
    public FunctionOutputOverviewVO getOverview(FunctionOutputQuery queryParams) {
        LocalDate statDate = queryParams == null || queryParams.getStatDate() == null
                ? LocalDate.now(BUSINESS_ZONE)
                : queryParams.getStatDate();
        String empName = queryParams != null && StrUtil.isNotBlank(queryParams.getEmpName())
                ? queryParams.getEmpName().trim()
                : null;
        String empTeam = queryParams == null ? null : queryParams.getEmpTeam();
        DisplayPageQuery displayQuery = new DisplayPageQuery();
        displayQuery.setPageNum(1);
        displayQuery.setPageSize(1);
        IPage<DisplayPageVO> displayPage = displayService.getDisplayPage(displayQuery);
        List<DisplayPageVO> displayRecords = displayPage == null ? null : displayPage.getRecords();
        DisplayPageVO latestDisplay = displayRecords == null || displayRecords.isEmpty()
                ? null
                : displayRecords.get(0);

        FunctionOutputOverviewVO overview = new FunctionOutputOverviewVO();
        overview.setStatDate(statDate);
        overview.setOfflineTotal(latestDisplay == null ? 0L : zero(latestDisplay.getFqcDailyInput()));
        overview.setFunctionOutputTotal(
                latestDisplay == null ? 0L : zero(latestDisplay.getFqcFunctionTestOutput()));
        overview.setUpdateTime(latestDisplay == null ? null : latestDisplay.getUpdateTime());

        Map<String, FunctionOutputOverviewVO.GroupVO> groups = createGroups();
        List<FunctionOutputEmployeeVO> employees =
                functionOutputMapper.getEmployeeStats(statDate, empName, empTeam);
        if (employees != null) {
            employees.stream()
                    .filter(employee -> employee != null && groups.containsKey(employee.getEmpTeam()))
                    .sorted(Comparator.comparing(
                            FunctionOutputEmployeeVO::getEmpId,
                            Comparator.nullsLast(String::compareTo)))
                    .forEach(employee -> addEmployee(groups.get(employee.getEmpTeam()), employee));
        }
        overview.setGroups(new ArrayList<>(groups.values()));
        return overview;
    }

    private static Map<String, FunctionOutputOverviewVO.GroupVO> createGroups() {
        Map<String, FunctionOutputOverviewVO.GroupVO> groups = new LinkedHashMap<>();
        for (String team : TEAMS) {
            FunctionOutputOverviewVO.GroupVO group = new FunctionOutputOverviewVO.GroupVO();
            group.setEmpTeam(team);
            group.setAssignedCount(0L);
            group.setCompletedCount(0L);
            group.setIncompleteCount(0L);
            group.setEmployees(new ArrayList<>());
            groups.put(team, group);
        }
        return groups;
    }

    private static void addEmployee(
            FunctionOutputOverviewVO.GroupVO group,
            FunctionOutputEmployeeVO employee) {
        employee.setAssignedCount(zero(employee.getAssignedCount()));
        employee.setCompletedCount(zero(employee.getCompletedCount()));
        employee.setIncompleteCount(zero(employee.getIncompleteCount()));
        group.getEmployees().add(employee);
        group.setAssignedCount(group.getAssignedCount() + employee.getAssignedCount());
        group.setCompletedCount(group.getCompletedCount() + employee.getCompletedCount());
        group.setIncompleteCount(group.getIncompleteCount() + employee.getIncompleteCount());
    }

    private static Long zero(Long value) {
        return value == null ? 0L : value;
    }
}
