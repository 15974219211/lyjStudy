package com.my.study.service;



import com.my.study.entity.ConsultContent;

import java.util.List;

/**
 * 默认是随机负载均衡策略
 */
public interface StudyRestTemplateService {
    List<ConsultContent> queryContents();

}
