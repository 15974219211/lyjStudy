package com.my.study.service.feign;


import com.google.common.collect.Lists;
import com.my.study.entity.ConsultContent;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 这种方式无法捕获到具体异常
 */
@Service
public class StudyFeignFallback implements StudyFeignService {
    @Override
    public List<ConsultContent> queryContents() {
        ConsultContent consultContent = new ConsultContent();
        consultContent.setContent("StudyFeignFallback->>发生异常了");
        return Lists.newArrayList(consultContent);
    }
}
