package com.my.study.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.my.study.entity.ConsultContent;

import java.util.List;


public interface ConsuilContentMapper extends BaseMapper<ConsultContent> {


    List<ConsultContent> findAll123();
}
