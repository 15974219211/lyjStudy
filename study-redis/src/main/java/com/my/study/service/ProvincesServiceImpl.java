package com.my.study.service;


import com.my.study.dao.YqGwProvinceRepository;
import com.my.study.entity.Provinces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 原始实现
 */
@Service("provincesService")
public class ProvincesServiceImpl implements YqGwProvinceService {

    @Autowired
    private YqGwProvinceRepository provincesDao;


    @Override
    public List<Provinces> list(){
        return provincesDao.findAll();
    }

    @Override
    public Provinces detail(String provinceid) {
        Provinces provinces = null;

        System.out.println("数据库中得到数据--------"+System.currentTimeMillis());
        provinces = provincesDao.findById(provinceid).get();


        return provinces;
    }

    @Override
    public Provinces update(Provinces entity) {
        provincesDao.save(entity);
        return entity;
    }

    @Override
    public Provinces add(Provinces entity) {
        provincesDao.save(entity);
        return entity;
    }

    @Override
    public void delete(String provinceid) {
        provincesDao.deleteById(provinceid);
    }


}