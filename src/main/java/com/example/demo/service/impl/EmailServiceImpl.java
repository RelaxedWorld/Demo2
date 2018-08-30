package com.example.demo.service.impl;

import com.example.demo.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Map;
@Service
@Slf4j
public class EmailServiceImpl implements EmailService {
    @Resource
    private JavaMailSender javaMailSender;

    @Resource
    private TemplateEngine templateEngine;

    //读取配置文件中的参数
    @Value("${spring.mail.username}")
    private String sender;

    @Override
    public void sendTemplateMail(String recipient, String subject, String template, Map params) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(sender);
            helper.setTo(recipient);
            helper.setSubject(subject);

            Context context = new Context();
            context.setVariables(params);
            String emailContent = templateEngine.process(template, context);
            helper.setText(emailContent, true);
        } catch (MessagingException e) {
            throw new RuntimeException("Messaging  Exception !", e);
        }
        log.info("sendTemplateMail recipient:{},params:{}", recipient, params.toString());
        javaMailSender.send(message);
    }
}
