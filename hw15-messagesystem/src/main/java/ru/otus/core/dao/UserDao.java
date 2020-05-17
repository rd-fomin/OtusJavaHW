package ru.otus.core.dao;

import ru.otus.core.sessionmanager.SessionManager;
import ru.otus.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    List<User> findAll();

    Optional<User> findById(long id);

    Optional<User> findByLogin(String login);

    long save(User user);

    SessionManager getSessionManager();

}