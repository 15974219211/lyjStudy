package com.my.study.service.feign;

import com.google.common.collect.Lists;
import com.my.study.entity.ConsultContent;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class StudyFeignFallbackFactory implements FallbackFactory<StudyFeignService> {

    @Override
    public StudyFeignService create(Throwable throwable) {

        if(throwable == null) {
            return null;
        }
        final String msg = throwable.getMessage();
        log.info("exception:" + msg);
        return new StudyFeignService() {
            @Override
            public List<ConsultContent> queryContents() {
                ConsultContent consultContent = new ConsultContent();
                consultContent.setContent("StudyFeignFallbackFactory->>发生异常了");
                return Lists.newArrayList(consultContent);
            }
        };
    }
}
