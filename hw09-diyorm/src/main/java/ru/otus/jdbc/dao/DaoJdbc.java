package ru.otus.jdbc.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.core.Id;
import ru.otus.core.dao.UserDao;
import ru.otus.core.dao.UserDaoException;
import ru.otus.core.sessionmanager.SessionManager;
import ru.otus.jdbc.DbExecutor;
import ru.otus.jdbc.sessionmanager.SessionManagerJdbc;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class DaoJdbc<T> implements UserDao<T> {
  private static Logger logger = LoggerFactory.getLogger(DaoJdbc.class);

  private final SessionManagerJdbc sessionManager;
  private final DbExecutor<T> dbExecutor;

  public DaoJdbc(SessionManagerJdbc sessionManager, DbExecutor<T> dbExecutor) {
    this.sessionManager = sessionManager;
    this.dbExecutor = dbExecutor;
  }


  @Override
  public Optional<T> findById(long id, Class<T> tClass) {
    try {
      List<Field> fields = new ArrayList<>();
      Field idField = null;
      for (Field field : tClass.getDeclaredFields()) {
        field.setAccessible(true);
        if (!field.isAnnotationPresent(Id.class)) {
          fields.add(field);
        } else {
          idField = field;
        }
      }
      T t = tClass.newInstance();
      idField.set(t, id);
      return dbExecutor.selectRecord(getConnection(),
              "select * from " + tClass.getSimpleName().toLowerCase() + " where " + idField.getName() + "  = ?",
              id,
              resultSet -> {
        try {
          if (resultSet.next()) {
            fields.get(0).set(t, resultSet.getObject(fields.get(0).getName()));
            fields.get(1).set(t, resultSet.getObject(fields.get(1).getName()));
            return t;
          }
        } catch (SQLException e) {
          logger.error(e.getMessage(), e);
        } catch (IllegalAccessException e) {
          e.printStackTrace();
        }
        return null;
      });
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
    }
    return Optional.empty();
  }

  @Override
  public long saveObject(T t) {
    try {
      Class<T> clazz = (Class<T>) t.getClass();
      List<Field> fields = new ArrayList<>();
      for (Field field : clazz.getDeclaredFields()) {
        field.setAccessible(true);
        if (!field.isAnnotationPresent(Id.class)) {
          fields.add(field);
        }
      }
      String result = new StringBuilder("insert into ")
              .append(clazz.getSimpleName().toLowerCase())
              .append('(')
              .append(fields.get(0).getName().toLowerCase()).append(',')
              .append(fields.get(1).getName().toLowerCase())
              .append(')')
              .append("values(?,?)")
              .toString();
      return dbExecutor.insertRecord(
              getConnection(), result, List.of(
                      fields.get(0).get(t).toString(),
                      fields.get(1).get(t).toString()
              )
      );
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      throw new UserDaoException(e);
    }
  }

  @Override
  public SessionManager getSessionManager() {
    return sessionManager;
  }

  private Connection getConnection() {
    return sessionManager.getCurrentSession().getConnection();
  }
}
