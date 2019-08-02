package com.chenyun.web;

import com.chenyun.config.RabbitConstants;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class FanoutCustomer {

    // 定义监听字符串队列
    @RabbitListener(queues = {RabbitConstants.F_QUEUE1})
    public void receiveMsg1(String msg){
        System.out.println("F_QUEUE1 收到消息："+msg);

    }

    // 定义监听字符串队列
    @RabbitListener(queues = {RabbitConstants.F_QUEUE2})
    public void receiveMsg2(String msg){
        System.out.println("F_QUEUE2 收到消息："+msg);
    }

    // 定义监听字符串队列
    @RabbitListener(queues = {RabbitConstants.F_QUEUE3})
    public void receiveMsg3(String msg){
        System.out.println("F_QUEUE3 收到消息："+msg);
    }

}
