package ru.otus.front.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.app.common.Serializers;
import ru.otus.domain.User;
import ru.otus.front.FrontendService;
import ru.otus.messagesystem.Message;
import ru.otus.messagesystem.RequestHandler;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class GetAllResponseHandler implements RequestHandler {
    private static final Logger logger = LoggerFactory.getLogger(GetAllResponseHandler.class);

    private final FrontendService frontendService;

    public GetAllResponseHandler(FrontendService frontendService) {
        this.frontendService = frontendService;
    }

    @Override
    public Optional<Message> handle(Message msg) {
        try {
            List<User> userData = Serializers.deserialize(msg.getPayload(), List.class);
            UUID sourceMessageId = msg.getSourceMessageId().orElseThrow(() -> new RuntimeException("Not found sourceMsg for message: " + msg.getId()));
            frontendService.takeConsumer(sourceMessageId, List.class).ifPresent(consumer -> consumer.accept(userData));
            logger.info("Response message from {} to {}: {}", msg.getFrom(), msg.getTo(), msg);
        } catch (Exception ex) {
            logger.error("Can't create message from {} to {}: {}", msg.getFrom(), msg.getTo(), msg, ex);
        }
        return Optional.empty();
    }
}
