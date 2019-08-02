package com.chenyun.config;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;


/**
 * 初始化Fanout交换机和队列，并将其绑定
 * */
@Configuration
public class FanoutConfig {

    // 初始化创建交换机
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange(RabbitConstants.FANOUT_EXCHANGE);
    }

    // 初始化创建队列
    @Bean
    public Queue getQueue1(){
        return  new Queue(RabbitConstants.F_QUEUE1,true);
    }
    @Bean
    public Queue getQueue2(){
        return  new Queue(RabbitConstants.F_QUEUE2,true);
    }
    @Bean
    public Queue getQueue3(){
        return  new Queue(RabbitConstants.F_QUEUE3,true);
    }

    // 将交换机和 和队列绑定
    @Bean
    public Binding binding1(){
        return BindingBuilder.bind(getQueue1()).to(fanoutExchange());
    }
    @Bean
    public Binding binding2(){
        return BindingBuilder.bind(getQueue2()).to(fanoutExchange());
    }
    @Bean
    public Binding binding3(){
        return BindingBuilder.bind(getQueue3()).to(fanoutExchange());
    }

//    public ConnectionFactory connectionFactory() {
//
//        //把基本参数带入，创建连接工厂
//        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
//        connectionFactory.setHost("localhost");
//        connectionFactory.setPort(5672);
//        connectionFactory.setUsername("admin");
//        connectionFactory.setPassword("123456");
//        connectionFactory.setPublisherConfirms(true); // enable confirm mode
//        return connectionFactory;
//
//    }
//
//    @Bean
//    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
//    //声明作用域prototype类型
//    public RabbitTemplate rabbitTemplate() {
//        RabbitTemplate template = new RabbitTemplate(connectionFactory());
//        return template;
//    }

//    // SimpleMessageListenerContainer类非常的强大，我们可以对他进行很多的设置，
//    // 用对于消费者的配置项，这个类都可以满足。
//    // 它有监听单个或多个队列、自动启动、自动声明功能
//    @Bean
//    public SimpleMessageListenerContainer messageListenerContainer(){
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//
//        container.setConnectionFactory(connectionFactory());
//        // 设置要监听的队列
//        container.setQueues(getQueue1(),getQueue2(),getQueue3());
//        //打开监听通道
//        container.setExposeListenerChannel(true);
//        //最大消费者数量
//        container.setMaxConcurrentConsumers(3);
//        //每次消费者接收时，都以当前消费者去接
//        // 设置确认模式：Manual （n.手册，adj.手动的）手动，Auto 自动
//        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
//
//        container.setMessageListener(new ChannelAwareMessageListener() {
//            @Override
//            public void onMessage(Message message, Channel channel) throws Exception {
//                byte[] body = message.getBody();
//                System.out.println("receive msg a: " + new String(body));
//                Thread.sleep(30000);
//                channel.basicAck(message.getMessageProperties().getDeliveryTag(), true); //确认消息成功消费
//
//
//            }
//        });
//
//        return container;
//    }

}
