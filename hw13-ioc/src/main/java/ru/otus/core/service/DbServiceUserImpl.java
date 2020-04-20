package ru.otus.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.core.cache.Cache;
import ru.otus.core.dao.UserDao;
import ru.otus.core.sessionmanager.SessionManager;
import ru.otus.domain.User;

import java.util.List;
import java.util.Optional;

public class DbServiceUserImpl implements DbServiceUser {
    private static final Logger logger = LoggerFactory.getLogger(DbServiceUserImpl.class);

    private final Cache<Long, User> cache;
    private final UserDao userDao;

    public DbServiceUserImpl(UserDao userDao, Cache<Long, User> cache) {
        this.userDao = userDao;
        this.cache = cache;
    }

    @Override
    public long save(User user) {
        try (SessionManager sessionManager = userDao.getSessionManager()) {
            sessionManager.beginSession();
            try {
                long userId = userDao.save(user);
                sessionManager.commitSession();
                if (cache.get(userId) == null) {
                    cache.put(userId, user);
                }
                logger.info("saved user with id: {}", userId);
                return userId;
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                sessionManager.rollbackSession();
                throw new DbServiceException(e);
            }
        }
    }

    @Override
    public Optional<User> findById(long id) {
        if (cache.get(id) != null) {
            return Optional.ofNullable(cache.get(id));
        }
        try (SessionManager sessionManager = userDao.getSessionManager()) {
            sessionManager.beginSession();
            try {
                Optional<User> user = userDao.findById(id);
                sessionManager.commitSession();
                logger.info("created user: {}", user.orElseThrow());
                return user;
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                sessionManager.rollbackSession();
                throw new DbServiceException(e);
            }
        }
    }

    @Override
    public Optional<User> findByLogin(String login) {
        try (SessionManager sessionManager = userDao.getSessionManager()) {
            sessionManager.beginSession();
            try {
                Optional<User> user = userDao.findByLogin(login);
                sessionManager.commitSession();
                logger.info("created user: {}", user.orElseThrow());
                return user;
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                sessionManager.rollbackSession();
                throw new DbServiceException(e);
            }
        }
    }

    @Override
    public List<User> findAll() {
        try (SessionManager sessionManager = userDao.getSessionManager()) {
            sessionManager.beginSession();
            try {
                List<User> users = userDao.findAll();
                sessionManager.commitSession();
                logger.info("created users: {}", users);
                return users;
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                sessionManager.rollbackSession();
                throw new DbServiceException(e);
            }
        }
    }
}
