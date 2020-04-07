package ru.otus.core.dao;

import ru.otus.core.sessionmanager.SessionManager;

import java.util.Optional;

public interface ObjectDao<T> {

  Optional<T> findById(long id, Class<T> tClass);

  long saveObject(T t);

  SessionManager getSessionManager();

}
