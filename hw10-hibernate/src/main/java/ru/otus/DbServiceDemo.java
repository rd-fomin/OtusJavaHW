package ru.otus;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.hibernate.sessionmanager.SessionManagerHibernate;
import ru.otus.core.dao.UserDao;
import ru.otus.core.model.AddressDataSet;
import ru.otus.core.model.PhoneDataSet;
import ru.otus.core.model.User;
import ru.otus.core.service.DBServiceUser;
import ru.otus.core.service.DbServiceUserImpl;
import ru.otus.hibernate.HibernateUtils;
import ru.otus.hibernate.dao.UserDaoHibernate;

import java.util.List;
import java.util.Optional;

public class DbServiceDemo {
    private static Logger logger = LoggerFactory.getLogger(DbServiceDemo.class);

    public static void main(String[] args) {
        // Все главное см в тестах
        SessionFactory sessionFactory = HibernateUtils.buildSessionFactory("hibernate.cfg.xml", User.class, AddressDataSet.class, PhoneDataSet.class);

        SessionManagerHibernate sessionManager = new SessionManagerHibernate(sessionFactory);
        UserDao<User> userDao = new UserDaoHibernate<>(sessionManager);
        DBServiceUser<User> dbServiceUser = new DbServiceUserImpl<>(userDao);


        long id = dbServiceUser.saveUser(
                new User(
                    0,
                    "Вася",
                    20,
                    new AddressDataSet(1, "My Address"),
                    List.of( new PhoneDataSet(1, "My Phone") )
                )
        );
        Optional<User> mayBeCreatedUser = dbServiceUser.getObject(id, User.class);

        id = dbServiceUser.saveUser(
                new User(
                    1L,
                    "А! Нет. Это же совсем не Вася",
                    20,
                    new AddressDataSet(1, "My Address"),
                    List.of( new PhoneDataSet(1, "My Phone") )
                )
        );
        Optional<User> mayBeUpdatedUser = dbServiceUser.getObject(id, User.class);

        outputUserOptional("Created object", mayBeCreatedUser);
        outputUserOptional("Updated object", mayBeUpdatedUser);
    }

    private static <T> void outputUserOptional(String header, Optional<T> mayBeUser) {
        System.out.println("-----------------------------------------------------------");
        System.out.println(header);
        mayBeUser.ifPresentOrElse(System.out::println, () -> logger.info("Object not found"));
    }
}
