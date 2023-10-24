package com.prashant.service;

import com.prashant.model.BookingEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class EmailServiceImpl implements IEmailService{
    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String senderEmail;

    private final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Override
    public void sendEmail(BookingEmail email) {
        try
        {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(senderEmail);
            mailMessage.setTo(email.getRecipientEmail());
            mailMessage.setSubject(email.getSubject());
            mailMessage.setText(email.getBody());
            mailMessage.setSentDate(new Date());

            javaMailSender.send(mailMessage);

            logger.info("Email sent successfully");
        }
        catch (Exception e)
        {
            logger.info("Something went wrong while sending the email");
        }
    }
}
