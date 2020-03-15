package com.my.study.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试路由到本地
 */
@Slf4j
@RestController
@RequestMapping("/local")
public class LocalController {

    @RequestMapping("/queryUser")
    public String queryUser() {
        log.info("zuul->queryUser");
        return "zuul->queryUser";
    }
}
