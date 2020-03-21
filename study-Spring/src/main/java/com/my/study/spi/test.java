package com.my.study.spi;

import java.util.ServiceLoader;

public class test {


    public static void main(String[] args) {
        //返回配置的所有实例
        ServiceLoader<SpiService> loads = ServiceLoader.load(SpiService.class);
        for (SpiService load:loads){
            load.testSpi();
        }
    }
}
