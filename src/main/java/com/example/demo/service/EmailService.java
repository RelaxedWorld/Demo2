package com.example.demo.service;

import java.util.Map;

public interface EmailService {
    void sendTemplateMail(String recipient, String subject, String template, Map params);
}
