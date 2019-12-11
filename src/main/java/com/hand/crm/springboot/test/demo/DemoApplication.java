package com.hand.crm.springboot.test.demo;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;

import javax.jms.Queue;
import javax.jms.Topic;

@SpringBootApplication
@MapperScan("com.hand.crm.springboot.test.demo.dao")
@EnableJms
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public Queue queue() {
        return new ActiveMQQueue("springbootTest");
    }

    @Bean
    public Topic userTopic(){
        return new ActiveMQTopic("spring.topic");
    }
}
