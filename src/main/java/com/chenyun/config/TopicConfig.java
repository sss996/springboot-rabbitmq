package com.chenyun.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 初始化Fanout交换机和队列，并将其绑定
 * */
@Configuration
public class TopicConfig {

    // 初始化创建交换机
    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(RabbitConstants.TOPIC_EXCHANGE);
    }

    // 初始化创建队列
    @Bean
    public Queue getQueue1(){
        return  new Queue(RabbitConstants.T_QUEUE1,true);
    }
    @Bean
    public Queue getQueue2(){
        return  new Queue(RabbitConstants.T_QUEUE2,true);
    }

    // 将交换机和 和队列绑定
    @Bean
    public Binding binding1(){
        return BindingBuilder.bind(getQueue1()).to(topicExchange()).with("insert.#");
    }
    @Bean
    public Binding binding2(){
        return BindingBuilder.bind(getQueue2()).to(topicExchange()).with("insert.user");
    }

}
