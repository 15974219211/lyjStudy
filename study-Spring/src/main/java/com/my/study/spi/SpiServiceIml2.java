package com.my.study.spi;

import com.my.study.beanDefinition.MyService;

@MyService
public class SpiServiceIml2 implements SpiService {

    @Override
    public void testSpi() {
        System.out.println("SPi2222222222-------------->");
    }
}
