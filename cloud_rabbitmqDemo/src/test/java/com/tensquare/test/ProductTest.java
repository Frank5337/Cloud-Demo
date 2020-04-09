package com.tensquare.test;

import com.cloud.rabbitmq.RabbitApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: zbl
 * @Date: 11:14 2019/10/4
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RabbitApplication.class)
public class ProductTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 直接模式
     */
    @Test
    public void sendMsg1(){
        rabbitTemplate.convertAndSend("ITCAST","直接模式提供者:我进来了");
    }

    /**
     * 分列模式
     */
    @Test
    public void sendMsg2(){
        rabbitTemplate.convertAndSend("chuanzhi","","分裂模式测试");
    }

    /**
     * 主题模式
     * itcast good.#
     * kpp    #.log
     * huatu  good.log
     */
    @Test
    public void sendMsg3(){
        rabbitTemplate.convertAndSend("TOPIC","good.log","主题模式测试");
    }


}
