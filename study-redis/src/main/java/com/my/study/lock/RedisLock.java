package com.my.study.lock;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

import static java.util.Collections.singletonList;


/**
 * 使用redis实现分布式锁
 */
@Service
public class RedisLock implements Lock {

    private static final String KEY = "LOCK_KEY";


    @Autowired
    private DefaultRedisScript<Long> script;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    private ThreadLocal<String> local = new ThreadLocal<>();

    @Override
    public void lock() {
        //1.尝试加锁
        if (tryLock()) {
            return;
        }
        //2.加锁失败，当前任务休眠一段时间
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //3.递归调用，再次去抢锁
        lock();
    }


    @Override
    //使用setNx命令加锁，并生产随机值
    public boolean tryLock() {
        //产生随机值，标识本次锁编号
        String uuid = UUID.randomUUID().toString();
        Boolean ret = redisTemplate.opsForValue().setIfAbsent(KEY, uuid, 1, TimeUnit.SECONDS);
        if (ret) {
            local.set(uuid);//抢锁成功，把锁标识号记录入本线程--- Threadlocal
            return true;
        }

        //key值里面有了,uuid未能设入进去，抢锁失败
        return false;
    }

    //使用Lua解锁，确保多个指令的原子性
    @Override
    public void unlock() {
        List<String> keys = singletonList(KEY);
        redisTemplate.execute(script, keys, local.get());

    }

    //-----------------------------------------------

    @Override
    public Condition newCondition() {
        return null;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit)
            throws InterruptedException {
        return false;
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
    }

}
