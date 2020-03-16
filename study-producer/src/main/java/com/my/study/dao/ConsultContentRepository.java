package com.my.study.dao;


import com.my.study.entity.ConsultContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ConsultContentRepository  extends JpaRepository<ConsultContent, Integer>, JpaSpecificationExecutor<ConsultContent> {

}


