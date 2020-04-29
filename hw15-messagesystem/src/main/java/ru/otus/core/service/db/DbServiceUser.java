package ru.otus.core.service.db;

import ru.otus.domain.User;

import java.util.List;
import java.util.Optional;

public interface DbServiceUser {

  long save(User user);

  Optional<User> findById(long id);

  Optional<User> findByLogin(String login);

  List<User> findAll();

}
