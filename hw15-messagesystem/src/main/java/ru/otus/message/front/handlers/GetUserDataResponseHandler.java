package ru.otus.message.front.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.domain.User;
import ru.otus.message.app.Serializers;
import ru.otus.message.front.FrontendService;
import ru.otus.message.messagesystem.Message;
import ru.otus.message.messagesystem.MessageType;
import ru.otus.message.messagesystem.RequestHandler;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class GetUserDataResponseHandler implements RequestHandler {
    private static final Logger logger = LoggerFactory.getLogger(GetUserDataResponseHandler.class);

    private final FrontendService frontendService;

    public GetUserDataResponseHandler(FrontendService frontendService) {
        this.frontendService = frontendService;
    }

    @Override
    public Optional<Message> handle(Message msg) {
        logger.info("new message:{}", msg);
        try {
            UUID sourceMessageId = msg.getSourceMessageId().orElseThrow(() -> new RuntimeException("Not found sourceMsg for message:" + msg.getId()));
            switch (msg.getType()) {
                case "GetAll" : {
                    var users = Serializers.deserialize(msg.getPayload(), List.class);
                    frontendService.takeConsumer(sourceMessageId, List.class).ifPresent(consumer -> consumer.accept(users));
                    break;
                }
                case "GetById":
                case "GetByLogin": {
                    User user = Serializers.deserialize(msg.getPayload(), User.class);
                    frontendService.takeConsumer(sourceMessageId, User.class).ifPresent(consumer -> consumer.accept(user));
                    break;
                }
                default : {
                    break;
                }
            }
        } catch (Exception ex) {
            logger.error("msg:" + msg, ex);
        }
        return Optional.empty();
    }

}
