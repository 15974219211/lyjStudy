package com.my.study.service;

import com.alibaba.dubbo.config.annotation.Service;

@Service
public class ProviderServiceImpl implements ProviderService {

    @Override
    public String getAll() {
        System.out.println(super.getClass().getName()+"被调用一次："+System.currentTimeMillis());
        return "测试Provider";
    }
}
