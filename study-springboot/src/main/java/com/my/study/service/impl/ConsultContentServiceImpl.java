package com.my.study.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.my.study.dao.ConsuilContentMapper;
import com.my.study.entity.ConsultContent;
import com.my.study.service.ConsultContentService;
import org.springframework.stereotype.Service;

@Service
public class ConsultContentServiceImpl extends ServiceImpl<ConsuilContentMapper, ConsultContent> implements ConsultContentService {
}
