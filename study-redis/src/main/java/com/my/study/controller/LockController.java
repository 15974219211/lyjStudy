package com.my.study.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;


@RestController
public class LockController {
    private static long count = 20;
    private CountDownLatch countDownLatch = new CountDownLatch(5);

    @Resource(name="redisLock")
    private Lock lock;

    @RequestMapping(value = "/testLock", method = RequestMethod.GET)
    public Long sale() throws InterruptedException {
        count = 20;
        countDownLatch = new CountDownLatch(5);
        new PlusThread().start();
        new PlusThread().start();
        new PlusThread().start();
        new PlusThread().start();
        new PlusThread().start();
        return count;
    }


    public class PlusThread extends Thread {
        private int amount = 0;

        @Override
        public void run() {
            countDownLatch.countDown();
            if (countDownLatch.getCount()==0){
                System.out.println("----------结果------------------------------");
            }
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            while (count > 0) {
                lock.lock();
                try {
                    if (count > 0) {
                        amount++;
                        count--;
                    }
                }finally{
                    lock.unlock();
                }

                try {
                    Thread.sleep(10);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + "售出"+ (amount) + "张票");
        }
    }
}