package ru.otus.front;


import ru.otus.domain.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

public interface FrontendService {

    void createUser(User user, Consumer<Long> dataConsumer);

    void getAll(String getAll, Consumer<List<User>> dataConsumer);

    <T> Optional<Consumer<T>> takeConsumer(UUID sourceMessageId, Class<T> tClass);

}

