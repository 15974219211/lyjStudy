package com.my.study.service;


import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import com.my.study.entity.Provinces;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * 缓存穿透
 */
//@Service("provincesService")
public class ProvincesServiceImpl4 extends ProvincesServiceImpl implements YqGwProvinceService {
    private BloomFilter<String> bf =null; //类似一个set集合

    @PostConstruct
    public void init(){//实例化bloomFilter,并加载数据
        List<Provinces> provinces = this.list();

        bf = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), provinces.size());
        for (Provinces p : provinces) {
            bf.put(p.getId());
        }
    }

    @Override
    @Cacheable(value = "province")
    public Provinces detail(String provinceid) {
        //先判断布隆过滤器中是否存在该值，值存在才允许访问缓存和数据库
        if(!bf.mightContain(provinceid)){
            System.out.println("非法访问--------"+System.currentTimeMillis());
            return null;
        }
        System.out.println("数据库中得到数据--------"+System.currentTimeMillis());
        Provinces provinces = super.detail(provinceid);

        return provinces;
    }

    @Override
    @CachePut(value = "province",key = "#entity.provinceid")
    public Provinces update(Provinces entity) {
        super.update(entity);
        return entity;
    }

    @Override
    @CacheEvict(value = "province",key = "#entity.provinceid")
    public Provinces add(Provinces entity) {
        super.add(entity);
        bf.put(entity.getId());//新生成，加入过滤器
        return entity;
    }

    @Override
    @CacheEvict("province")
    public void delete(String provinceid) {
        super.delete(provinceid);
    }
}