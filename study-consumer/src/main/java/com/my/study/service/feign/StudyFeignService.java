package com.my.study.service.feign;


import com.my.study.entity.ConsultContent;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/*
* fallback = StudyFeignFallback.class
* 不能获取具体异常
*
* */
@FeignClient(name = "study-producer"
        /*fallback = StudyFeignFallback.class,*/
        ,fallbackFactory = StudyFeignFallbackFactory.class)
public interface StudyFeignService {

    @GetMapping("/study/queryContent")
    List<ConsultContent> queryContents();

}
