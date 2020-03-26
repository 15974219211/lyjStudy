package com.my.study.dao;

import com.my.study.entity.Provinces;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface YqGwProvinceRepository extends JpaRepository<Provinces, String>, JpaSpecificationExecutor<Provinces> {

}