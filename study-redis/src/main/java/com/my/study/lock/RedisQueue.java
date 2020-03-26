package com.my.study.lock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Queue;
import java.util.concurrent.TimeUnit;

@Component
public class RedisQueue{

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    public void  push(String str){

        Long queueKey = stringRedisTemplate.opsForList().leftPush("queueKey", str);

    }

    public  String  pop(){
        //String value = stringRedisTemplate.opsForList().rightPop("queueKey");
        String value = stringRedisTemplate.opsForList().rightPop("queueKey", 3, TimeUnit.SECONDS);
        return  value;
    }
}
