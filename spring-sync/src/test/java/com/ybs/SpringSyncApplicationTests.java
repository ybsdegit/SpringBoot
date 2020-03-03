package com.ybs;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@SpringBootTest
class SpringSyncApplicationTests {

    @Autowired
    JavaMailSenderImpl mailSender;

    @Test
    void sendMail1() {
        // 一个简单的邮件
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setSubject("Springboot 测试邮件");
        mailMessage.setText("Springboot实现发送邮件");

        mailMessage.setTo("*******@163.com");
        mailMessage.setFrom("*******@163.com");
        mailSender.send(mailMessage);
    }


    @Test
    void sendMail2() throws MessagingException {
        // 一个复杂的邮件
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        // 组装
        helper.setSubject("Springboot 测试复杂邮件");
        helper.setText("<p style='color:blue'>Springboot实现发送复杂邮件</p>", true);

        // 附件
        helper.addAttachment("1.png", new File("D:\\Users\\ybsde\\IdeaProjects\\SpringBoot\\spring-sync\\src\\main\\resources\\1.png"));
        helper.addAttachment("1.png", new File("D:\\Users\\ybsde\\IdeaProjects\\SpringBoot\\spring-sync\\src\\main\\resources\\1.png"));

        // 地址
        helper.setTo("*******@163.com");
        helper.setFrom("*******@163.com");

        mailSender.send(mimeMessage);
    }

    /**
     *
     * @param html
     * @param subject
     * @param text
     * @throws MessagingException
     * @Author Paulson
     */
    public void sendMail(Boolean html, String subject, String text) throws MessagingException {
        // 一个复杂的邮件
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        // 组装
        helper.setSubject(subject);
        helper.setText(text, true);

        // 附件
        helper.addAttachment("1.png", new File("D:\\Users\\ybsde\\IdeaProjects\\SpringBoot\\spring-sync\\src\\main\\resources\\1.png"));
        helper.addAttachment("1.png", new File("D:\\Users\\ybsde\\IdeaProjects\\SpringBoot\\spring-sync\\src\\main\\resources\\1.png"));

        // 地址
        helper.setTo("*******@163.com");
        helper.setFrom("*******@163.com");

        mailSender.send(mimeMessage);
    }
}
