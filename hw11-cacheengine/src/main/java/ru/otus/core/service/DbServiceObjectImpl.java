package ru.otus.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.core.dao.ObjectDao;
import ru.otus.core.sessionmanager.SessionManager;

import java.util.Optional;
import java.util.WeakHashMap;

public class DbServiceObjectImpl<T> implements DBServiceObject<T> {
    private static Logger logger = LoggerFactory.getLogger(DbServiceObjectImpl.class);

    private final WeakHashMap<Long, Optional<T>> cache = new WeakHashMap<>();

    private final ObjectDao<T> objectDao;

    public DbServiceObjectImpl(ObjectDao<T> objectDao) {
        this.objectDao = objectDao;
    }

    @Override
    public long saveUser(T t) {
        try (SessionManager sessionManager = objectDao.getSessionManager()) {
            sessionManager.beginSession();
            try {
                long userId = objectDao.saveObject(t);
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
        if (cache.containsKey(id))
            return cache.get(id);
        try (SessionManager sessionManager = objectDao.getSessionManager()) {
            sessionManager.beginSession();
            try {
                Optional<T> objectOptional = objectDao.findById(id, tClass);
                logger.info("object: {}", objectOptional.orElse(null));
                cache.put(id, objectOptional);
                return objectOptional;
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                sessionManager.rollbackSession();
            }
            return Optional.empty();
        }
    }

}
