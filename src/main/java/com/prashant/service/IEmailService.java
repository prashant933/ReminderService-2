package com.prashant.service;

import com.prashant.model.BookingEmail;

public interface IEmailService {
    void sendEmail(BookingEmail email);
}
