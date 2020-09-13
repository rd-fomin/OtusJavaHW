package ru.otus.core.dao;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.otus.core.model.User;
import ru.otus.core.sessionmanager.SessionManager;
import ru.otus.core.sessionmanager.SessionManagerHibernate;

import java.util.List;
import java.util.Optional;

@Service
public class InDbMemoryUserDao implements UserDao {
    private static final Logger logger = LoggerFactory.getLogger(InDbMemoryUserDao.class);

    private final SessionManagerHibernate sessionManager;

    public InDbMemoryUserDao(SessionManagerHibernate sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public List<User> findAll() {
        var currentSession = sessionManager.getCurrentSession();
        try {
            return currentSession.getHibernateSession()
                    .createQuery("from User", User.class)
                    .list();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return List.of();
    }

    @Override
    public Optional<User> findById(long id) {
        var currentSession = sessionManager.getCurrentSession();
        try {
            return Optional.ofNullable(currentSession.getHibernateSession().find(User.class, id));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findByName(String name) {
        var currentSession = sessionManager.getCurrentSession();
        try {
            User result = currentSession.getHibernateSession()
                    .createQuery("from User where name=:name", User.class)
                    .setParameter("name", name)
                    .list().get(0);
            return Optional.ofNullable(result);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return Optional.empty();
    }

    @Override
    public long save(Optional<User> user) throws UserDaoException {
        var currentSession = sessionManager.getCurrentSession();
        try {
            long id = user.orElseThrow().getId();
            Session hibernateSession = currentSession.getHibernateSession();
            hibernateSession.merge(user.orElseThrow());
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
