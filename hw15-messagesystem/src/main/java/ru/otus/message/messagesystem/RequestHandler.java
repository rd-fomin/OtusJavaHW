package ru.otus.message.messagesystem;

import java.util.Optional;

@FunctionalInterface
public interface RequestHandler {
    Optional<Message> handle(Message msg);
}
