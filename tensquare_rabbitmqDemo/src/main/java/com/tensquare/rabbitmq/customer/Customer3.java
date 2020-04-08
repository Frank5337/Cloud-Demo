package com.tensquare.rabbitmq.customer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author: zbl
 * @Date: 11:18 2019/10/4
 * @Description:
 */
@Component
@RabbitListener(queues = "HUATU")
public class Customer3 {

   /* @RabbitHandler
    public void getMsg1(String msg){
        System.out.println("直接模式消费者: 消费消息: " + msg);
    }*/

    @RabbitHandler
    public void getMsg(String msg){
        System.out.println("HUATU: " + msg);
    }


}
