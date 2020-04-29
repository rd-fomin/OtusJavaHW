package ru.otus.message.handlers;

import ru.otus.core.service.db.DbServiceUser;
import ru.otus.domain.User;
import ru.otus.message.app.Serializers;
import ru.otus.message.messagesystem.Message;
import ru.otus.message.messagesystem.MessageType;
import ru.otus.message.messagesystem.RequestHandler;

import java.util.Optional;


public class GetUserDataRequestHandler implements RequestHandler {

    private final DbServiceUser dbService;

    public GetUserDataRequestHandler(DbServiceUser dbService) {
        this.dbService = dbService;
    }

    @Override
    public Optional<Message> handle(Message msg) {
        long id = Serializers.deserialize(msg.getPayload(), Long.class);
        User data = dbService.findById(id).orElseThrow();
        return Optional.of(new Message(msg.getTo(), msg.getFrom(), msg.getId(), MessageType.USER_DATA.getValue(), Serializers.serialize(data)));
    }

}
