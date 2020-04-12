package ru.otus.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.cache.HwListener;
import ru.otus.cache.MyCache;
import ru.otus.core.dao.ObjectDao;
import ru.otus.core.sessionmanager.SessionManager;

import java.util.Optional;

public class DbServiceObjectImpl<T> implements DBServiceObject<T> {
    private static Logger logger = LoggerFactory.getLogger(DbServiceObjectImpl.class);

    private final MyCache<Long, T> cache = new MyCache<>();

    private final ObjectDao<T> objectDao;

    public DbServiceObjectImpl(ObjectDao<T> objectDao) {
        this.objectDao = objectDao;
        cache.addListener(new HwListener<Long, T>() {
            @Override
            public void notify(Long key, T value, String action) {
                logger.info("key: {}, value:{}, action:{}", key, value, action);
            }
        });
    }

    @Override
    public long saveObject(T t) {
        try (SessionManager sessionManager = objectDao.getSessionManager()) {
            sessionManager.beginSession();
            try {
                long userId = objectDao.saveObject(t);
                if (cache.get(userId) == null) {
                    cache.put(userId, t);
                }
                sessionManager.commitSession();
                logger.info("created object: {}", userId);
                return userId;
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                sessionManager.rollbackSession();
                throw new DbServiceException(e);
            }
        }
    }


    @Override
    public Optional<T> getObject(long id, Class<T> tClass) {
        if (cache.get(id) != null)
            return Optional.of(cache.get(id));
        try (SessionManager sessionManager = objectDao.getSessionManager()) {
            sessionManager.beginSession();
            try {
                Optional<T> objectOptional = objectDao.findById(id, tClass);
                logger.info("object: {}", objectOptional.orElse(null));
                cache.put(id, objectOptional.orElseThrow());
                return objectOptional;
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                sessionManager.rollbackSession();
            }
            return Optional.empty();
        }
    }

}
