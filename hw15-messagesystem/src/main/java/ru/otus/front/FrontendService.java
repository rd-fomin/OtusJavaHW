package ru.otus.front;


import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

public interface FrontendService {

    void getUserData(Long userId, Consumer<Long> dataConsumer);

    <T> Optional<Consumer<T>> takeConsumer(UUID sourceMessageId, Class<T> tClass);

}

