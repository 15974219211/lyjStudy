package com.my.study;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;

@SpringBootApplication
//注册到eureka
@EnableEurekaClient
//开启断路器功能
@EnableCircuitBreaker
//开启feign支持，clients指定哪个类开启feign
@EnableFeignClients
@EnableBinding({Sink.class})
//开启重试功能
//@EnableRetry
public class StudyConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyConsumerApplication.class,args);
    }
}
