package com.prashant.consumer;

import com.prashant.model.BookingEmail;
import com.prashant.service.IEmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingEmailConsumer {
    private final Logger logger = LoggerFactory.getLogger(BookingEmailConsumer.class);
    @Autowired
    private IEmailService emailService;

    @RabbitListener(queues = {"${rabbitmq.reminder.service.queue.name}"})
    public void consume(BookingEmail email) {
        logger.info("Received message from queue: "+email.toString());
        emailService.sendEmail(email);
    }

}
