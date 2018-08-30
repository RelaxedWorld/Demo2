package com.example.demo.domains;

import lombok.Data;
import lombok.ToString;

import java.util.Map;
@Data
@ToString
public class EmailMessage {
    /**
     *  收件人邮箱地址
     */
    private String recipient;
    /**
     *  主题
     */
    private String subject;
    /**
     *  邮件模板
     */
    private String template;
    /**
     *  邮件参数
     */
    private Map params;
}
