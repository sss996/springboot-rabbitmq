package com.chenyun.web;

import com.chenyun.config.RabbitConstants;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TopicPublisherController implements RabbitTemplate.ConfirmCallback {


    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping("/topic/test")
    public String send(String msg,String routingKey){

        System.out.println("发送消息：【"+ msg+"】");
        // 设置回调函数
        rabbitTemplate.setConfirmCallback(this);
        // 发送消息，
        rabbitTemplate.convertAndSend(RabbitConstants.TOPIC_EXCHANGE,"insert.user",msg);

        return msg;

    }

    // 回调确认方法
    @Override
    public void confirm(CorrelationData correlationData, boolean ask, String cause) {
        if(ask) {
            System.out.println("消费成功！");
        }else {
            System.out.println("消费失败！：" + cause);
        }
    }
}
