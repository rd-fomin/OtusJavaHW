package ru.otus.core.service;

import java.util.Optional;

public interface DBServiceUser<T> {

  long saveUser(T t);

  Optional<T> getObject(long id);

}
