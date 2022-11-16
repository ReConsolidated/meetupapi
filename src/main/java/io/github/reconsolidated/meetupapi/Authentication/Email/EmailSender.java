package io.github.reconsolidated.meetupapi.Authentication.Email;

public interface EmailSender {
    void send(String to, String email);
}
