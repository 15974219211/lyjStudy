package com.my.study.controller;


import com.my.study.dao.ConsuilContentMapper;
import com.my.study.entity.ConsultContent;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/study")
@RestController
@Api(tags = "学习springboot接口")
public class StudyController {

    @Autowired
    private ConsuilContentMapper consuilContentMapper;

    @ApiOperation("测试接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", defaultValue = "jack"),
            @ApiImplicitParam(name = "address", value = "用户地址", defaultValue = "长沙")
    })
    @GetMapping("/testMybatis")
    public List<ConsultContent> findAll(){


        return consuilContentMapper.findAll123();
    }
}
