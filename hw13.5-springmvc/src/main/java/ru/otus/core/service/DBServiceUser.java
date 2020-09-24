package ru.otus.core.service;

import ru.otus.core.model.User;

import java.util.List;
import java.util.Optional;

public interface DBServiceUser {

  long save(User user);

  Optional<User> findById(long id);

  Optional<User> findByName(String login);

  List<User> findAll();

}
