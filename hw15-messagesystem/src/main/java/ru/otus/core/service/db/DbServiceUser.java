package ru.otus.core.service.db;

import ru.otus.domain.User;

import java.util.List;
import java.util.Optional;

public interface DbServiceUser {

  long save(User user);

  Optional<User> findBy(long id);

  Optional<User> findBy(String login);

  List<User> findAll();

}
