package com.my.study.exchange.direct;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 *类说明：一个连接多个信道
 */
public class MultiChannelConsumer {

    private static class ConsumerWorker implements Runnable{
        final Connection connection;

        public ConsumerWorker(Connection connection) {
            this.connection = connection;
        }

        @Override
        public void run() {
            try {
                /*创建一个信道，意味着每个线程单独一个信道*/
                Channel channel = connection.createChannel();
                //信道设置交换器类型(direct)
                channel.exchangeDeclare(DirectProducer.EXCHANGE_NAME,BuiltinExchangeType.DIRECT);
                // 声明一个随机队列，每个线程的队列都是不一样的，会收到匹配的全部消息
                 //String queueName = channel.queueDeclare().getQueue();
                String queueName = "queue-king";      // 同一个队列,会平分消息
                //消费者名字，打印输出用
                final String consumerName =  Thread.currentThread().getName()+"-all";
                /*队列绑定到交换器上时，是允许绑定多个路由键的，也就是多重绑定*/
                String[] routekeys={"king","mark"};
                for(String routekey:routekeys){
                    channel.queueBind(queueName,DirectProducer.EXCHANGE_NAME,
                            routekey);
                }
                System.out.println("["+consumerName+"] Waiting for messages:");

                // 创建队列消费者
                final Consumer consumerA = new DefaultConsumer(channel) {
                    @Override
                    public void handleDelivery(String consumerTag,
                                               Envelope envelope,
                                               AMQP.BasicProperties
                                                       properties,
                                               byte[] body)
                            throws IOException {
                        String message =
                                new String(body, "UTF-8");
                        System.out.println(consumerName
                                +" Received "  + envelope.getRoutingKey()
                                + ":'" + message + "'");
                    }
                };
                channel.basicConsume(queueName, true, consumerA);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] argv) throws IOException,
            InterruptedException, TimeoutException {
        //连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //连接rabbitMq的地址
        factory.setHost(RabbitMqConst.HOST);
        // 打开连接和创建频道，与发送端一样
        Connection connection = factory.newConnection();
        //一个连接多个信道
        for(int i=0;i<2;i++){
            /*将连接作为参数，传递给每个线程*/
            Thread worker =new Thread(new ConsumerWorker(connection));
            worker.start();
        }
    }
}
