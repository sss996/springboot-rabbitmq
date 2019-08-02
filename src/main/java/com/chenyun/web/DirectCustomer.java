package com.chenyun.web;

import com.chenyun.config.RabbitConstants;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DirectCustomer {

    // 定义监听字符串队列T
    @RabbitListener(queues = {RabbitConstants.D_QUEUE1})
    public void receiveMsg1(String msg){
        System.out.println("D_QUEUE1 收到消息："+msg);

    }

}
