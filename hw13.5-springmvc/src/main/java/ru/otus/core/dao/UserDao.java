package ru.otus.core.dao;

import ru.otus.core.model.User;
import ru.otus.core.sessionmanager.SessionManager;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    List<User> findAll();

    Optional<User> findById(long id);

    Optional<User> findByName(String login);

    default long save(Optional<User> user) {
        return 0;
    }

    SessionManager getSessionManager();
}