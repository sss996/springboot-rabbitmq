package com.chenyun.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 初始化Fanout交换机和队列，并将其绑定
 * */
@Configuration
public class DirectConfig {

    // 初始化创建交换机
    @Bean
    public DirectExchange DirectExchange(){
        return new DirectExchange(RabbitConstants.DIRECT_EXCHANGE);
    }

    // 初始化创建队列
    @Bean
    public Queue getQueue1(){
        return  new Queue(RabbitConstants.D_QUEUE1,true);
    }

    // 将交换机和 和队列绑定
    @Bean
    public Binding binding1(){
        return BindingBuilder.bind(getQueue1()).to(DirectExchange()).with("insert");
    }

}
