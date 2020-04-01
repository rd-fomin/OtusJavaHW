package ru.otus.core.service;

import java.util.Optional;

public interface DBServiceUser<T> {

  long saveObject(T t);

  Optional<T> getObject(long id, Class<T> tClass);

}
