package ru.otus.core.service;

import ru.otus.core.model.User;

import java.util.List;
import java.util.Optional;

public interface DBServiceUser {

  long save(User user);

  Optional<User> getById(long id);

  Optional<User> getByLogin(String login);

  List<User> getAll();

}
