package ru.otus.core.dao;

import ru.otus.core.sessionmanager.SessionManager;

import java.util.Optional;

public interface UserDao<T> {

  Optional<T> findById(long id);

  long saveObject(T t);

  SessionManager getSessionManager();
}
