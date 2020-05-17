package ru.otus.front;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.domain.User;
import ru.otus.messagesystem.Message;
import ru.otus.messagesystem.MessageType;
import ru.otus.messagesystem.MsClient;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;


public class FrontendServiceImpl implements FrontendService {
    private static final Logger logger = LoggerFactory.getLogger(FrontendServiceImpl.class);

    private final Map<UUID, Consumer<?>> consumerMap = new ConcurrentHashMap<>();
    private final MsClient msClient;
    private final String databaseServiceClientName;

    public FrontendServiceImpl(MsClient msClient, String databaseServiceClientName) {
        this.msClient = msClient;
        this.databaseServiceClientName = databaseServiceClientName;
    }

    @Override
    public void createUser(User user, Consumer<Long> dataConsumer) {
        Message outMsg = msClient.produceMessage(databaseServiceClientName, user, MessageType.CREATE_USER);
        consumerMap.put(outMsg.getId(), dataConsumer);
        msClient.sendMessage(outMsg);
    }

    @Override
    public void getAll(String getAll, Consumer<List<User>> dataConsumer) {
        Message outMsg = msClient.produceMessage(databaseServiceClientName, getAll, MessageType.GET_ALL);
        consumerMap.put(outMsg.getId(), dataConsumer);
        msClient.sendMessage(outMsg);
    }

    @Override
    public <T> Optional<Consumer<T>> takeConsumer(UUID sourceMessageId, Class<T> tClass) {
        Consumer<T> consumer = (Consumer<T>) consumerMap.remove(sourceMessageId);
        if (consumer == null) {
            logger.warn("consumer not found for: {}", sourceMessageId);
            return Optional.empty();
        }
        return Optional.of(consumer);
    }

}
