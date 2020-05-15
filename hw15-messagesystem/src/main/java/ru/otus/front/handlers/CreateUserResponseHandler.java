package ru.otus.front.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.app.common.Serializers;
import ru.otus.front.FrontendService;
import ru.otus.messagesystem.Message;
import ru.otus.messagesystem.RequestHandler;

import java.util.Optional;
import java.util.UUID;

public class CreateUserResponseHandler implements RequestHandler {
    private static final Logger logger = LoggerFactory.getLogger(CreateUserResponseHandler.class);

    private final FrontendService frontendService;

    public CreateUserResponseHandler(FrontendService frontendService) {
        this.frontendService = frontendService;
    }

    @Override
    public Optional<Message> handle(Message msg) {
        try {
            long userData = Serializers.deserialize(msg.getPayload(), Long.class);
            UUID sourceMessageId = msg.getSourceMessageId().orElseThrow(() -> new RuntimeException("Not found sourceMsg for message: " + msg.getId()));
            frontendService.takeConsumer(sourceMessageId, Long.class).ifPresent(consumer -> consumer.accept(userData));
            logger.info("Response message from {} to {}: {}", msg.getFrom(), msg.getTo(), msg);
        } catch (Exception ex) {
            logger.error("Can't create message from {} to {}: {}", msg.getFrom(), msg.getTo(), msg, ex);
        }
        return Optional.empty();
    }
}
