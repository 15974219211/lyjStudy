package com.my.study.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.my.study.service.ProviderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestCOntroller {

    @Reference
    private ProviderService providerService;

    @RequestMapping("/dubbo")
    public  String test1(){

        return  providerService.getAll();
    }
}
