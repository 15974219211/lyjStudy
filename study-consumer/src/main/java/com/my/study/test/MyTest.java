package com.my.study.test;

import com.my.study.StudyConsumerApplication;
import com.my.study.service.StudyRestTemplateService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.concurrent.CountDownLatch;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StudyConsumerApplication.class)
@WebAppConfiguration
public class MyTest {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private Integer count = 11;

    private CountDownLatch cdl = new CountDownLatch(count);

    @Autowired
    StudyRestTemplateService studyService;

    @Test
    public void hystrixTest() {

        for (Integer i = 0; i < count; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        cdl.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    logger.info(Thread.currentThread().getName() + "==>" + studyService.queryContents());
                }
            }).start();
            cdl.countDown();
        }

        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
