package com.my.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;



//http://localhost:7070/actuator/routes 查看所有路由规则

@SpringBootApplication
@EnableZuulProxy
//@EnableZuulServer
public class StudyZuulApplication {
    public static void main(String[] args) {

        SpringApplication.run(StudyZuulApplication.class,args);
    }

    //动态刷新配置
    @Bean
    @RefreshScope
    @ConfigurationProperties("zuul")
    @Primary
    public ZuulProperties zuulProperties() {
        return new ZuulProperties();
    }
}
