package com.my.study.exchange.topic;

import com.my.study.exchange.direct.RabbitMqConst;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 5、关注所有的B章节，怎么办？
 *类说明：
 */
public class AllBConsumer {

    public static void main(String[] argv) throws IOException,
            InterruptedException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(RabbitMqConst.HOST);

        // 打开连接和创建频道，与发送端一样
        Connection connection = factory.newConnection();
        final Channel channel = connection.createChannel();

        channel.exchangeDeclare(TopicProducer.EXCHANGE_NAME,
                BuiltinExchangeType.TOPIC);
        // 声明一个随机队列
        String queueName = channel.queueDeclare().getQueue();
        //#表示以.分开的多个,*只能表示以.分开的一个
        channel.queueBind(queueName,TopicProducer.EXCHANGE_NAME, "#.B");

        System.out.println(" [*] Waiting for messages:");

        // 创建队列消费者
        final Consumer consumerA = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag,
                                       Envelope envelope,
                                       AMQP.BasicProperties properties,
                                       byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" AllConsumer Received "
                        + envelope.getRoutingKey()
                        + "':'" + message + "'");
            }
        };
        channel.basicConsume(queueName, true, consumerA);
    }
}
