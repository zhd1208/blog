package com.mszlu.blog.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.mail.MailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MailClient {

    private static final Logger logger = LoggerFactory.getLogger(MailClient.class);

    @Autowired
    private JavaMailSender mailSender;

    /*@Value("${spring.mail.username}")
    private String from;*/

    public void sendMail(String to, String subject, String content) {
        /*String filePath = "application.properties";
        Properties properties=new Properties();
        properties.setProperty("spring.mail.host","smtp.sina.com");
        properties.setProperty("spring.mail.username","blog_xmxz@sina.com");
        properties.setProperty("spring.mail.password","a69fabdf306cdfe3");
        try {
            properties.store(new FileOutputStream(filePath), "xl");
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        String from="blog_xmxz@sina.com";
        /*JavaMailSenderImpl mailSenderImpl = new JavaMailSenderImpl();
        Properties prop = new Properties();

        mailSenderImpl.setJavaMailProperties(prop);


        mailSenderImpl.setUsername("blog_xmxz@sina.com");
        mailSenderImpl.setHost("smtp.sina.com");
        mailSenderImpl.setPassword("a69fabdf306cdfe3");
        mailSenderImpl.setProtocol("smtps");
        mailSenderImpl.setPort(465);*/

        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost("smtp.sina.com");
        sender.setPort(465);
        sender.setUsername("blog_xmxz@sina.com");
        sender.setPassword("a69fabdf306cdfe3");
        sender.setProtocol("smtps");
        sender.setDefaultEncoding("Utf-8");
        Properties p = new Properties();
        p.setProperty("mail.smtp.auth","true");
        p.setProperty("mail.smtp.ssl.enable","true");
        sender.setJavaMailProperties(p);
        try {
            MimeMessage message = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            sender.send(helper.getMimeMessage());
        } catch (MessagingException e) {
            logger.error("发送邮件失败:" + e.getMessage());
        }
    }

    public void sendMailByqq(String to, String subject, String content) throws IOException {
        /*JavaMailSenderImpl mailSenderImpl = new JavaMailSenderImpl();
        Properties prop = new Properties();

        mailSenderImpl.setJavaMailProperties(prop);
        mailSenderImpl.setUsername("702475703@qq.com");
        mailSenderImpl.setHost("smtp.qq.com");
        mailSenderImpl.setProtocol("smtps");
        mailSenderImpl.setPassword("mvapagtdyfepbdad");
        mailSenderImpl.setPort(465);
*/
        String from1="702475703@qq.com";

        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost("smtp.qq.com");
        sender.setPort(465);
        sender.setUsername("702475703@qq.com");
        sender.setPassword("mvapagtdyfepbdad");
        sender.setProtocol("smtps");
        sender.setDefaultEncoding("Utf-8");
        Properties p = new Properties();
        p.setProperty("mail.smtp.auth","true");
        p.setProperty("mail.smtp.ssl.enable","true");
        sender.setJavaMailProperties(p);
        try {

            MimeMessage message = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setFrom(from1);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            sender.send(helper.getMimeMessage());
        } catch (MessagingException e) {
            logger.error("发送邮件失败:" + e.getMessage());
        }
    }

}
