package com.my.study.service;

import com.my.study.entity.ConsultContent;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
public class StudyRestTemplateServiceImpl implements StudyRestTemplateService {


    public static String SERVIER_NAME = "study-producer";

    @Autowired
    private RestTemplate restTemplate;
    @HystrixCommand(fallbackMethod = "queryContentsFallback",
            commandKey = "queryContents",
            groupKey = "querygroup-one",
            commandProperties = {
                      //指定信号量的大小,指定是信号量策略才会生效
                    @HystrixProperty(name = "execution.isolation.semaphore.maxConcurrentRequests",value = "100"),
                    //指定隔离策略 THREAD线程池 SEMAPHORE 信号量
                    @HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE"),
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000000000")
            },
            //指定线程池的大小，指定是线程池策略才会生效
            threadPoolKey = "queryContentshystrixJackpool", threadPoolProperties = {
            @HystrixProperty(name = "coreSize", value = "100")
    })
    @Override
    public List<ConsultContent> queryContents() {
        log.info(Thread.currentThread().getName() + "========queryContents=========");
        List<ConsultContent> results = restTemplate.getForObject("http://"
                + SERVIER_NAME + "/study/queryContent", List.class);
        return results;
    }


    public  List<ConsultContent> queryContentsFallback() {
        log.info("========queryContentsFallback=========");
        return Collections.emptyList();
    }


}
