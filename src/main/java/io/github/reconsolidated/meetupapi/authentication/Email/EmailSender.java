package io.github.reconsolidated.meetupapi.authentication.Email;

public interface EmailSender {
    void send(String to, String email);
}
