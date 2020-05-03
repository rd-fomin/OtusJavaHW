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
        switch (msg.getType()) {
            case "GetAll" : {
                List<User> users = dbService.findAll();
                return Optional.of(
                        new Message(
                                msg.getTo(),
                                msg.getFrom(),
                                msg.getId(),
                                MessageType.GET_ALL.getValue(),
                                Serializers.serialize(users)
                        )
                );
            }
            case "GetById" : {
                long id = Long.parseLong(Serializers.deserialize(msg.getPayload(), String.class));
                User user = dbService.findBy(id).orElseThrow();
                return Optional.of(
                        new Message(
                                msg.getTo(),
                                msg.getFrom(),
                                msg.getId(),
                                MessageType.GET_BY_ID.getValue(),
                                Serializers.serialize(user)
                        )
                );
            }
            case "GetByLogin" : {
                String login = Serializers.deserialize(msg.getPayload(), String.class);
                User user = dbService.findBy(login).orElseThrow();
                return Optional.of(
                        new Message(
                                msg.getTo(),
                                msg.getFrom(),
                                msg.getId(),
                                MessageType.GET_BY_LOGIN.getValue(),
                                Serializers.serialize(user)
                        )
                );
            }
            case "SaveUser" : {
                User user = Serializers.deserialize(msg.getPayload(), User.class);
                dbService.save(user);
                return Optional.of(
                        new Message(
                                msg.getTo(),
                                msg.getFrom(),
                                msg.getId(),
                                MessageType.SAVE_USER.getValue(),
                                Serializers.serialize(user)
                        )
                );
            }
            default : {
                return Optional.empty();
            }
        }
    }

}
