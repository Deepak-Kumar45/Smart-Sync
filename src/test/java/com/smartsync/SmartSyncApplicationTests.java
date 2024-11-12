package com.smartsync;

import java.io.File;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.smartsync.services.EmailService;

@SpringBootTest
class SmartSyncApplicationTests {

	@Autowired
	EmailService emailService;

	@Test
    public void testSendMail() {
		emailService.sendMail("kumardeep4591@gmail.com", "SmartSync Verification", "This is a testing message to test mail");
    }
	
	@Test
    public void testSendMails() {
		emailService.sendMail(new String[]{"kumardeep4591@gmail.com","deepakverma4551116@gmail.com","newavenger970@gmail.com"}, "SmartSync Verification", "This is a testing message to test mail");
    }

	@Test
	public void testMailWithFile(){
		File file=new File("C:/Deepak/spring projects/smart_sync/Smart-Sync/src/main/resources/static/images/smartsynclogo.png"); 
		if(file.exists()){
			System.out.println("file's length: "+file.length());
		}
		emailService.sendMailWithFile("newavenger970@gmail.com", "Kuch nahi", file);
	}

	@Test
    public void testSendHTMLMail() {
		String html="<!DOCTYPE html>\r\n" + //
						"<html lang=\"en\">\r\n" + //
						"<head>\r\n" + //
						"    <meta charset=\"UTF-8\">\r\n" + //
						"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + //
						"    <title>Email Verification</title>\r\n" + //
						"    <style>\r\n" + //
						"        body {\r\n" + //
						"            font-family: Arial, sans-serif;\r\n" + //
						"            margin: 0;\r\n" + //
						"            padding: 0;\r\n" + //
						"            background-color: #f4f4f4;\r\n" + //
						"        }\r\n" + //
						"        .container {\r\n" + //
						"            max-width: 600px;\r\n" + //
						"            margin: 20px auto;\r\n" + //
						"            background: #ffffff;\r\n" + //
						"            padding: 20px;\r\n" + //
						"            border-radius: 8px;\r\n" + //
						"            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);\r\n" + //
						"        }\r\n" + //
						"        h1 {\r\n" + //
						"            color: #333;\r\n" + //
						"        }\r\n" + //
						"        p {\r\n" + //
						"            line-height: 1.5;\r\n" + //
						"            color: #555;\r\n" + //
						"        }\r\n" + //
						"        .button {\r\n" + //
						"            display: inline-block;\r\n" + //
						"            padding: 10px 20px;\r\n" + //
						"            margin: 20px 0;\r\n" + //
						"            color: #ffffff;\r\n" + //
						"            background-color: #007BFF;\r\n" + //
						"            text-decoration: none;\r\n" + //
						"            border-radius: 5px;\r\n" + //
						"        }\r\n" + //
						"        .footer {\r\n" + //
						"            margin-top: 20px;\r\n" + //
						"            font-size: 12px;\r\n" + //
						"            color: #aaa;\r\n" + //
						"        }\r\n" + //
						"    </style>\r\n" + //
						"</head>\r\n" + //
						"<body>\r\n" + //
						"    <div class=\"container\">\r\n" + //
						"        <h1>Email Verification</h1>\r\n" + //
						"        <p>Hi there!</p>\r\n" + //
						"        <p>Thank you for signing up. To complete your registration, please verify your email address by clicking the button below:</p>\r\n" + //
						"        <a href=\"{{verification_link}}\" class=\"button\">Verify Email</a>\r\n" + //
						"        <p>If you did not create an account, please ignore this email.</p>\r\n" + //
						"        <p>Thank you!</p>\r\n" + //
						"        <div class=\"footer\">\r\n" + //
						"            <p>&copy; {{year}} Your Company. All rights reserved.</p>\r\n" + //
						"        </div>\r\n" + //
						"    </div>\r\n" + //
						"</body>\r\n" + //
						"</html>\r\n" + //
						"";
		emailService.sendMailWithHtml("newavenger970@gmail.com", "SmartSync Verification", html);
    }


	@Test
	void contextLoads() {
	}

}
