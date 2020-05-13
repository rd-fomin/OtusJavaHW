  package ru.otus.db.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.app.common.Serializers;
import ru.otus.db.DBService;
import ru.otus.messagesystem.Message;
import ru.otus.messagesystem.MessageType;
import ru.otus.messagesystem.RequestHandler;

import java.util.Optional;


public class GetUserDataRequestHandler implements RequestHandler {
    public static final Logger logger = LoggerFactory.getLogger(GetUserDataRequestHandler.class);

    private final DBService dbService;

    public GetUserDataRequestHandler(DBService dbService) {
        this.dbService = dbService;
    }

    @Override
    public Optional<Message> handle(Message msg) {
        Long id = Serializers.deserialize(msg.getPayload(), Long.class);
        logger.info("Request message from {} to {} with id = {}", msg.getFrom(), msg.getTo(), id);
        return Optional.of(new Message(msg.getTo(), msg.getFrom(), msg.getId(), MessageType.USER_DATA.getValue(), Serializers.serialize(id)));
    }

}
