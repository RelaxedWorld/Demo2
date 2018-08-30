package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.domains.EmailMessage;
import com.example.demo.domains.OperateLog;
import com.example.demo.domains.User;
import com.example.demo.listener.event.OperateLogEvent;
import com.example.demo.mq.Producer;
import com.example.demo.utils.RSAUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/")
@Slf4j
public class EmailController {
    @Autowired
    private Producer producer;
    @Resource
    private ApplicationEventPublisher publisher;                    // 事件发布器

    @GetMapping("sendEmail")
    public String sendEmail(HttpServletRequest request) {
        //1.通过mq异步发送邮件
        Map params = new HashMap();
        params.put("id", 2);
        EmailMessage emailMessage = new EmailMessage();
        emailMessage.setRecipient("1003695258@qq.com");
        emailMessage.setSubject("激活邮件");
        emailMessage.setTemplate("emailTemplate");
        emailMessage.setParams(params);
        Destination destination = new ActiveMQQueue("email.queue");
        producer.sendMessage(destination, JSON.toJSONString(emailMessage));
        //2.通过java事件引擎异步记录日志
        OperateLog operateLog = new OperateLog();
        operateLog.setBusinessId(123L);
        operateLog.setOpetater("admin");
        operateLog.setUserIp(request.getRemoteHost());
        operateLog.setOperateType(1);//测试
        operateLog.setContent("测试日志");
        saveOperateLog(operateLog);
        log.info("邮件发送成功，日志记录成功");
        return "发送成功...";
    }

    private String validate(User user) throws InvalidKeySpecException, NoSuchAlgorithmException {
        String privateKey2 = "MIIBUwIBADANBgkqhkiG9w0BAQEFAASCAT0wggE5AgEAAkEAnl+Cdh/E9NoJ/73Vd8QIBSKSkN6S4U23/kzrR9kdIoiPgiqKYfqJ6S9+Fd5K5PNXYVVT0V5gCAjbFGlhHkR9FQIDAQABAkAMuH8yHbskL589+3F5/TTntK8xE6+YiTWOJ39+deHxNAWbHdxdvNX9vs74C/WiW0NQtPsHiLE2dXq4V/DJjD4BAiEA/dQTbEdjTg0FN7r2usNesYwYz35U+ykZyQpvNnMgzukCIQCful8eUWOyZah9RJX5QjfGtbVVB2DlTRnPOhdKf7WZTQIgcIMjIOn78MsAtxVe7KZRQS4yiTccgEBVt7GyXlZmApECIHcpFuULWxEv/e7WR/xYhjvcO5dflc37IxISCGYvuE7RAiBg3q2JLsEYeRlpIBOAZLHfkiA39Xbh4y3IuBKTNeyCqQ==";
        String userName = RSAUtils.privateDecrypt(user.getUserName(), RSAUtils.getPrivateKey(privateKey2));
        System.out.println("解密后文字userName: \r\n" + userName);
        String userPwd = RSAUtils.privateDecrypt(user.getUserPwd(), RSAUtils.getPrivateKey(privateKey2));
        System.out.println("解密后文字userPwd: \r\n" + userPwd);
        user.setUserName(userName);
        user.setUserPwd(userPwd);
        if (user != null) {
            if (!("zsy".equals(user.getUserName()) && "zsy".equals(user.getUserPwd()))) {
                return "用户不存在哦";
            }
        } else {
            return "用户不存在哦";
        }
        return "";
    }

    private void saveOperateLog(OperateLog operateLog) {
        OperateLogEvent event = new OperateLogEvent(this);
        event.setOperateLog(operateLog);
        publisher.publishEvent(event);
    }
}
