package ru.otus;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.otus.core.cache.Cache;
import ru.otus.core.cache.CacheImpl;
import ru.otus.core.dao.UserDao;
import ru.otus.core.dao.UserDaoImpl;
import ru.otus.core.service.DbServiceUser;
import ru.otus.core.service.DbServiceUserImpl;
import ru.otus.core.sessionmanager.SessionManagerHibernate;
import ru.otus.core.utils.HibernateUtils;
import ru.otus.domain.User;

@Configuration
@ComponentScan
@EnableWebMvc
public class DatabaseConfig implements WebMvcConfigurer {

    @Bean
    public SessionManagerHibernate sessionManager() {
        SessionFactory sessionFactory = HibernateUtils.buildSessionFactory("hibernate.cfg.xml", User.class);
        return new SessionManagerHibernate(sessionFactory);
    }

    @Bean
    public UserDao userDao(SessionManagerHibernate sessionManagerHibernate) {
        return new UserDaoImpl(sessionManagerHibernate);
    }

    @Bean
    public Cache<Long, User> cache() {
        return new CacheImpl<>();
    }

    @Bean
    public DbServiceUser dbServiceUser(UserDao userDao, Cache<Long, User> cache) {
        DbServiceUser serviceUser = new DbServiceUserImpl(userDao, cache);
        serviceUser.save(new User("Крис Гир", "user1", "11111"));
        serviceUser.save(new User("Ая Кэш", "user2", "11111"));
        serviceUser.save(new User("Десмин Боргес", "user3", "11111"));
        serviceUser.save(new User("Кетер Донохью", "user4", "11111"));
        serviceUser.save(new User("Стивен Шнайдер", "user5", "11111"));
        serviceUser.save(new User("Джанет Вэрни", "user6", "11111"));
        serviceUser.save(new User("Брэндон Смит", "user7", "11111"));
        return serviceUser;
    }

}
