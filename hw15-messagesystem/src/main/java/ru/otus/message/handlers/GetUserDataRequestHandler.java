package ru.otus.message.handlers;

import ru.otus.core.service.db.DbServiceUser;
import ru.otus.domain.User;
import ru.otus.message.app.Serializers;
import ru.otus.message.messagesystem.Message;
import ru.otus.message.messagesystem.MessageType;
import ru.otus.message.messagesystem.RequestHandler;

import java.util.List;
import java.util.Optional;


public class GetUserDataRequestHandler implements RequestHandler {

    private final DbServiceUser dbService;

    public GetUserDataRequestHandler(DbServiceUser dbService) {
        this.dbService = dbService;
    }

    @Override
    public Optional<Message> handle(Message msg) {
        if ("all".equals(Serializers.deserialize(msg.getPayload(), String.class))) {
            List<User> users = dbService.findAll();
            return Optional.of(new Message(msg.getTo(), msg.getFrom(), msg.getId(), MessageType.USER_DATA.getValue(), Serializers.serialize(users)));
        } else {
            long id = -1;
            id = Serializers.deserialize(msg.getPayload(), Long.class);
            if (id != -1) {
                User data = dbService.findById(id).orElseThrow();
                return Optional.of(new Message(msg.getTo(), msg.getFrom(), msg.getId(), MessageType.USER_DATA.getValue(), Serializers.serialize(data)));
            } else {
                String login = Serializers.deserialize(msg.getPayload(), String.class);
                User data = dbService.findByLogin(login).orElseThrow();
                return Optional.of(new Message(msg.getTo(), msg.getFrom(), msg.getId(), MessageType.USER_DATA.getValue(), Serializers.serialize(data)));
            }
        }
    }

}
