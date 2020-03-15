package com.my.study.controller;

import com.my.study.entity.ConsultContent;
import com.my.study.service.StudyRestTemplateService;
import com.my.study.service.feign.StudyFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/study")
public class ConsumerController {

    @Autowired
    private StudyRestTemplateService studyRestTemplateService;

    @Autowired
    private StudyFeignService studyFeignService;

    @Autowired
    Environment environment;

    @RequestMapping("/template")
    public List<ConsultContent> hghgh(HttpServletRequest request) {
        log.info("==================已经调用==========" + request.getRemotePort());
        log.info("Environment======username======" + environment.getProperty("username"));
        List<ConsultContent> consultContents = studyRestTemplateService.queryContents();
        return consultContents;
    }

    @RequestMapping("/feignService")
    public List<ConsultContent> feignService(HttpServletRequest request) {
        log.info("==================已经调用==========" + request.getRemotePort());
        log.info("Environment======username======" + environment.getProperty("username"));

        return studyFeignService.queryContents();
    }
}
