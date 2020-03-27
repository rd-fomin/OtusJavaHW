package ru.otus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.core.Id;
import ru.otus.core.dao.UserDao;
import ru.otus.core.model.User;
import ru.otus.core.service.DBServiceUser;
import ru.otus.core.service.DbServiceUserImpl;
import ru.otus.h2.DataSourceH2;
import ru.otus.jdbc.DbExecutor;
import ru.otus.jdbc.dao.DaoJdbc;
import ru.otus.jdbc.sessionmanager.SessionManagerJdbc;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author sergey
 * created on 03.02.19.
 */
public class DbServiceDemo {
  private static Logger logger = LoggerFactory.getLogger(DbServiceDemo.class);

  public static void main(String[] args) throws Exception {
    DataSource dataSource = new DataSourceH2();
    DbServiceDemo demo = new DbServiceDemo();

    User user = new User(1, "dbServiceUser", 18);
    demo.createTable(dataSource, user);

    SessionManagerJdbc sessionManager = new SessionManagerJdbc(dataSource);
    DbExecutor<User> dbExecutor = new DbExecutor<>();
    UserDao<User> userDao = new DaoJdbc<>(sessionManager, dbExecutor);

    DBServiceUser<User> dbServiceUser = new DbServiceUserImpl<>(userDao);
    long id = dbServiceUser.saveUser(user);
    Optional<User> userNew = dbServiceUser.getObject(id);

    System.out.println(userNew);
    userNew.ifPresentOrElse(
        crUser -> logger.info("created user, name:{}", crUser.getName()),
        () -> logger.info("user was not created")
    );

  }

  private <T> void createTable(DataSource dataSource, T t) throws SQLException {
    try (Connection connection = dataSource.getConnection()) {
      Class<T> clazz = (Class<T>) t.getClass();
      List<Field> fields = new ArrayList<>();
      Field id = null;
      for (Field field : clazz.getDeclaredFields()) {
        field.setAccessible(true);
        if (!field.isAnnotationPresent(Id.class)) {
          fields.add(field);
        } else {
          id = field;
        }
      }
      String result = new StringBuilder("create table ")
              .append(clazz.getSimpleName().toLowerCase())
              .append('(')
              .append(id.getName()).append(" long auto_increment, ")
              .append(fields.get(0).getName().toLowerCase()).append(" varchar(50), ")
              .append(fields.get(1).getName().toLowerCase()).append(" int")
              .append(')')
              .toString();
      PreparedStatement pst = connection.prepareStatement(result);
      pst.executeUpdate();
    }
    System.out.println("table created");
  }
}
