package ru.otus;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.core.dao.ObjectDao;
import ru.otus.core.model.AddressDataSet;
import ru.otus.core.model.PhoneDataSet;
import ru.otus.core.model.User;
import ru.otus.core.service.DBServiceObject;
import ru.otus.core.service.DbServiceObjectImpl;
import ru.otus.hibernate.HibernateUtils;
import ru.otus.hibernate.dao.ObjectDaoHibernate;
import ru.otus.hibernate.sessionmanager.SessionManagerHibernate;

import java.util.List;
import java.util.Optional;

public class DbServiceDemo {
    private static Logger logger = LoggerFactory.getLogger(DbServiceDemo.class);

    public static void main(String[] args) {
        // Все главное см в тестах
        SessionFactory sessionFactory = HibernateUtils.buildSessionFactory("hibernate.cfg.xml", User.class, AddressDataSet.class, PhoneDataSet.class);

        SessionManagerHibernate sessionManager = new SessionManagerHibernate(sessionFactory);
        ObjectDao<User> objectDao = new ObjectDaoHibernate<>(sessionManager);
        DBServiceObject<User> dbServiceObject = new DbServiceObjectImpl<>(objectDao);


        long id = dbServiceObject.saveUser(
                new User(
                    0,
                    "Вася",
                    20,
                    new AddressDataSet(1, "My Address"),
                    List.of( new PhoneDataSet(1, "My Phone") )
                )
        );
        Optional<User> mayBeCreatedUser = dbServiceObject.getObject(id, User.class);

        id = dbServiceObject.saveUser(
                new User(
                    1L,
                    "А! Нет. Это же совсем не Вася",
                    20,
                    new AddressDataSet(1, "My Address"),
                    List.of( new PhoneDataSet(1, "My Phone") )
                )
        );
        Optional<User> mayBeUpdatedUser = dbServiceObject.getObject(id, User.class);

        outputUserOptional("Created object", mayBeCreatedUser);
        outputUserOptional("Updated object", mayBeUpdatedUser);

        mayBeUpdatedUser = dbServiceObject.getObject(id, User.class);

        outputUserOptional("Created object", mayBeCreatedUser);
        outputUserOptional("Updated object", mayBeUpdatedUser);
    }

    private static <T> void outputUserOptional(String header, Optional<T> mayBeUser) {
        System.out.println("-----------------------------------------------------------");
        System.out.println(header);
        mayBeUser.ifPresentOrElse(System.out::println, () -> logger.info("Object not found"));
    }
}
