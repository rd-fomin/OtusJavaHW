package ru.otus;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.core.model.User;
import ru.otus.core.sessionmanager.SessionManagerHibernate;
import ru.otus.hibernate.HibernateUtils;

@Configuration
public class DbConfig {

    @Bean
    SessionFactory sessionFactory() {
        return HibernateUtils.buildSessionFactory("hibernate.cfg.xml", User.class);
    }

    @Bean
    SessionManagerHibernate sessionManager(SessionFactory sessionFactory) {
        return new SessionManagerHibernate(sessionFactory);
    }

}
