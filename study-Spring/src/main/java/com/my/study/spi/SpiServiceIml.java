package com.my.study.spi;

import com.my.study.beanDefinition.MyService;

@MyService
public class SpiServiceIml implements SpiService {

    @Override
    public void testSpi() {
        System.out.println("SPi-------------->");
    }
}
