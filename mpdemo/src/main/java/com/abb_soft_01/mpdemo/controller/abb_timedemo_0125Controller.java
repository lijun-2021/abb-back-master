package com.abb_soft_01.mpdemo.controller;

import com.abb_soft_01.mpdemo.entity.abb_timedemo_0125;
import com.abb_soft_01.mpdemo.mapper.AbbTimedemo0125Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class abb_timedemo_0125Controller {

    @Autowired
    private AbbTimedemo0125Mapper abb_timedemo_0125Mapper;
    @GetMapping("/test")
    public String query() {

        List<abb_timedemo_0125> list = abb_timedemo_0125Mapper.find();
        System.out.println(list);
        return "查询用户";
    }
}
