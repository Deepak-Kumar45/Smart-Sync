package com.smartsync.services;

import java.io.*;

public interface EmailService {

    public void sendMail(String to,String subject,String body);

    public void sendMail(String to[],String subject,String body);
    
    public void sendMailWithHtml(String to,String subject,String html);

    public void sendMailWithFile(String to,String subject,File file);
}
