package com.my.study.rabbitmq;


import com.my.study.entity.ConsultContent;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @StreamListener(Sink.INPUT)
    public  void  consumer(ConsultContent consultContent){

        System.out.println("消费者消费消息--->>"+consultContent.toString());
    }
}
