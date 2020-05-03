package ru.otus.message.front;


import ru.otus.domain.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

public interface FrontendService {

    void getUserData(long userId, Consumer<User> dataConsumer);

    void getUserData(String userLogin, Consumer<String> dataConsumer);

    void getAllData(Consumer<List<User>> dataConsumer);

    void save(User user, Consumer<User> dataConsumer);

    <T> Optional<Consumer<T>> takeConsumer(UUID sourceMessageId, Class<T> tClass);

}

