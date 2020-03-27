package com.remifo.core.dao;

import com.remifo.core.sessionmanager.SessionManager;
import com.remifo.test.User;

import java.util.Optional;

public interface UserDao {
  Optional<User> findById(long id);

  long saveUser(User user);

  SessionManager getSessionManager();
}
