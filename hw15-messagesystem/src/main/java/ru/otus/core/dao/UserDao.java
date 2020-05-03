package ru.otus.core.dao;

import ru.otus.core.sessionmanager.SessionManager;
import ru.otus.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    List<User> findAll();

    Optional<User> findBy(long id);

    Optional<User> findBy(String login);

    default long save(User user) {
        return 0;
    }

    SessionManager getSessionManager();
}