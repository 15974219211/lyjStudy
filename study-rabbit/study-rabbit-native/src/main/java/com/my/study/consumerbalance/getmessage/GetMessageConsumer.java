package com.my.study.consumerbalance.getmessage;

import com.my.study.exchange.direct.RabbitMqConst;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.GetResponse;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 *类说明：消费者——拉取模式，很消耗性能
 */
public class GetMessageConsumer {


    public static void main(String[] argv)
            throws IOException, TimeoutException, InterruptedException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(RabbitMqConst.HOST);

        // 打开连接和创建频道，与发送端一样
        Connection connection = factory.newConnection();
        final Channel channel = connection.createChannel();

        channel.exchangeDeclare(GetMessageProducer.EXCHANGE_NAME,
                "direct");
        // 声明一个队列
        String queueName = "focuserror";
        channel.queueDeclare(queueName,
                false,false,
                false,null);

        String routekey="error";//只关注error
        channel.queueBind(queueName,
                GetMessageProducer.EXCHANGE_NAME, routekey);

        System.out.println(" [*] Waiting for messages......");
        //无限循环拉取
        while(true){
            //拉一条，false表示不自动向mq自动确认,需要手动确认,true表示自动确认
            GetResponse getResponse = channel.basicGet(queueName, false);
            if(null!=getResponse){
                System.out.println("received["
                        +getResponse.getEnvelope().getRoutingKey()+"]"
                        +new String(getResponse.getBody()));
            }
            //手动确认
            channel.basicAck(0,true);
            Thread.sleep(1000);
        }


    }

}
