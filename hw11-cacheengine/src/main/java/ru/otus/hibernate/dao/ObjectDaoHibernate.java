package ru.otus.hibernate.dao;


import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.core.dao.ObjectDao;
import ru.otus.core.dao.UserDaoException;
import ru.otus.core.sessionmanager.SessionManager;
import ru.otus.hibernate.sessionmanager.DatabaseSessionHibernate;
import ru.otus.hibernate.sessionmanager.SessionManagerHibernate;

import javax.persistence.Id;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

public class ObjectDaoHibernate<T> implements ObjectDao<T> {
    private static Logger logger = LoggerFactory.getLogger(ObjectDaoHibernate.class);

    private final SessionManagerHibernate sessionManager;

    public ObjectDaoHibernate(SessionManagerHibernate sessionManager) {
        this.sessionManager = sessionManager;
    }


    @Override
    public Optional<T> findById(long id, Class<T> tClass) {
        DatabaseSessionHibernate currentSession = sessionManager.getCurrentSession();
        try {
            return Optional.ofNullable(currentSession.getHibernateSession().find(tClass, id));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return Optional.empty();
    }


    @Override
    public long saveObject(T t) {
        DatabaseSessionHibernate currentSession = sessionManager.getCurrentSession();
        try {
            Class<?> tClass = t.getClass();
            long id = List.of(tClass.getDeclaredFields()).stream()
                    .filter(field -> field.isAnnotationPresent(Id.class))
                    .peek(field -> field.setAccessible(true))
                    .findFirst().orElseThrow().getLong(t);
            Session hibernateSession = currentSession.getHibernateSession();
            hibernateSession.merge(t);
            System.out.println(id);
            return id;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new UserDaoException(e);
        }
    }

    @Override
    public SessionManager getSessionManager() {
        return sessionManager;
    }
}
