package com.my.study.controller;


import com.my.study.dao.ConsultContentRepository;
import com.my.study.entity.ConsultContent;
import com.my.study.rabbitmq.Producer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;

@RefreshScope
@RestController
@RequestMapping("/study")
@Slf4j
public class ProduceController {

    /**
     * ## 手动刷新配置url  http://localhost:8085/actuator/bus-refresh
     */

    @Value("${server.port}")
    private String port;
    @Autowired
    private ConsultContentRepository consultContentRepository;
    //通过@Value获取分布式配置中心配置的值
  /*  @Value("${redis.password}")
    private String redispass;*/
    //通过Spring 环境获取
    @Autowired
    Environment environment;

    @Value("${test}")
    private  String test;
    @Autowired
    private Producer producer;

    @RequestMapping("/queryContent")
    public List<ConsultContent> queryContent(HttpServletRequest request) {

        Cookie[] cookies = request.getCookies();

        if(cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                log.info("cookie name = >" + cookies[i].getName() + "= >" + "cookie value = " + cookies[i].getValue());
            }
        }
        Enumeration<String> headerNames = request.getHeaderNames();
        while(headerNames.hasMoreElements()) {
            String headername = headerNames.nextElement();
            log.info("header name = >" + headername + "= >" + "headervalue = " + request.getHeader(headername));
        }



        producer.send();
        log.info("本程序的端口------>>" + port);
        log.info("test-->"+test);
        //log.info("@Value-->"+redispass);
        log.info("Environment======redispass======" + environment.getProperty("redis.password"));
        return consultContentRepository.findAll();
    }
}
