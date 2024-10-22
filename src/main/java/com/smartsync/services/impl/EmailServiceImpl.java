package com.smartsync.services.impl;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.smartsync.services.EmailService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

    @Value("${spring.mail.username}")
    private String FROM;

    private JavaMailSender mailSender;

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendMail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        message.setFrom(FROM);
        mailSender.send(message);
        System.out.println("Email has been sent successfully...");
    }

    @Override
    public void sendMail(String[] to, String subject, String body) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setFrom(FROM);
        mailMessage.setSubject(subject);
        mailMessage.setText(body);
        mailSender.send(mailMessage);
        System.out.println("Mails are send successfully..");
    }

    @Override
    public void sendMailWithHtml(String to, String subject, String html) {
        MimeMessage mailMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mailMessage, true, "UTF-8");
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setFrom(FROM);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(html, true);
            mailSender.send(mailMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        System.out.println("Mail with html are send successfully..");
    }

    @Override
    public void sendMailWithFile(String to, String subject, File file) {
        MimeMessage mime = mailSender.createMimeMessage();
        try {
        MimeMessageHelper mimeHelper = new MimeMessageHelper(mime, true);
            mimeHelper.setTo(to);
            mimeHelper.setFrom(FROM);
            mimeHelper.setSubject(subject);
            FileSystemResource fileSystemResource=new FileSystemResource(file);
            mimeHelper.addAttachment(fileSystemResource.getFilename(), fileSystemResource);
            mailSender.send(mime);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        System.out.println("Mail with file send successfully..");
    }

}
