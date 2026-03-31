package com.abb_soft_01.mpdemo.mapper;

import com.abb_soft_01.mpdemo.entity.abb_timedemo_0125;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
@MapperScan("com.abb_soft_01.mpdemo.mapper")
public interface AbbTimedemo0125Mapper {

    @Select("select * from abb_timedemo_0125")
    public List<abb_timedemo_0125> find();
}
