package com.my.study.rabbitmq;


import com.my.study.dao.ConsultContentRepository;
import com.my.study.entity.ConsultContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class Producer {

    @Autowired
    private Source source;

    @Autowired
    private ConsultContentRepository consultContentRepository;
    public  void  send(){
        System.out.println("生产者发送消息");
        ConsultContent consultContent = consultContentRepository.findAll().get(0);
        source.output().send(MessageBuilder.withPayload(consultContent).build());
    }
}
