package ru.otus.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.otus.cache.HwListener;
import ru.otus.cache.MyCache;
import ru.otus.core.dao.UserDao;
import ru.otus.core.model.User;
import ru.otus.core.sessionmanager.SessionManager;

import java.util.List;
import java.util.Optional;

@Service
public class DbServiceUserImpl implements DBServiceUser {
    private static Logger logger = LoggerFactory.getLogger(DbServiceUserImpl.class);

    private final MyCache<Long, User> cache = new MyCache<>();

    private final UserDao userDao;

    public DbServiceUserImpl(UserDao userDao) {
        this.userDao = userDao;
        cache.addListener(new HwListener<Long, User>() {
            @Override
            public void notify(Long key, User user, String action) {
                logger.info("key: {}, user:{}, action:{}", key, user, action);
            }
        });
    }

    @Override
    public long save(User user) {
        try (SessionManager sessionManager = userDao.getSessionManager()) {
            sessionManager.beginSession();
            try {
                long userId = userDao.save(Optional.ofNullable(user));
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
    public Optional<User> findByName(String login) {
        try (SessionManager sessionManager = userDao.getSessionManager()) {
            sessionManager.beginSession();
            try {
                Optional<User> user = userDao.findByName(login);
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
