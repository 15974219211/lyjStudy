package com.my.study.dao;


import com.my.study.entity.ConsultContent;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


//extends BaseMapper<ConsultContent>
public interface ConsuilContentMapper {


    List<ConsultContent> findConsultContent();
}
