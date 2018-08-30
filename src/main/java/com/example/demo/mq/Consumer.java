package com.example.demo.mq;


import com.alibaba.fastjson.JSON;
import com.example.demo.domains.EmailMessage;
import com.example.demo.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Consumer {
    @Autowired
    private EmailService emailService;

    // 使用JmsListener配置消费者监听的队列，其中text是接收到的消息
    @JmsListener(destination = "email.queue")
    public void receiveEmailQueue(String text) {
        log.info("Consumer收到的报文为:" + text);
        EmailMessage emailMessage = JSON.parseObject(text, EmailMessage.class);
        //用于发送短信，这里先注释掉
//        emailService.sendTemplateMail(emailMessage.getRecipient(), emailMessage.getSubject(), emailMessage.getTemplate(), emailMessage.getParams());
    }
}

