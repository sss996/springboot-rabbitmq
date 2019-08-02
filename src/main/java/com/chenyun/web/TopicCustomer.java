package com.chenyun.web;

import com.chenyun.config.RabbitConstants;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TopicCustomer {

    // 定义监听字符串队列T
    @RabbitListener(queues = {RabbitConstants.T_QUEUE1})
    public void receiveMsg1(String msg){
        System.out.println("T_QUEUE1 收到消息："+msg);

    }

    // 定义监听字符串队列
    @RabbitListener(queues = {RabbitConstants.T_QUEUE2})
    public void receiveMsg2(String msg){
        System.out.println("T_QUEUE2 收到消息："+msg);
    }

}
