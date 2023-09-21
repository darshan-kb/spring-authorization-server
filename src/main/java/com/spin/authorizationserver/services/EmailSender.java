package com.spin.authorizationserver.services;

public interface EmailSender {
    void send(String to, String email, String subject);
}
