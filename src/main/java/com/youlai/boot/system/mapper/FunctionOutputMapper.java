package com.youlai.boot.system.mapper;

import com.youlai.boot.system.model.vo.FunctionOutputEmployeeVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface FunctionOutputMapper {

    List<FunctionOutputEmployeeVO> getEmployeeStats(
            @Param("statDate") LocalDate statDate,
            @Param("empName") String empName,
            @Param("empTeam") String empTeam);
}
